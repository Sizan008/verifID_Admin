package com.leads.microcube.verifidadmin.account;

import com.leads.microcube.verifidadmin.account.exception.AccountValidationException;
import com.leads.microcube.verifidadmin.parameterconfig.repository.AppSettingRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class AccountSettings {

  private final AppSettingRepository appSettingRepository;
  private final String authServerUrl;
  private final String centralLoginUrl;
  private final String logoutUrl;
  private final String cbsName;
  private final String spark;
  private final String applicationId;
  private final boolean licenceCheckEnabled;

  public AccountSettings(
      AppSettingRepository appSettingRepository,
      @Value("${verifidadmin.account.auth-server-url:}") String authServerUrl,
      @Value("${verifidadmin.account.central-login-url:}") String centralLoginUrl,
      @Value("${verifidadmin.account.logout-url:}") String logoutUrl,
      @Value("${verifidadmin.account.cbs-name:LEADS_CBS}") String cbsName,
      @Value("${verifidadmin.account.spark:1}") String spark,
      @Value("${verifidadmin.account.application-id:15}") String applicationId,
      @Value("${verifidadmin.account.licence-check-enabled:true}")
          boolean licenceCheckEnabled) {
    this.appSettingRepository = appSettingRepository;
    this.authServerUrl = normalizeUrl(authServerUrl);
    this.centralLoginUrl = centralLoginUrl == null ? "" : centralLoginUrl.trim();
    this.logoutUrl = logoutUrl == null ? "" : logoutUrl.trim();
    this.cbsName = cbsName == null ? "" : cbsName.trim();
    this.spark = spark == null ? "" : spark.trim();
    this.applicationId = applicationId == null ? "15" : applicationId.trim();
    this.licenceCheckEnabled = licenceCheckEnabled;
  }

  public String getLoginUrl() {
    return requireAuthServerUrl() + "/api/Security/DoLogin";
  }

  public String getLogoutApiUrl() {
    return requireAuthServerUrl() + "/api/Security/DoLogout";
  }

  public String getCentralLoginUrl() {
    return centralLoginUrl;
  }

  public String getLogoutUrl() {
    return logoutUrl;
  }

  public boolean isCentralLoginEnabled() {
    return StringUtils.hasText(centralLoginUrl) && isLeadsCbs();
  }

  public boolean isLeadsCbs() {
    return "LEADS_CBS".equalsIgnoreCase(cbsName);
  }

  public String getSpark() {
    return spark;
  }

  public String getApplicationId() {
    return applicationId;
  }

  public boolean isLicenceCheckEnabled() {
    return licenceCheckEnabled;
  }

  public String retrieveRequiredSetting(String key) {
    return appSettingRepository
        .findById(key)
        .map(setting -> setting.getValue())
        .filter(StringUtils::hasText)
        .map(String::trim)
        .orElseThrow(() -> new AccountValidationException(key + " is missing."));
  }

  public boolean isUserAutoAuthorizationEnabled() {
    return appSettingRepository
        .findById("ADMIN_USER_ADD_AUTO")
        .map(setting -> setting.getValue())
        .filter(StringUtils::hasText)
        .map(String::trim)
        .map(value -> "TRUE".equalsIgnoreCase(value))
        .orElse(false);
  }

  private String requireAuthServerUrl() {
    if (!StringUtils.hasText(authServerUrl)) {
      throw new AccountValidationException("Account authentication server URL is missing.");
    }
    return authServerUrl;
  }

  private String normalizeUrl(String value) {
    if (!StringUtils.hasText(value)) {
      return "";
    }
    String normalized = value.trim();
    while (normalized.endsWith("/")) {
      normalized = normalized.substring(0, normalized.length() - 1);
    }
    return normalized;
  }
}
