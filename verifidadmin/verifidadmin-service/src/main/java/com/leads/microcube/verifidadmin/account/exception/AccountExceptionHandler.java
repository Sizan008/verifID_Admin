package com.leads.microcube.verifidadmin.account.exception;

import com.leads.microcube.verifidadmin.common.response.ApiResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE + 2)
@RestControllerAdvice(basePackages = "com.leads.microcube.verifidadmin.account")
public class AccountExceptionHandler {

  @ExceptionHandler({AccountValidationException.class, AccountNotFoundException.class})
  public ResponseEntity<ApiResponse<Void>> handleAccountException(RuntimeException exception) {
    return ResponseEntity.ok(ApiResponse.failure(exception.getMessage()));
  }
}
