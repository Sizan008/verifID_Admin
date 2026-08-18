package com.leads.microcube.verifidadmin.ecverification.exception;

public class EcVerificationException extends RuntimeException {

  public EcVerificationException(String message) {
    super(message);
  }

  public EcVerificationException(String message, Throwable cause) {
    super(message, cause);
  }
}
