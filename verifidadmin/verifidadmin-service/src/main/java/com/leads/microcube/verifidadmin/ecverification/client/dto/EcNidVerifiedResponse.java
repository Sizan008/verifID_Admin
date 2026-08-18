package com.leads.microcube.verifidadmin.ecverification.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EcNidVerifiedResponse {

  @JsonProperty("message")
  private String message;

  @JsonProperty("status")
  private String status;

  @JsonProperty("statusCode")
  private String statusCode;

  @JsonProperty("Success")
  private EcNidVerifySuccess success;

  @JsonProperty("verified")
  private Boolean verified;

  @JsonProperty("fieldVerificationResult")
  private EcFieldVerificationResult fieldVerificationResult;
}
