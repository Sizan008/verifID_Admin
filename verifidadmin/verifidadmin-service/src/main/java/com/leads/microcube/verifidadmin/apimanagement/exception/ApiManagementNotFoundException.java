package com.leads.microcube.verifidadmin.apimanagement.exception;

public class ApiManagementNotFoundException extends RuntimeException {

  public ApiManagementNotFoundException(Integer apiConnId) {

    super("API connection not found: " + apiConnId);
  }
}
