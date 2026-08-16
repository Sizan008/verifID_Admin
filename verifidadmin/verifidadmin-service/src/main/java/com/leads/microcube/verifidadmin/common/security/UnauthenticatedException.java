package com.leads.microcube.verifidadmin.common.security;

public class UnauthenticatedException extends RuntimeException {

  public UnauthenticatedException() {
    super("Valid session required.");
  }
}
