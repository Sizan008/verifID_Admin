package com.leads.microcube.verifidadmin.customerprofile.repository;

import java.time.LocalDate;

/** Read projection for the legacy NOMINEE_GUARDIAN table. */
public interface NomineeGuardianProjection {

  Long getTrackingNo();

  Integer getNomineeNo();

  Integer getGuardianNo();

  String getGuardianName();

  Short getGuardianIdType();

  String getGuardianIdNo();

  LocalDate getBirthdate();

  String getGender();

  Integer getReligion();

  String getRelation();

  Short getAge();

  Double getSharePercent();

  String getMotherNameEn();

  String getMotherNameBn();

  String getFatherNameEn();

  String getFatherNameBn();

  String getPresentAddressEn();

  String getPresentAddressBn();

  String getPermanentAddress();

  String getCountry();

  Integer getDivision();

  Integer getDistrict();

  Integer getSubDistrict();

  Integer getThana();

  String getZipCode();

  String getOtherInfo();

  String getStatus();
}
