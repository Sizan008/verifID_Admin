package com.leads.microcube.verifidadmin.ecverification.exception;

import com.leads.microcube.verifidadmin.ecverification.EcVerificationController;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(assignableTypes = EcVerificationController.class)
public class EcVerificationExceptionHandler {

  @ExceptionHandler(EcVerificationException.class)
  public ResponseEntity<String> handleEcVerification(EcVerificationException exception) {
    return ResponseEntity.badRequest().body(exception.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<String> handleInvalidRequest(MethodArgumentNotValidException exception) {
    return ResponseEntity.badRequest().body("NID or Date of Birth mismatch!");
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<String> handleInvalidJson(HttpMessageNotReadableException exception) {
    return ResponseEntity.badRequest().body("NID or Date of Birth mismatch!");
  }
}
