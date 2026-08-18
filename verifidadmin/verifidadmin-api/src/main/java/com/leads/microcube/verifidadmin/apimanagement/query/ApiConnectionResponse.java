package com.leads.microcube.verifidadmin.apimanagement.query;

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
public class ApiConnectionResponse {

  @JsonProperty("ApiConnId")
  private Integer apiConnId;

  @JsonProperty("ApiConnName")
  private String apiConnName;

  @JsonProperty("ApiConnKey")
  private String apiConnKey;

  @JsonProperty("ApiConnPort")
  private String apiConnPort;

  @JsonProperty("ApiConnUrl")
  private String apiConnUrl;

  @JsonProperty("ApiConnUser")
  private String apiConnUser;

  @JsonProperty("ApiConnPass")
  private String apiConnPass;

  @JsonProperty("ApiCredential")
  private String apiCredential;
}
