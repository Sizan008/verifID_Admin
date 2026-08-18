package com.leads.microcube.verifidadmin.account;

import com.leads.microcube.verifidadmin.account.exception.AccountNotFoundException;
import com.leads.microcube.verifidadmin.account.exception.AccountValidationException;
import com.leads.microcube.verifidadmin.account.query.AccountLoginConfigurationResponse;
import com.leads.microcube.verifidadmin.account.query.AccountUser;
import com.leads.microcube.verifidadmin.account.query.AccountUserResponse;
import com.leads.microcube.verifidadmin.account.query.CurrentAccountUserResponse;
import com.leads.microcube.verifidadmin.account.repository.BankUserRoleRepository;
import com.leads.microcube.verifidadmin.common.security.CurrentUser;
import com.leads.microcube.verifidadmin.common.security.CurrentUserProvider;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountQueryServiceImpl implements AccountQueryService {

  private static final String UNAUTHORIZED_STATUS = "U";

  private final BankUserRoleRepository bankUserRoleRepository;
  private final CurrentUserProvider currentUserProvider;
  private final AccountMapper accountMapper;
  private final AccountSettings accountSettings;
  private final AccountRolePolicy accountRolePolicy;

  @Override
  public AccountLoginConfigurationResponse retrieveLoginConfiguration() {
    return AccountLoginConfigurationResponse.builder()
        .centralLoginEnabled(accountSettings.isCentralLoginEnabled())
        .centralLoginUrl(accountSettings.getCentralLoginUrl())
        .logoutUrl(accountSettings.getLogoutUrl())
        .build();
  }

  @Override
  public CurrentAccountUserResponse retrieveCurrentUser() {
    CurrentUser currentUser = currentUserProvider.getCurrentUser();
    return CurrentAccountUserResponse.builder()
        .userName(currentUser.getUserName())
        .userBranch(currentUser.getHomeBranchId())
        .userBranchName(currentUser.getHomeBranchName())
        .build();
  }

  @Override
  public List<AccountUserResponse> retrieveUsers() {
    accountRolePolicy.requireSuperAdmin();
    try {
      return bankUserRoleRepository.findAllByOrderByUserRoleIdAsc().stream()
          .map(accountMapper::toResponse)
          .toList();
    } catch (RuntimeException exception) {
      log.error("Unable to retrieve account users.", exception);
      throw new AccountValidationException("Unable to retrieve account users.");
    }
  }

  @Override
  public AccountUserResponse retrieveUser(AccountUser query) {
    String userId = requireUserId(query == null ? null : query.getUserId());
    return bankUserRoleRepository
        .findByUserIdIgnoreCase(userId)
        .map(accountMapper::toResponse)
        .orElseThrow(() -> new AccountNotFoundException("User not found."));
  }

  @Override
  public List<AccountUserResponse> retrieveUnauthorizedUsers() {
    accountRolePolicy.requireChecker();
    String currentUserId = accountRolePolicy.retrieveCurrentUserId();
    return bankUserRoleRepository.findAllByOrderByUserRoleIdAsc().stream()
        .filter(user -> UNAUTHORIZED_STATUS.equalsIgnoreCase(trim(user.getAuthStatus())))
        .filter(
            user ->
                "IBU".equalsIgnoreCase(trim(user.getMakeBy()))
                    || "MOB".equalsIgnoreCase(trim(user.getMakeBy()))
                    || !currentUserId.equalsIgnoreCase(trim(user.getCheckBy())))
        .map(accountMapper::toResponse)
        .toList();
  }

  private String requireUserId(String userId) {
    if (!StringUtils.hasText(userId)) {
      throw new AccountValidationException("User ID is required.");
    }
    return userId.trim();
  }

  private String trim(String value) {
    return value == null ? "" : value.trim();
  }
}
