package com.leads.microcube.verifidadmin.common.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class FeaturePermissionInterceptor implements HandlerInterceptor {

  private final CurrentUserProvider currentUserProvider;

  @Value("${verifidadmin.security.enabled:false}")
  private boolean securityEnabled;

  @Override
  public boolean preHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler) {
    if (!securityEnabled || "OPTIONS".equalsIgnoreCase(request.getMethod())) {
      return true;
    }

    CurrentUser currentUser = currentUserProvider.getCurrentUser();
    if (!(handler instanceof HandlerMethod)) {
      return true;
    }

    HandlerMethod handlerMethod = (HandlerMethod) handler;
    RequirePermission permission =
        AnnotatedElementUtils.findMergedAnnotation(
            handlerMethod.getMethod(), RequirePermission.class);
    if (permission == null) {
      permission =
          AnnotatedElementUtils.findMergedAnnotation(
              handlerMethod.getBeanType(), RequirePermission.class);
    }

    if (permission == null) {
      return true;
    }

    if (!hasPermission(currentUser, permission)) {
      throw new FeatureAccessDeniedException();
    }
    return true;
  }

  private boolean hasPermission(
      CurrentUser currentUser,
      RequirePermission requiredPermission) {
    List<FunctionAccess> functionAccesses = currentUser.getUserFunctionAccess();
    if (functionAccesses == null) {
      return false;
    }

    return functionAccesses.stream()
        .filter(access -> sameTarget(access.getTargetPath(), requiredPermission.targetPath()))
        .anyMatch(access -> isAllowed(access, requiredPermission.value()));
  }

  private boolean sameTarget(String actual, String expected) {
    return StringUtils.hasText(actual)
        && actual.trim().equalsIgnoreCase(expected.trim());
  }

  private boolean isAllowed(FunctionAccess access, PermissionType permissionType) {
    Integer flag =
        switch (permissionType) {
          case VIEW -> access.getAllowViewFlag();
          case ADD -> access.getAllowAddFlag();
          case EDIT -> access.getAllowEditFlag();
          case DELETE -> access.getAllowDeleteFlag();
          case AUTHORIZE -> access.getAllowAuthFlag();
          case PROCESS -> access.getAllowProcessFlag();
          case REPORT_VIEW -> access.getAllowReportViewFlag();
          case REPORT_PRINT -> access.getAllowReportPrintFlag();
          case REPORT_GENERATE -> access.getAllowReportGenFlag();
        };
    return Integer.valueOf(1).equals(flag);
  }
}
