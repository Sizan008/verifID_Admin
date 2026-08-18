package com.leads.microcube.verifidadmin.common.security;

import com.leads.microcube.verifidadmin.common.response.ApiResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE + 1)
@RestControllerAdvice
public class SecurityExceptionHandler {

  @ExceptionHandler(UnauthenticatedException.class)
  public ResponseEntity<ApiResponse<Void>> handleUnauthenticated(
      UnauthenticatedException exception) {
    return ResponseEntity.ok(
        new ApiResponse<>("UNAUTH", exception.getMessage(), null));
  }

  @ExceptionHandler(FeatureAccessDeniedException.class)
  public ResponseEntity<ApiResponse<Void>> handleAccessDenied(
      FeatureAccessDeniedException exception) {
    return ResponseEntity.ok(
        new ApiResponse<>("UNAUTH", exception.getMessage(), null));
  }
}
