package com.leads.microcube.verifidadmin.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leads.microcube.verifidadmin.account.client.SecurityApiGateway;
import com.leads.microcube.verifidadmin.account.client.dto.SecurityApiResponse;
import com.leads.microcube.verifidadmin.account.client.dto.SecuritySessionContainer;
import com.leads.microcube.verifidadmin.account.command.AuthorizeAccountUser;
import com.leads.microcube.verifidadmin.account.command.CreateAccountUser;
import com.leads.microcube.verifidadmin.account.command.DeclineAccountUser;
import com.leads.microcube.verifidadmin.account.command.DeleteAccountUser;
import com.leads.microcube.verifidadmin.account.command.LoginAccount;
import com.leads.microcube.verifidadmin.account.command.LoginContext;
import com.leads.microcube.verifidadmin.account.command.UpdateAccountUser;
import com.leads.microcube.verifidadmin.account.exception.AccountNotFoundException;
import com.leads.microcube.verifidadmin.account.exception.AccountValidationException;
import com.leads.microcube.verifidadmin.account.query.AccountLoginResponse;
import com.leads.microcube.verifidadmin.account.repository.BankUserRoleEntity;
import com.leads.microcube.verifidadmin.account.repository.BankUserRoleRepository;
import com.leads.microcube.verifidadmin.common.security.CurrentUser;
import com.leads.microcube.verifidadmin.common.security.CurrentUserProvider;
import com.leads.microcube.verifidadmin.log.UserActivityLogService;
import com.leads.microcube.verifidadmin.log.command.RecordCurrentUserActivity;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

  private static final String AUTHORIZED_STATUS = "A";
  private static final String UNAUTHORIZED_STATUS = "U";
  private static final String DECLINED_STATUS = "D";

  private final BankUserRoleRepository bankUserRoleRepository;
  private final CurrentUserProvider currentUserProvider;
  private final SecurityApiGateway securityApiGateway;
  private final LegacyLicenceValidator legacyLicenceValidator;
  private final AccountSettings accountSettings;
  private final AccountRolePolicy accountRolePolicy;
  private final ObjectMapper objectMapper;
  private final UserActivityLogService userActivityLogService;
  private final AccountSessionStore accountSessionStore;

  @Override
  public AccountLoginResponse process(LoginAccount command, LoginContext context) {
    validateLogin(command, context);
    legacyLicenceValidator.validate();
    SecurityApiResponse apiResponse = securityApiGateway.login(command, context);
    if (!apiResponse.isResponseStatus()) {
      throw new AccountValidationException(resolveMessage(apiResponse.getResponseMessage(), "Login failed."));
    }

    SecuritySessionContainer sessionContainer =
        deserializeSessionContainer(apiResponse.getResponseBusinessData());
    CurrentUser currentUser = sessionContainer.getLoginUser();
    if (!StringUtils.hasText(currentUser.getSessionId())) {
      currentUser.setSessionId(context.getSessionId());
    }
    currentUserProvider.setCurrentUser(currentUser);
    accountSessionStore.store(sessionContainer, apiResponse.getResponseBusinessData());
    recordActivity("Login", "is Logging In");
    return AccountLoginResponse.builder().currentUser(currentUser).build();
  }

  @Override
  public void process() {
    CurrentUser currentUser = currentUserProvider.getCurrentUser();
    if (accountSettings.isLeadsCbs()) {
      SecurityApiResponse response = securityApiGateway.logout(currentUser.getUserId());
      if (!response.isResponseStatus()) {
        throw new AccountValidationException(
            resolveMessage(response.getResponseMessage(), "Logout failed."));
      }
    }
    recordActivity("Logout", "is Logging Out");
    currentUserProvider.clear();
  }

  @Override
  @Transactional
  public void process(CreateAccountUser command) {
    accountRolePolicy.requireMaker();
    validateCreate(command);
    String userId = command.getUserId().trim();
    if (bankUserRoleRepository.existsByUserIdIgnoreCase(userId)) {
      throw new AccountValidationException("User with this UserId Already Exists.");
    }

    String currentUserId = accountRolePolicy.retrieveCurrentUserId();
    LocalDateTime currentTime = LocalDateTime.now();
    BankUserRoleEntity entity =
        BankUserRoleEntity.builder()
            .userRoleId(bankUserRoleRepository.retrieveMaximumUserRoleId() + 1)
            .userId(userId)
            .userName(trimToNull(command.getUserName()))
            .userPassword("1")
            .branchId(command.getBranchId().trim())
            .userRole(command.getUserRole().trim())
            .lastAction("ADD")
            .makeBy(currentUserId)
            .makeDt(currentTime)
            .checkBy(currentUserId)
            .checkDt(currentTime)
            .authStatus(resolvePendingStatus())
            .build();
    bankUserRoleRepository.saveAndFlush(entity);
  }

  @Override
  @Transactional
  public void process(UpdateAccountUser command) {
    accountRolePolicy.requireMaker();
    validateUpdate(command);
    BankUserRoleEntity entity = retrieveForUpdate(command.getUserId());
    String currentUserId = accountRolePolicy.retrieveCurrentUserId();
    LocalDateTime currentTime = LocalDateTime.now();
    entity.setUserName(trimToNull(command.getUserName()));
    entity.setUserPassword(command.getUserPassword().trim());
    entity.setBranchId(command.getBranchId().trim());
    entity.setUserRole(command.getUserRole().trim());
    entity.setLastAction("EDT");
    entity.setAuthStatus(resolvePendingStatus());
    entity.setUpdateBy(currentUserId);
    entity.setUpdateDt(currentTime);
    entity.setCheckBy(currentUserId);
    entity.setCheckDt(currentTime);
    bankUserRoleRepository.saveAndFlush(entity);
  }

  @Override
  @Transactional
  public void process(DeleteAccountUser command) {
    accountRolePolicy.requireMaker();
    String userId = requireUserId(command == null ? null : command.getUserId());
    BankUserRoleEntity entity = retrieveForUpdate(userId);
    String currentUserId = accountRolePolicy.retrieveCurrentUserId();
    LocalDateTime currentTime = LocalDateTime.now();
    entity.setAuthStatus(UNAUTHORIZED_STATUS);
    entity.setLastAction("DEL");
    entity.setUpdateBy(currentUserId);
    entity.setUpdateDt(currentTime);
    entity.setCheckBy(currentUserId);
    entity.setCheckDt(currentTime);
    bankUserRoleRepository.saveAndFlush(entity);
  }

  @Override
  @Transactional
  public void process(AuthorizeAccountUser command) {
    accountRolePolicy.requireChecker();
    String userId = requireUserId(command == null ? null : command.getUserId());
    BankUserRoleEntity entity = retrieveForUpdate(userId);
    entity.setAuthStatus("DEL".equalsIgnoreCase(trim(entity.getLastAction()))
        ? DECLINED_STATUS : AUTHORIZED_STATUS);
    entity.setAuthBy(accountRolePolicy.retrieveCurrentUserId());
    entity.setAuthDt(LocalDateTime.now());
    bankUserRoleRepository.saveAndFlush(entity);
  }

  @Override
  @Transactional
  public void process(DeclineAccountUser command) {
    accountRolePolicy.requireChecker();
    String userId = requireUserId(command == null ? null : command.getUserId());
    BankUserRoleEntity entity = retrieveForUpdate(userId);
    entity.setAuthStatus(DECLINED_STATUS);
    entity.setAuthBy(accountRolePolicy.retrieveCurrentUserId());
    entity.setAuthDt(LocalDateTime.now());
    bankUserRoleRepository.saveAndFlush(entity);
  }

  private SecuritySessionContainer deserializeSessionContainer(String responseBusinessData) {
    if (!StringUtils.hasText(responseBusinessData)) {
      throw new AccountValidationException("Security API session data is missing.");
    }
    try {
      SecuritySessionContainer container =
          objectMapper.readValue(responseBusinessData, SecuritySessionContainer.class);
      if (container == null
          || container.getLoginUser() == null
          || !StringUtils.hasText(container.getLoginUser().getUserId())) {
        throw new AccountValidationException("Security API session user is missing.");
      }
      return container;
    } catch (JsonProcessingException exception) {
      throw new AccountValidationException("Security API session data is invalid.", exception);
    }
  }

  private BankUserRoleEntity retrieveForUpdate(String userId) {
    return bankUserRoleRepository
        .retrieveForUpdate(requireUserId(userId))
        .orElseThrow(() -> new AccountNotFoundException("No data Found"));
  }

  private String resolvePendingStatus() {
    return accountSettings.isUserAutoAuthorizationEnabled()
        ? AUTHORIZED_STATUS : UNAUTHORIZED_STATUS;
  }

  private void validateLogin(LoginAccount command, LoginContext context) {
    if (command == null
        || !StringUtils.hasText(command.getUserName())
        || !StringUtils.hasText(command.getPassword())) {
      throw new AccountValidationException("Username and password are required.");
    }
    if (context == null || !StringUtils.hasText(context.getSessionId())) {
      throw new AccountValidationException("Session ID is required.");
    }
  }

  private void validateCreate(CreateAccountUser command) {
    if (command == null) {
      throw new AccountValidationException("Account user information is required.");
    }
    requireUserId(command.getUserId());
    requireText(command.getBranchId(), "Branch ID is required.");
    requireText(command.getUserRole(), "User role is required.");
  }

  private void validateUpdate(UpdateAccountUser command) {
    if (command == null) {
      throw new AccountValidationException("Account user information is required.");
    }
    requireUserId(command.getUserId());
    requireText(command.getUserPassword(), "User password is required.");
    requireText(command.getBranchId(), "Branch ID is required.");
    requireText(command.getUserRole(), "User role is required.");
  }

  private String requireUserId(String userId) {
    requireText(userId, "User ID is required.");
    return userId.trim();
  }

  private void requireText(String value, String message) {
    if (!StringUtils.hasText(value)) {
      throw new AccountValidationException(message);
    }
  }

  private String trimToNull(String value) {
    return StringUtils.hasText(value) ? value.trim() : null;
  }

  private String trim(String value) {
    return value == null ? "" : value.trim();
  }

  private String resolveMessage(String value, String fallback) {
    return StringUtils.hasText(value) ? value.trim() : fallback;
  }

  private void recordActivity(String actionType, String particulars) {
    userActivityLogService.process(
        RecordCurrentUserActivity.builder()
            .trackingNo(0L)
            .stepId(0)
            .actionType(actionType)
            .actionParticulars(particulars)
            .requestChannel("")
            .build());
  }
}
