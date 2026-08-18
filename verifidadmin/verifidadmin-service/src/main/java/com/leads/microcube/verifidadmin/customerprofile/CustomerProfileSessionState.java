package com.leads.microcube.verifidadmin.customerprofile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@RequiredArgsConstructor
public class CustomerProfileSessionState {

  private static final String AUTH_TYPE_KEY =
      "VERIFID_ADMIN_CUSTOMER_PROFILE_AUTH_TYPE";
  private static final String TARGET_PATH_KEY =
      "VERIFID_ADMIN_CUSTOMER_PROFILE_TARGET_PATH";

  private final HttpServletRequest request;

  public void store(String authType, String targetPath) {
    HttpSession session = request.getSession(true);
    session.setAttribute(AUTH_TYPE_KEY, authType);
    session.setAttribute(TARGET_PATH_KEY, targetPath);
  }

  public String retrieveAuthType() {
    HttpSession session = request.getSession(false);
    Object value = session == null ? null : session.getAttribute(AUTH_TYPE_KEY);
    if (value instanceof String && StringUtils.hasText((String) value)) {
      return ((String) value).trim();
    }
    return "U";
  }

  public String retrieveTargetPath() {
    HttpSession session = request.getSession(false);
    Object value = session == null ? null : session.getAttribute(TARGET_PATH_KEY);
    if (value instanceof String && StringUtils.hasText((String) value)) {
      return ((String) value).trim();
    }
    return "CustomerProfile/UnauthorizedCustomers";
  }
}
