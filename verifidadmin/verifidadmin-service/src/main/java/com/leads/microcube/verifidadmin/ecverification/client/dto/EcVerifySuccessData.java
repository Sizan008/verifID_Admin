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
public class EcVerifySuccessData {

  @JsonProperty("nationalId")
  private String nationalId;

  @JsonProperty("pin")
  private String pin;

  @JsonProperty("photo")
  private String photo;
}
