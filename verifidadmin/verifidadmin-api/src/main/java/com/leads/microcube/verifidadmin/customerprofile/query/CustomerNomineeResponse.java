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
public class CustomerNomineeResponse {

  @JsonProperty("TrackingNo")
  private Long trackingNo;
  @JsonProperty("NomineeNo")
  private Integer nomineeNo;
  @JsonProperty("NomineeName")
  private String nomineeName;
  @JsonProperty("NomineeIdType")
  private Short nomineeIdType;
  @JsonProperty("NomineeIdNo")
  private String nomineeIdNo;
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
  @JsonProperty("NomineePhoto")
  private String nomineePhoto;
  @JsonProperty("NomineeNidFront")
  private String nomineeNidFront;
  @JsonProperty("NomineeNidBack")
  private String nomineeNidBack;
  @JsonProperty("NomineeCIF")
  private String nomineeCif;
}
