package com.leads.microcube.verifidadmin.customerprofile.query;

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
public class CustomerBeneficiaryResponse {

  @JsonProperty("TrackingNo")
  private Long trackingNo;
  @JsonProperty("BenifNo")
  private Integer beneficiaryNo;
  @JsonProperty("BenifName")
  private String beneficiaryName;
  @JsonProperty("BenifIdType")
  private Short beneficiaryIdType;
  @JsonProperty("BenifIdNo")
  private String beneficiaryIdNo;
  @JsonProperty("Birthdate")
  private String birthdate;
  @JsonProperty("Gender")
  private String gender;
  @JsonProperty("Religion")
  private Integer religion;
  @JsonProperty("Relation")
  private String relation;
  @JsonProperty("Age")
  private Short age;
  @JsonProperty("SharePercent")
  private Double sharePercent;
  @JsonProperty("MotherNameEN")
  private String motherNameEn;
  @JsonProperty("MotherNameBN")
  private String motherNameBn;
  @JsonProperty("FatherNameEN")
  private String fatherNameEn;
  @JsonProperty("FatherNameBN")
  private String fatherNameBn;
  @JsonProperty("PresentAddressEN")
  private String presentAddressEn;
  @JsonProperty("PresentAddressBN")
  private String presentAddressBn;
  @JsonProperty("PermanentAddress")
  private String permanentAddress;
  @JsonProperty("Country")
  private String country;
  @JsonProperty("Division")
  private Integer division;
  @JsonProperty("District")
  private Integer district;
  @JsonProperty("SubDistrict")
  private Integer subDistrict;
  @JsonProperty("Thana")
  private Integer thana;
  @JsonProperty("ZipCode")
  private String zipCode;
  @JsonProperty("OtherInfo")
  private String otherInfo;
  @JsonProperty("Status")
  private String status;
  @JsonProperty("benifIdPhoto")
  private String beneficiaryIdPhoto;
  @JsonProperty("benifIdFront")
  private String beneficiaryIdFront;
  @JsonProperty("benifIdBack")
  private String beneficiaryIdBack;
  @JsonProperty("NomineeCIF")
  private String nomineeCif;
}
