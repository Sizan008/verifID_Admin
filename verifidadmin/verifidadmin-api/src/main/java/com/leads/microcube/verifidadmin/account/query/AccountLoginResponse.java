package com.leads.microcube.verifidadmin.account.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.leads.microcube.verifidadmin.common.security.CurrentUser;
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
public class AccountLoginResponse {

  @JsonProperty("CurrentUser")
  private CurrentUser currentUser;
}
