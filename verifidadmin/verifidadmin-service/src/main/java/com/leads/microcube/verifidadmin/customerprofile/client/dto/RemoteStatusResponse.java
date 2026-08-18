package com.leads.microcube.verifidadmin.customerprofile.client.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RemoteStatusResponse {

  @JsonProperty("status")
  @JsonAlias("Status")
  private String status;

  @JsonProperty("message")
  @JsonAlias("Message")
  private String message;

  @JsonProperty("result")
  @JsonAlias("Result")
  private JsonNode result;
}
