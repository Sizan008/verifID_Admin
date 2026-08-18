package com.leads.microcube.verifidadmin.account;

import com.leads.microcube.verifidadmin.account.client.dto.SecuritySessionContainer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@RequiredArgsConstructor
public class AccountSessionStore {

  public static final String LEGACY_SESSION_CONTAINER_KEY = "SessionContainer";
  public static final String SECURITY_SESSION_KEY = "VERIFID_ADMIN_SECURITY_SESSION";

  private final HttpServletRequest request;

  public void store(SecuritySessionContainer sessionContainer, String rawSessionContainer) {
    HttpSession session = request.getSession(true);
    session.setAttribute(LEGACY_SESSION_CONTAINER_KEY, rawSessionContainer);
    session.setAttribute(SECURITY_SESSION_KEY, sessionContainer);
  }

  public SecuritySessionContainer retrieve() {
    HttpSession session = request.getSession(false);
    if (session == null) {
      return null;
    }
    Object value = session.getAttribute(SECURITY_SESSION_KEY);
    return value instanceof SecuritySessionContainer
        ? (SecuritySessionContainer) value
        : null;
  }
}
