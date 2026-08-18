package com.leads.microcube.verifidadmin.ecverification.query;

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
public class FieldVerificationResponse {

  @JsonProperty("nationalId")
  private Boolean nationalId;

  @JsonProperty("dateOfBirth")
  private Boolean dateOfBirth;

  @JsonProperty("name")
  private Boolean name;

  @JsonProperty("nameEn")
  private Boolean nameEn;

  @JsonProperty("father")
  private Boolean father;

  @JsonProperty("mother")
  private Boolean mother;

  @JsonProperty("spouse")
  private Boolean spouse;

  @JsonProperty("presentAddressMouzaOrMoholla")
  private Boolean presentAddressMouzaOrMoholla;

  @JsonProperty("presentAddressWardForUnionPorishod")
  private Boolean presentAddressWardForUnionPorishod;

  @JsonProperty("presentAddressUpozila")
  private Boolean presentAddressUpozila;

  @JsonProperty("presentAddressDivision")
  private Boolean presentAddressDivision;

  @JsonProperty("presentAddressDistrict")
  private Boolean presentAddressDistrict;

  @JsonProperty("presentAddressRmo")
  private Boolean presentAddressRmo;

  @JsonProperty("presentAddressPostalCode")
  private Boolean presentAddressPostalCode;

  @JsonProperty("presentAddressRegion")
  private Boolean presentAddressRegion;

  @JsonProperty("presentAddressPostOffice")
  private Boolean presentAddressPostOffice;

  @JsonProperty("permanentAddressDivision")
  private Boolean permanentAddressDivision;

  @JsonProperty("permanentAddressDistrict")
  private Boolean permanentAddressDistrict;

  @JsonProperty("permanentAddressUpozila")
  private Boolean permanentAddressUpozila;

  @JsonProperty("permanentAddressRmo")
  private Boolean permanentAddressRmo;

  @JsonProperty("permanentAddressPostalCode")
  private Boolean permanentAddressPostalCode;

  @JsonProperty("permanentAddressRegion")
  private Boolean permanentAddressRegion;

  @JsonProperty("permanentAddressPostOffice")
  private Boolean permanentAddressPostOffice;

  @JsonProperty("permanentAddressMouzaOrMoholla")
  private Boolean permanentAddressMouzaOrMoholla;

  @JsonProperty("permanentAddressWardForUnionPorishod")
  private Boolean permanentAddressWardForUnionPorishod;
}
