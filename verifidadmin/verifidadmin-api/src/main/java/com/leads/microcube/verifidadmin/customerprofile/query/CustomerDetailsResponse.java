package com.leads.microcube.verifidadmin.customerprofile.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
public class CustomerDetailsResponse {

  @JsonProperty("TrackingNo")
  private Long trackingNo;
  @JsonProperty("TrackingStatus")
  private Short trackingStatus;
  @JsonProperty("MobileNo")
  private String mobileNo;
  @JsonProperty("Email")
  private String email;
  @JsonProperty("NidNo")
  private String nidNo;
  @JsonProperty("FullnameEN")
  private String fullNameEn;
  @JsonProperty("FullnameBN")
  private String fullNameBn;
  @JsonProperty("Birthdate")
  private String birthdate;
  @JsonProperty("Gender")
  private String gender;
  @JsonProperty("Religion")
  private Integer religion;
  @JsonProperty("Profession")
  private String profession;
  @JsonProperty("DepositPerMonth")
  private BigDecimal depositPerMonth;
  @JsonProperty("WithdrawPerMonth")
  private BigDecimal withdrawPerMonth;
  @JsonProperty("RiskGrading")
  private BigDecimal riskGrading;
  @JsonProperty("maxRiskGrading")
  private BigDecimal maximumRiskGrading;
  @JsonProperty("MotherNameEN")
  private String motherNameEn;
  @JsonProperty("MotherNameBN")
  private String motherNameBn;
  @JsonProperty("FatherNameEN")
  private String fatherNameEn;
  @JsonProperty("FatherNameBN")
  private String fatherNameBn;
  @JsonProperty("SpouseName")
  private String spouseName;
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
  @JsonProperty("BranchId")
  private String branchId;
  @JsonProperty("BranchName")
  private String branchName;
  @JsonProperty("ProductId")
  private String productId;
  @JsonProperty("ProductName")
  private String productName;
  @JsonProperty("ProductTypeId")
  private String productTypeId;
  @JsonProperty("ProductTypeName")
  private String productTypeName;
  @JsonProperty("ProductCount")
  private int productCount;
  @JsonProperty("CustomerId")
  private String customerId;
  @JsonProperty("AccountNo")
  private String accountNo;
  @JsonProperty("FaceMatchScoreCard")
  private BigDecimal faceMatchScoreCard;
  @JsonProperty("AuthStatus")
  private String authStatus;
  @JsonProperty("AuthBy")
  private String authByDisplay;
  @JsonProperty("CheckBoxValue")
  private boolean checkBoxValue;
  @JsonProperty("Remark")
  private String remark;
  @JsonProperty("isActive")
  private boolean active;
  @JsonProperty("RequestChannel")
  private String requestChannel;
  @JsonProperty("Edd_Check")
  private Integer eddCheck;
  @JsonProperty("DeclineReason")
  private String declineReason;
  @JsonProperty("CustPhoto")
  private String customerPhoto;
  @JsonProperty("NidFront")
  private String nidFront;
  @JsonProperty("NidBack")
  private String nidBack;
  @JsonProperty("PorichoyPhoto")
  private String porichoyPhoto;
  @JsonProperty("NidPhoto")
  private String nidPhoto;
  @JsonProperty("SignPhoto")
  private String signaturePhoto;
  @JsonProperty("SacntionScreening")
  private String sanctionScreening;
  @JsonProperty("custEkycType")
  private String customerEkycType;
  @JsonProperty("checkBy")
  private String checkBy;
  @JsonProperty("checkDate")
  private String checkDate;
  @JsonProperty("authBy")
  private String authBy;
  @JsonProperty("authDate")
  private String authDate;
  @JsonProperty("SmsAlertFlag")
  private Integer smsAlertFlag;
  @JsonProperty("EmailAlertFlag")
  private Integer emailAlertFlag;
  @JsonProperty("ChqBookFlag")
  private Integer chequeBookFlag;
  @JsonProperty("DebitCardFlag")
  private Integer debitCardFlag;
  @JsonProperty("FatkaChecked")
  private boolean fatkaChecked;
  @JsonProperty("UpdateBy")
  private String updateBy;
  @JsonProperty("rmcode")
  private String rmCode;
  @JsonProperty("AccountStatus")
  private String accountStatus;
  @JsonProperty("ReferenceNo")
  private Long referenceNo;
  @JsonProperty("AuthPermission")
  private boolean authPermission;
  @JsonProperty("PendingBranchAuthorization")
  private boolean pendingBranchAuthorization;
  @JsonProperty("NomineeCount")
  private long nomineeCount;
  @JsonProperty("GuardianCount")
  private long guardianCount;
  @JsonProperty("BeneficiaryCount")
  private long beneficiaryCount;
  @JsonProperty("DocumentCount")
  private long documentCount;
  @JsonProperty("SSLPayment")
  private boolean sslPaymentEnabled;
  @JsonProperty("DebitRestriction")
  private CustomerActionResponse debitRestriction;
  @JsonProperty("Photos")
  private CustomerPhotosResponse photos;
  @JsonProperty("BankShNm")
  private String bankShortName;
  @JsonProperty("RISK_GRADING_DETAILS")
  private String riskGradingDetails;
  @JsonProperty("EDD_DETAILS")
  private String eddDetails;
  @JsonProperty("ShowAlerts")
  private String showAlerts;
  @JsonProperty("ShowAlertsAllChannels")
  private String showAlertsAllChannels;
  @JsonProperty("ShowRiskGradeAllEKYC")
  private String showRiskGradeAllEkyc;
  @JsonProperty("ReturnThisCust2PreviousStep")
  private String returnThisCustomerToPreviousStep;
  @Builder.Default
  @JsonProperty("GuardianList")
  private List<CustomerGuardianResponse> guardianList = new ArrayList<>();
  @Builder.Default
  @JsonProperty("JointPartnerList")
  private List<CustomerDetailsResponse> jointPartners = new ArrayList<>();
}
