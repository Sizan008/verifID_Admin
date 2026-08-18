package com.leads.microcube.verifidadmin.home.exception;

import com.leads.microcube.verifidadmin.common.response.ApiResponse;
import com.leads.microcube.verifidadmin.home.HomeController;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(assignableTypes = HomeController.class)
public class HomeExceptionHandler {

  @ExceptionHandler(HomeValidationException.class)
  public ResponseEntity<ApiResponse<Void>> handleHomeValidation(
      HomeValidationException exception) {
    return ResponseEntity.ok(ApiResponse.failure(exception.getMessage()));
  }
}
