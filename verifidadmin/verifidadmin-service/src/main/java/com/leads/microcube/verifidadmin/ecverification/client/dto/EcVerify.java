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
public class EcVerify {

  @JsonProperty("name")
  private String name;

  @JsonProperty("nameEn")
  private String nameEn;

  @JsonProperty("dateOfBirth")
  private String dateOfBirth;

  @JsonProperty("father")
  private String father;

  @JsonProperty("mother")
  private String mother;

  @JsonProperty("spouse")
  private String spouse;

  @JsonProperty("permanentAddress")
  private EcAddress permanentAddress;
}
