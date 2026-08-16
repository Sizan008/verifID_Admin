package com.leads.microcube.verifidadmin.common.security;

public class FeatureAccessDeniedException extends RuntimeException {

  public FeatureAccessDeniedException() {
    super("Access denied.");
  }
}
