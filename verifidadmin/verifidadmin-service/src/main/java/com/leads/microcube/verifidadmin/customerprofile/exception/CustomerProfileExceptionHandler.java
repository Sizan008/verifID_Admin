package com.leads.microcube.verifidadmin.customerprofile.exception;

import com.leads.microcube.verifidadmin.common.response.ApiResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.leads.microcube.verifidadmin.customerprofile")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomerProfileExceptionHandler {

  @ExceptionHandler({
    CustomerProfileValidationException.class,
    CustomerProfileNotFoundException.class
  })
  public ResponseEntity<ApiResponse<Void>> handleCustomerProfileException(
      RuntimeException exception) {
    return ResponseEntity.ok(ApiResponse.failure(exception.getMessage()));
  }
}
