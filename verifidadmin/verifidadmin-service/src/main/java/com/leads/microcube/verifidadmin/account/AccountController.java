package com.leads.microcube.verifidadmin.account;

import com.leads.microcube.verifidadmin.account.command.AuthorizeAccountUser;
import com.leads.microcube.verifidadmin.account.command.CreateAccountUser;
import com.leads.microcube.verifidadmin.account.command.DeclineAccountUser;
import com.leads.microcube.verifidadmin.account.command.DeleteAccountUser;
import com.leads.microcube.verifidadmin.account.command.LoginAccount;
import com.leads.microcube.verifidadmin.account.command.LoginContext;
import com.leads.microcube.verifidadmin.account.command.UpdateAccountUser;
import com.leads.microcube.verifidadmin.account.query.AccountLoginConfigurationResponse;
import com.leads.microcube.verifidadmin.account.query.AccountLoginResponse;
import com.leads.microcube.verifidadmin.account.query.AccountUser;
import com.leads.microcube.verifidadmin.account.query.AccountUserResponse;
import com.leads.microcube.verifidadmin.account.query.CurrentAccountUserResponse;
import com.leads.microcube.verifidadmin.common.response.ApiResponse;
import com.leads.microcube.verifidadmin.log.UserActivityLogService;
import com.leads.microcube.verifidadmin.log.command.RecordCurrentUserActivity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Account")
@RequiredArgsConstructor
public class AccountController {

  private final AccountService accountService;
  private final AccountQueryService accountQueryService;
  private final AccountRolePolicy accountRolePolicy;
  private final UserActivityLogService userActivityLogService;

  @GetMapping("/Login")
  public ResponseEntity<ApiResponse<AccountLoginConfigurationResponse>> retrieveLoginConfiguration() {
    return ResponseEntity.ok(ApiResponse.success(accountQueryService.retrieveLoginConfiguration()));
  }

  @PostMapping("/Validate")
  public ResponseEntity<ApiResponse<AccountLoginResponse>> login(
      @Valid @RequestBody LoginAccount command, HttpServletRequest request) {
    AccountLoginResponse response = accountService.process(command, createLoginContext(request));
    return ResponseEntity.ok(ApiResponse.success("Login Successfull!", response));
  }

  @PostMapping("/ValidateCentralLogin")
  public ResponseEntity<ApiResponse<AccountLoginResponse>> loginFromCentralPlatform(
      @Valid @RequestBody LoginAccount command, HttpServletRequest request) {
    AccountLoginResponse response = accountService.process(command, createLoginContext(request));
    return ResponseEntity.ok(ApiResponse.success("Login Successfull!", response));
  }

  @GetMapping("/getUserName")
  public ResponseEntity<ApiResponse<CurrentAccountUserResponse>> retrieveCurrentUser() {
    return ResponseEntity.ok(ApiResponse.success(accountQueryService.retrieveCurrentUser()));
  }

  @GetMapping("/SignOut")
  public ResponseEntity<ApiResponse<Void>> logout() {
    accountService.process();
    return ResponseEntity.ok(ApiResponse.success("Successfully logged out.", null));
  }

  @GetMapping("/ActiveDirectoryUsers")
  public ResponseEntity<ApiResponse<List<AccountUserResponse>>> retrieveAccountUsers() {
    return ResponseEntity.ok(ApiResponse.success(accountQueryService.retrieveUsers()));
  }

  @GetMapping("/CreateActiveDirectoryUserView")
  public ResponseEntity<ApiResponse<CreateAccountUser>> retrieveCreateAccountUserForm() {
    accountRolePolicy.requireSuperAdmin();
    return ResponseEntity.ok(ApiResponse.success(new CreateAccountUser()));
  }

  @PostMapping("/CreateActiveDirectoryUser")
  public ResponseEntity<ApiResponse<Void>> registerAccountUser(
      @Valid @RequestBody CreateAccountUser command) {
    accountService.process(command);
    recordActivity("Create", "is Creating New Active Directory User");
    return ResponseEntity.ok(ApiResponse.success("Successfully new user created.", null));
  }

