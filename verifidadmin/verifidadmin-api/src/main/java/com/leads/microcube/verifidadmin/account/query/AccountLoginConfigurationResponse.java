package com.leads.microcube.verifidadmin.account.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountLoginConfigurationResponse {

  @JsonProperty("CentralLoginEnabled")
  private boolean centralLoginEnabled;

  @JsonProperty("CentralLoginUrl")
  private String centralLoginUrl;

  @JsonProperty("LogoutUrl")
  private String logoutUrl;
}
