package com.leads.microcube.verifidadmin.parameterconfig.exception;

/** Raised when a requested parameter configuration does not exist. */
public class ParameterConfigNotFoundException extends RuntimeException {

  public ParameterConfigNotFoundException(String key) {

    super("Parameter configuration not found: " + key);
  }
}
