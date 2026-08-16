package com.leads.microcube.verifidadmin.common.exception;

import com.leads.microcube.verifidadmin.common.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@Order(Ordered.LOWEST_PRECEDENCE - 1)
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(DataAccessException.class)
  public ResponseEntity<ApiResponse<Void>> handleDatabaseException(
      DataAccessException exception) {
    log.error("Database operation failed.", exception);
    return failure("Database operation failed. Please try again later.");
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ApiResponse<Void>> handleInvalidParameter(
      MethodArgumentTypeMismatchException exception) {
    return failure("Invalid value for parameter: " + exception.getName());
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ApiResponse<Void>> handleUnreadableBody(
      HttpMessageNotReadableException exception) {
    return failure("Invalid or missing JSON request body.");
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<ApiResponse<Void>> handleMissingParameter(
      MissingServletRequestParameterException exception) {
    return failure("Required parameter is missing: " + exception.getParameterName());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<Void>> handleMethodValidation(
      MethodArgumentNotValidException exception) {
    String message =
        exception.getBindingResult().getFieldErrors().stream()
            .findFirst()
            .map(error -> error.getField() + " " + error.getDefaultMessage())
            .orElse("Invalid request.");
    return failure(message);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<Void>> handleUnexpectedException(
      Exception exception) {
    log.error("Unexpected application error.", exception);
    return failure("Something went wrong. Please try again later.");
  }

  private ResponseEntity<ApiResponse<Void>> failure(String message) {
    return ResponseEntity.ok(ApiResponse.failure(message));
  }
}
