package com.leads.microcube.verifidadmin.customerprofile.exception;

public class CustomerProfileValidationException extends RuntimeException {

  public CustomerProfileValidationException(String message) {
    super(message);
  }

  public CustomerProfileValidationException(String message, Throwable cause) {
    super(message, cause);
  }
}
