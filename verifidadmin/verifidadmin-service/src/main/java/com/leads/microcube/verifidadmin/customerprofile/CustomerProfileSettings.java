package com.leads.microcube.verifidadmin.customerprofile;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CustomerProfileSettings {

  private final String apiBaseUrl;
  private final String mlBaseUrl;
  private final String reportBaseUrl;
  private final String bankShortName;
  private final String cbsName;
  private final boolean imageProcessInternal;

  public CustomerProfileSettings(
      @Value("${verifidadmin.customer-profile.api-base-url:}") String apiBaseUrl,
      @Value("${verifidadmin.customer-profile.ml-base-url:}") String mlBaseUrl,
      @Value("${verifidadmin.customer-profile.report-base-url:}") String reportBaseUrl,
      @Value("${verifidadmin.customer-profile.bank-short-name:}") String bankShortName,
      @Value("${verifidadmin.account.cbs-name:}") String cbsName,
      @Value("${verifidadmin.customer-profile.image-process-internal:false}")
          boolean imageProcessInternal) {
    this.apiBaseUrl = apiBaseUrl;
    this.mlBaseUrl = mlBaseUrl;
    this.reportBaseUrl = reportBaseUrl;
    this.bankShortName = bankShortName;
    this.cbsName = cbsName;
    this.imageProcessInternal = imageProcessInternal;
  }

  public String getApiBaseUrl() {
    return requireUrl(apiBaseUrl, "VerifID API base URL");
  }

  public String getMlBaseUrl() {
    return requireUrl(mlBaseUrl, "VerifID ML base URL");
  }

  public String getReportBaseUrl() {
    return requireUrl(reportBaseUrl, "EKYC report base URL");
  }

  public String getBankShortName() {
    return bankShortName == null ? "" : bankShortName.trim();
  }

  public boolean isImageProcessInternal() {
    return imageProcessInternal;
  }

  public boolean isOtherCbs() {
    return "OTHER_CBS".equalsIgnoreCase(cbsName == null ? "" : cbsName.trim());
  }

  private String requireUrl(String value, String name) {
    if (!StringUtils.hasText(value)) {
      throw new IllegalStateException(name + " is not configured.");
    }
    String normalized = value.trim();
    return normalized.endsWith("/") ? normalized : normalized + "/";
  }
}
