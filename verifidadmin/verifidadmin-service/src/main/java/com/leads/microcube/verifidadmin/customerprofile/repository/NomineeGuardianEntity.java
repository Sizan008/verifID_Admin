package com.leads.microcube.verifidadmin.customerprofile.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "NOMINEE_GUARDIAN")
@IdClass(NomineeGuardianId.class)
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class NomineeGuardianEntity {

  @Id
  @Column(name = "TRACKING_NO", nullable = false)
  private Long trackingNo;

  @Id
  @Column(name = "REFERENCE_NO", nullable = false)
  private Integer nomineeNo;

  @Id
  @Column(name = "GUARDIAN_NO", nullable = false)
  private Integer guardianNo;

  @Column(name = "GUARDIAN_NAME", length = 100)
  private String guardianName;

  @Column(name = "GUARDIAN_ID_TYPE")
  private Short guardianIdType;

  @Column(name = "\"Guardian_ID_NO\"", length = 20)
  private String guardianIdNo;

  @Column(name = "BIRTHDATE")
  private LocalDate birthdate;

  @Column(name = "GENDER", length = 1)
  private String gender;

  @Column(name = "RELIGION")
  private Integer religion;

  @Column(name = "RELATION", length = 100)
  private String relation;

  @Column(name = "AGE")
  private Short age;

  @Column(name = "SHAREPERCENT", nullable = false)
  private Double sharePercent;

  @Column(name = "MOTHERNAMEEN", length = 100)
  private String motherNameEn;

  @Column(name = "MOTHERNAMEBN", length = 100)
  private String motherNameBn;

  @Column(name = "FATHERNAMEEN", length = 100)
  private String fatherNameEn;

  @Column(name = "FATHERNAMEBN", length = 100)
  private String fatherNameBn;

  @Column(name = "PRESENTADDRESSEN", length = 100)
  private String presentAddressEn;

  @Column(name = "PRESENTADDRESSBN", length = 100)
  private String presentAddressBn;

  @Column(name = "PERMANENTADDRESS", length = 100)
  private String permanentAddress;

  @Column(name = "COUNTRY", length = 3)
  private String country;

  @Column(name = "DIVISION")
  private Integer division;

  @Column(name = "DISTRICT")
  private Integer district;

  @Column(name = "SUBDISTRICT")
  private Integer subDistrict;

  @Column(name = "THANA")
  private Integer thana;

  @Column(name = "ZIP_CODE", length = 10)
  private String zipCode;

  @Column(name = "OTHER_INFO", length = 100)
  private String otherInfo;

  @Column(name = "STATUS", length = 1)
  private String status;

}
