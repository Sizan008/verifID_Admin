package com.leads.microcube.verifidadmin.customerprofile;

import com.leads.microcube.verifidadmin.common.security.CurrentUser;
import com.leads.microcube.verifidadmin.common.security.CurrentUserProvider;
import com.leads.microcube.verifidadmin.common.security.FunctionAccess;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class CustomerProfilePermissionSupport {

  private final CurrentUserProvider currentUserProvider;
  private final CustomerProfileSessionState sessionState;

  @Value("${verifidadmin.security.enabled:false}")
  private boolean securityEnabled;

  public boolean hasAuthorizationPermission() {
    return hasPermission(FunctionAccess::getAllowAuthFlag);
  }

  public boolean hasViewPermission() {
    if (!securityEnabled) {
      return true;
    }
    CurrentUser currentUser = currentUserProvider.getCurrentUser();
    List<FunctionAccess> accesses = currentUser.getUserFunctionAccess();
    if (accesses == null) {
      return false;
    }
    String targetPath = sessionState.retrieveTargetPath();
    return accesses.stream()
        .anyMatch(access -> sameTarget(access.getTargetPath(), targetPath));
  }

  private boolean hasPermission(
      java.util.function.Function<FunctionAccess, Integer> permissionSelector) {
    if (!securityEnabled) {
      return true;
    }
    CurrentUser currentUser = currentUserProvider.getCurrentUser();
    List<FunctionAccess> accesses = currentUser.getUserFunctionAccess();
    if (accesses == null) {
      return false;
    }
    String targetPath = sessionState.retrieveTargetPath();
    return accesses.stream()
        .filter(access -> sameTarget(access.getTargetPath(), targetPath))
        .map(permissionSelector)
        .anyMatch(flag -> Integer.valueOf(1).equals(flag));
  }

  private boolean sameTarget(String actual, String expected) {
    return StringUtils.hasText(actual)
        && actual.trim().equalsIgnoreCase(expected.trim());
  }
}
