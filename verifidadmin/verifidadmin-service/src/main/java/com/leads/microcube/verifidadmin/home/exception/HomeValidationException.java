package com.leads.microcube.verifidadmin.home.exception;

public class HomeValidationException extends RuntimeException {

  public HomeValidationException(String message) {
    super(message);
  }

  public HomeValidationException(String message, Throwable cause) {
    super(message, cause);
  }
}
