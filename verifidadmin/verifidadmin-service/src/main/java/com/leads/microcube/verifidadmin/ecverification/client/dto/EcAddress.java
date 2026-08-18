package com.leads.microcube.verifidadmin.ecverification.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EcAddress {

  @JsonProperty("division")
  private String division;

  @JsonProperty("district")
  private String district;

  @JsonProperty("upozila")
  private String upozila;

  @JsonProperty("postOffice")
  private String postOffice;

  @JsonProperty("postalCode")
  private String postalCode;
}
