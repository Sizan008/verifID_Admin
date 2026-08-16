package com.leads.microcube.verifidadmin.parameterconfig.exception;

/** Raised when parameter configuration input is invalid. */
public class ParameterConfigValidationException extends RuntimeException {

  public ParameterConfigValidationException(String message) {

    super(message);
  }
}