  @GetMapping("/UpdateActiveDirectoryUserView")
  public ResponseEntity<ApiResponse<AccountUserResponse>> retrieveUpdateAccountUserForm(
      @RequestParam("userId") String userId) {
    accountRolePolicy.requireSuperAdmin();
    accountRolePolicy.requireSameUser(userId);
    return ResponseEntity.ok(
        ApiResponse.success(accountQueryService.retrieveUser(new AccountUser(userId))));
  }

  @PostMapping("/UpdateActiveDirectoryUser")
  public ResponseEntity<ApiResponse<Void>> updateAccountUser(
      @Valid @RequestBody UpdateAccountUser command) {
    accountService.process(command);
    recordActivity("Edit", "is Updating Active Directory User");
    return ResponseEntity.ok(ApiResponse.success("Successfully user updated.", null));
  }

  @GetMapping("/DeleteActiveDirectoryUserView")
  public ResponseEntity<ApiResponse<AccountUserResponse>> retrieveDeleteAccountUserForm(
      @RequestParam("userId") String userId) {
    accountRolePolicy.requireSuperAdmin();
    accountRolePolicy.requireDifferentUser(
            userId,
            "You Can Not Update / Delete Your Own Information. Please Inform Other SuperAdmin.");
    return ResponseEntity.ok(
        ApiResponse.success(accountQueryService.retrieveUser(new AccountUser(userId))));
  }

  @PostMapping("/DeleteActiveDirectoryUser")
  public ResponseEntity<ApiResponse<Void>> deleteAccountUser(
      @Valid @RequestBody DeleteAccountUser command) {
    accountService.process(command);
    recordActivity("Delete", "is Requesting Active Directory User Deletion");
    return ResponseEntity.ok(ApiResponse.success("Successfully User Deleted.", null));
  }

  @GetMapping("/ActiveDirectoryUnauthorizedQueue")
  public ResponseEntity<ApiResponse<List<AccountUserResponse>>> retrieveUnauthorizedAccountUsers() {
    return ResponseEntity.ok(ApiResponse.success(accountQueryService.retrieveUnauthorizedUsers()));
  }

  @GetMapping("/AuthorizeActiveDirectoryUserView")
  public ResponseEntity<ApiResponse<AccountUserResponse>> retrieveAuthorizeAccountUserForm(
      @RequestParam("userId") String userId) {
    accountRolePolicy.requireChecker();
    accountRolePolicy.requireDifferentUser(
            userId,
            "You Can Not Update / Delete Your Own Information");
    return ResponseEntity.ok(
        ApiResponse.success(accountQueryService.retrieveUser(new AccountUser(userId))));
  }

  @PostMapping("/AuthorizeActiveDirectoryUser")
  public ResponseEntity<ApiResponse<Void>> authorizeAccountUser(
      @Valid @RequestBody AuthorizeAccountUser command) {
    accountService.process(command);
    recordActivity("Authorize", "is Authorizing Active Directory User");
    return ResponseEntity.ok(ApiResponse.success("Successfully authorized.", null));
  }

  @GetMapping("/DeclineActiveDirectoryUserView")
  public ResponseEntity<ApiResponse<AccountUserResponse>> retrieveDeclineAccountUserForm(
      @RequestParam("userId") String userId) {
    accountRolePolicy.requireChecker();
    accountRolePolicy.requireDifferentUser(
            userId,
            "You Can Not Update / Delete Your Own Information");
    return ResponseEntity.ok(
        ApiResponse.success(accountQueryService.retrieveUser(new AccountUser(userId))));
  }

  @PostMapping("/DeclineActiveDirectoryUser")
  public ResponseEntity<ApiResponse<Void>> declineAccountUser(
      @Valid @RequestBody DeclineAccountUser command) {
    accountService.process(command);
    recordActivity("Decline", "is Declining Active Directory User");
    return ResponseEntity.ok(ApiResponse.success("Successfully Declined.", null));
  }

  private LoginContext createLoginContext(HttpServletRequest request) {
    return LoginContext.builder()
        .clientIp(request.getRemoteAddr())
        .serverIp(request.getLocalAddr())
        .sessionId(request.getSession(true).getId())
        .build();
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
