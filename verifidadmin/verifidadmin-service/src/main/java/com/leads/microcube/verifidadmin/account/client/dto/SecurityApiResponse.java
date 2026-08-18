package com.leads.microcube.verifidadmin.account.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class SecurityApiResponse {

  @JsonProperty("ResponseStatus")
  private boolean responseStatus;

  @JsonProperty("ResponseMessage")
  private String responseMessage;

  @JsonProperty("ResponseBusinessData")
  private String responseBusinessData;
}
