package com.leads.microcube.verifidadmin.ecverification;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class EcVerificationSettings {

  private final String apiUrl;

  public EcVerificationSettings(
      @Value("${verifidadmin.ec-verification.api-url:}") String apiUrl) {
    this.apiUrl = apiUrl;
  }

  public String getApiUrl() {
    if (!StringUtils.hasText(apiUrl)) {
      throw new IllegalStateException("EC verification API URL is not configured.");
    }
    return apiUrl.trim();
  }
}
