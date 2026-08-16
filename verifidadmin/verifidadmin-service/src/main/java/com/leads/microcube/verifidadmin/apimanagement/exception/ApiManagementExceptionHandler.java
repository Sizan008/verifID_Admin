package com.leads.microcube.verifidadmin.apimanagement.exception;

import com.leads.microcube.verifidadmin.apimanagement.ApiManagementController;
import com.leads.microcube.verifidadmin.common.response.ApiResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(assignableTypes = ApiManagementController.class)
public class ApiManagementExceptionHandler {

  @ExceptionHandler(ApiManagementNotFoundException.class)
  public ApiResponse<Void> handleNotFound(ApiManagementNotFoundException exception) {
    return ApiResponse.failure(exception.getMessage());
  }

  @ExceptionHandler(ApiManagementValidationException.class)
  public ApiResponse<Void> handleValidation(ApiManagementValidationException exception) {
    return ApiResponse.failure(exception.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ApiResponse<Void> handleInvalidRequest(MethodArgumentNotValidException exception) {
    String message =
        exception.getBindingResult().getFieldErrors().stream()
            .findFirst()
            .map(error -> error.getField() + " " + error.getDefaultMessage())
            .orElse("Invalid request.");
    return ApiResponse.failure(message);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ApiResponse<Void> handleInvalidJson(HttpMessageNotReadableException exception) {
    return ApiResponse.failure("Invalid request.");
  }
}
