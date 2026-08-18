package com.leads.microcube.verifidadmin.assistedekyc.exception;

import com.leads.microcube.verifidadmin.assistedekyc.AssistedEkycController;
import com.leads.microcube.verifidadmin.common.response.ApiResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(assignableTypes = AssistedEkycController.class)
public class AssistedEkycExceptionHandler {

  @ExceptionHandler(AssistedEkycConfigurationException.class)
  public ResponseEntity<ApiResponse<Void>> handleConfiguration(
      AssistedEkycConfigurationException exception) {
    return ResponseEntity.ok(ApiResponse.failure(exception.getMessage()));
  }
}
