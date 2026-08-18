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
public class EcFieldVerificationResult {

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

  @JsonProperty("presentAddress.mouzaOrMoholla")
  private Boolean presentAddressMouzaOrMoholla;

  @JsonProperty("presentAddress.wardForUnionPorishod")
  private Boolean presentAddressWardForUnionPorishod;

  @JsonProperty("presentAddress.upozila")
  private Boolean presentAddressUpozila;

  @JsonProperty("presentAddress.division")
  private Boolean presentAddressDivision;

  @JsonProperty("presentAddress.district")
  private Boolean presentAddressDistrict;

  @JsonProperty("presentAddress.rmo")
  private Boolean presentAddressRmo;

  @JsonProperty("presentAddress.postalCode")
  private Boolean presentAddressPostalCode;

  @JsonProperty("presentAddress.region")
  private Boolean presentAddressRegion;

  @JsonProperty("presentAddress.postOffice")
  private Boolean presentAddressPostOffice;

  @JsonProperty("permanentAddress.division")
  private Boolean permanentAddressDivision;

  @JsonProperty("permanentAddress.district")
  private Boolean permanentAddressDistrict;

  @JsonProperty("permanentAddress.upozila")
  private Boolean permanentAddressUpozila;

  @JsonProperty("permanentAddress.rmo")
  private Boolean permanentAddressRmo;

  @JsonProperty("permanentAddress.postalCode")
  private Boolean permanentAddressPostalCode;

  @JsonProperty("permanentAddress.region")
  private Boolean permanentAddressRegion;

  @JsonProperty("permanentAddress.postOffice")
  private Boolean permanentAddressPostOffice;

  @JsonProperty("permanentAddress.mouzaOrMoholla")
  private Boolean permanentAddressMouzaOrMoholla;

  @JsonProperty("permanentAddress.wardForUnionPorishod")
  private Boolean permanentAddressWardForUnionPorishod;
}
