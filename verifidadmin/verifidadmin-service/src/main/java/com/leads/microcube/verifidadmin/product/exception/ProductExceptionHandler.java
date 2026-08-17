package com.leads.microcube.verifidadmin.product.exception;

import com.leads.microcube.verifidadmin.common.response.ApiResponse;
import com.leads.microcube.verifidadmin.product.ProductController;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(assignableTypes = ProductController.class)
public class ProductExceptionHandler {

  @ExceptionHandler(ProductNotFoundException.class)
  public ApiResponse<Void> handleNotFound(ProductNotFoundException exception) {
    return ApiResponse.failure(exception.getMessage());
  }

  @ExceptionHandler(ProductValidationException.class)
  public ApiResponse<Void> handleValidation(ProductValidationException exception) {
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
