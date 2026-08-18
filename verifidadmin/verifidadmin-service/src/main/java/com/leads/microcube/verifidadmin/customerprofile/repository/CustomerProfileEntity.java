package com.leads.microcube.verifidadmin.customerprofile.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CUSTOMER_PROFILES")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CustomerProfileEntity {

  @Id
  @Column(name = "TRACKING_NO", nullable = false)
  private Long trackingNo;

  @Column(name = "TRACKING_STATUS")
  private Short trackingStatus;

  @Column(name = "EKYC_FLAG")
  private Integer ekycFlag;

  @Column(name = "MOBILENO", length = 20)
  private String mobileNo;

  @Column(name = "EMAIL", length = 100)
  private String email;

  @Column(name = "NID_TYPE")
  private Short nidType;

  @Column(name = "NID_NO", length = 20)
  private String nidNo;

  @Column(name = "NID_NO_PORICHOY", length = 20)
  private String nidNoPorichoy;

  @Column(name = "FULLNAMEEN", length = 100)
  private String fullNameEn;

  @Column(name = "FULLNAMEBN", length = 100)
  private String fullNameBn;

  @Column(name = "OTPSMS", length = 20)
  private String otpSms;

  @Column(name = "OTPEMAIL", length = 20)
  private String otpEmail;

  @Column(name = "BIRTHDATE")
  private LocalDate birthdate;

  @Column(name = "GENDER", length = 1)
  private String gender;

  @Column(name = "RELIGION")
  private Integer religion;

  @Column(name = "PROFESSION", length = 100)
  private String profession;

  @Column(name = "NAME_OF_ORG", length = 105)
  private String nameOfOrg;

  @Column(name = "DESIGNATION", length = 105)
  private String designation;

  @Column(name = "DEPOSITPERMONTH")
  private BigDecimal depositPerMonth;

  @Column(name = "WITHDRAWPERMONTH")
  private BigDecimal withdrawPerMonth;

  @Column(name = "SOURCE_OF_FUND", length = 200)
  private String sourceOfFund;

  @Column(name = "TIN", length = 20)
  private String tin;

  @Column(name = "RISKGRADING")
  private BigDecimal riskGrading;

  @Column(name = "MOTHERNAMEEN", length = 100)
  private String motherNameEn;

  @Column(name = "MOTHERNAMEBN", length = 100)
  private String motherNameBn;

  @Column(name = "FATHERNAMEEN", length = 100)
  private String fatherNameEn;

  @Column(name = "FATHERNAMEBN", length = 100)
  private String fatherNameBn;

  @Column(name = "SPOUSENAME", length = 100)
  private String spouseName;

  @Column(name = "PRESENTADDRESSEN", length = 315)
  private String presentAddressEn;

  @Column(name = "PRESENTADDRESSBN", length = 315)
  private String presentAddressBn;

  @Column(name = "PERMANENTADDRESS", length = 210)
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

  @Column(name = "SDN_FLAG")
  private Short sdnFlag;

  @Column(name = "BRANCH_ID", length = 10)
  private String branchId;

  @Column(name = "PRODUCT_ID", length = 10)
  private String productId;

  @Column(name = "CUSTOMER_ID", length = 10)
  private String customerId;

  @Column(name = "ACCOUNT_NO", length = 20)
  private String accountNo;

  @Column(name = "ACCOUNT_STATUS", length = 1)
  private String accountStatus;

  @Column(name = "FACEMATCHSCORE_CARD")
  private BigDecimal faceMatchScoreCard;

  @Column(name = "FACEMATCHSCORE_RPA")
  private BigDecimal faceMatchScoreRpa;

  @Column(name = "FATKA_CHEKED")
  private Integer fatkaCheked;

  @Column(name = "FATKA_US_RESIDENT")
  private Integer fatkaUsResident;

  @Column(name = "FATKA_US_CITIZEN")
  private Integer fatkaUsCitizen;

  @Column(name = "FATKA_GREEN_CARD")
  private Integer fatkaGreenCard;

  @Column(name = "LATITUDE")
  private BigDecimal latitude;

  @Column(name = "LONGITUDE")
  private BigDecimal longitude;

  @Column(name = "ALTITUDE")
  private BigDecimal altitude;

  @Column(name = "NETWORTH_AMT_ID")
  private Integer netWorthAmountId;

  @Column(name = "DEBIT_CARD_FLAG")
  private Integer debitCardFlag;

  @Column(name = "SMS_ALART_FLAG")
  private Integer smsAlertFlag;

  @Column(name = "EMAIL_ALART_FLAG")
  private Integer emailAlertFlag;

  @Column(name = "ESTATEMENT_FLAG")
  private Integer eStatementFlag;

  @Column(name = "CHQ_BOOK_FLAG")
  private Integer chequeBookFlag;

  @Column(name = "POSITIVE_PAY_FLAG")
  private Integer positivePayFlag;

  @Column(name = "EDD_CHECK")
  private Integer eddCheck;

  @Column(name = "MAKEBY", nullable = false, length = 30)
  private String makeBy;

  @Column(name = "MAKEDT", nullable = false)
  private LocalDateTime makeDt;

  @Column(name = "CHECKBY", length = 30)
  private String checkBy;

  @Column(name = "CHECKDT")
  private LocalDateTime checkDt;

  @Column(name = "VERIFYBY", length = 30)
  private String verifyBy;

  @Column(name = "VERIFYDT")
  private LocalDateTime verifyDt;

  @Column(name = "AUTHBY", length = 30)
  private String authBy;

  @Column(name = "AUTHDT")
  private LocalDateTime authDt;

  @Column(name = "UPDATEBY", length = 30)
  private String updateBy;

  @Column(name = "UPDATEDT")
  private LocalDateTime updateDt;

  @Column(name = "AUTHSTATUS", length = 1)
  private String authStatus;

  @Column(name = "DECLINE_REASON")
  private String declineReason;

  @Column(name = "REFERENCE_NO")
  private Long referenceNo;

  @Column(name = "GROUP_ID", length = 20)
  private String groupId;

  @Column(name = "MONTHLY_INCOME")
  private BigDecimal monthlyIncome;

  @Column(name = "ZIP", length = 20)
  private String zip;

}
