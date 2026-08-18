package com.leads.microcube.verifidadmin.common.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@RequiredArgsConstructor
public class SessionCurrentUserProvider implements CurrentUserProvider {

  public static final String CURRENT_USER_SESSION_KEY =
      "VERIFID_ADMIN_CURRENT_USER";

  private final HttpServletRequest request;

  @Override
  public CurrentUser getCurrentUser() {
    HttpSession session = request.getSession(false);
    if (session == null) {
      throw new UnauthenticatedException();
    }

    Object value = session.getAttribute(CURRENT_USER_SESSION_KEY);
    if (!(value instanceof CurrentUser)) {
      throw new UnauthenticatedException();
    }

    return (CurrentUser) value;
  }

  @Override
  public void setCurrentUser(CurrentUser currentUser) {
    if (currentUser == null) {
      throw new IllegalArgumentException("Current user is required.");
    }
    request.getSession(true).setAttribute(CURRENT_USER_SESSION_KEY, currentUser);
  }

  @Override
  public void clear() {
    HttpSession session = request.getSession(false);
    if (session != null) {
      session.invalidate();
    }
  }
}
