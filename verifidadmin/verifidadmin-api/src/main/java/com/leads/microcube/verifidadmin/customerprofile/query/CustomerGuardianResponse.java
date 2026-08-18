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
public class CustomerGuardianResponse {

  @JsonProperty("TrackingNo")
  private Long trackingNo;
  @JsonProperty("GuardianNo")
  private Integer guardianNo;
  @JsonProperty("NomineeNo")
  private Integer nomineeNo;
  @JsonProperty("GuardianName")
  private String guardianName;
  @JsonProperty("GuardianIdType")
  private Short guardianIdType;
  @JsonProperty("GuardianIdNo")
  private String guardianIdNo;
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
  @JsonProperty("GuardianPhoto")
  private String guardianPhoto;
  @JsonProperty("GuardianNidFront")
  private String guardianNidFront;
  @JsonProperty("GuardianNidBack")
  private String guardianNidBack;
  @JsonProperty("GuardianCIF")
  private String guardianCif;
}
