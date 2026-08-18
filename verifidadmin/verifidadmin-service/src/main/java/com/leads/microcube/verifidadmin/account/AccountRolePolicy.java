package com.leads.microcube.verifidadmin.account;

import com.leads.microcube.verifidadmin.account.repository.BankUserRoleEntity;
import com.leads.microcube.verifidadmin.account.repository.BankUserRoleRepository;
import com.leads.microcube.verifidadmin.common.security.CurrentUser;
import com.leads.microcube.verifidadmin.common.security.CurrentUserProvider;
import com.leads.microcube.verifidadmin.common.security.FeatureAccessDeniedException;
import com.leads.microcube.verifidadmin.account.exception.AccountValidationException;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AccountRolePolicy {

  private final BankUserRoleRepository bankUserRoleRepository;
  private final CurrentUserProvider currentUserProvider;
  private final boolean securityEnabled;

  public AccountRolePolicy(
      BankUserRoleRepository bankUserRoleRepository,
      CurrentUserProvider currentUserProvider,
      @Value("${verifidadmin.security.enabled:false}") boolean securityEnabled) {
    this.bankUserRoleRepository = bankUserRoleRepository;
    this.currentUserProvider = currentUserProvider;
    this.securityEnabled = securityEnabled;
  }

  public void requireSuperAdmin() {
    requireRole("SUPERADMIN");
  }

  public void requireMaker() {
    requireRole("SUPERADMIN", "USERMAKER");
  }

  public void requireChecker() {
    requireRole("SUPERADMIN", "USERCHECKER");
  }

  public void requireDifferentUser(String userId, String message) {
    if (!securityEnabled) {
      return;
    }
    CurrentUser currentUser = currentUserProvider.getCurrentUser();
    if (currentUser.getUserId() != null && currentUser.getUserId().equalsIgnoreCase(userId)) {
      throw new AccountValidationException(message);
    }
  }

  public void requireSameUser(String userId) {
    if (!securityEnabled) {
      return;
    }
    CurrentUser currentUser = currentUserProvider.getCurrentUser();
    if (currentUser.getUserId() == null || !currentUser.getUserId().equalsIgnoreCase(userId)) {
      throw new AccountValidationException(
              "You Can Not Update / Delete Someone Else's Information");
    }
  }

  public String retrieveCurrentUserId() {
    if (!securityEnabled) {
      return "admin";
    }
    return currentUserProvider.getCurrentUser().getUserId().trim();
  }

  private void requireRole(String... allowedRoles) {
    if (!securityEnabled) {
      return;
    }
    String userId = retrieveCurrentUserId();
    String role =
        bankUserRoleRepository
            .findByUserIdIgnoreCase(userId)
            .map(BankUserRoleEntity::getUserRole)
            .map(String::trim)
            .orElseThrow(FeatureAccessDeniedException::new);
    boolean allowed = Arrays.stream(allowedRoles).anyMatch(role::equalsIgnoreCase);
    if (!allowed) {
      throw new FeatureAccessDeniedException();
    }
  }
}
