package com.leads.microcube.verifidadmin.customerprofile.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
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
public class CustomerListItem {

  @JsonProperty("Fullname")
  private String fullName;

  @JsonProperty("MotherNameEN")
  private String motherNameEn;

  @JsonProperty("FatherNameEN")
  private String fatherNameEn;

  @JsonProperty("Religion")
  private Integer religion;

  @JsonProperty("Email")
  private String email;

  @JsonProperty("MobileNo")
  private String mobileNo;

  @JsonProperty("NidNo")
  private String nidNo;

  @JsonProperty("DOB")
  private String dateOfBirth;

  @JsonProperty("Gender")
  private String gender;

  @JsonProperty("Branch")
  private String branch;

  @JsonProperty("Product")
  private String product;

  @JsonProperty("Profession")
  private String profession;

  @JsonProperty("AuthStatus")
  private String authStatus;

  @JsonProperty("TrackingNo")
  private Long trackingNo;

  @JsonProperty("TrackingNoStr")
  private String trackingNoText;

  @JsonProperty("ImageBase64")
  private String imageBase64;

  @JsonProperty("SearchTerm")
  private String searchTerm;

  @JsonProperty("FaceMatchScore")
  private BigDecimal faceMatchScore;

  @JsonProperty("RiskScore")
  private BigDecimal riskScore;

  @JsonProperty("MakeDate")
  private String makeDate;

  @JsonProperty("MakeBy")
  private String makeBy;

  @JsonProperty("AuthBy")
  private String authBy;

  @JsonProperty("AuthDate")
  private String authDate;

  @JsonProperty("DeclineReason")
  private String declineReason;

  @JsonProperty("DeclineReasonTrimmed")
  private String declineReasonTrimmed;

  @JsonProperty("TrackingStatus")
  private String trackingStatus;

  @JsonProperty("AccountNo")
  private String accountNo;

  @JsonProperty("CustomerId")
  private String customerId;

  @JsonProperty("NomineeName1")
  private String nomineeName1;

  @JsonProperty("NomineeName2")
  private String nomineeName2;

  @JsonProperty("PermanentAddress")
  private String permanentAddress;

  @JsonProperty("custEkycType")
  private String customerEkycType;

  @JsonProperty("source_of_fund")
  private String sourceOfFund;

  @JsonProperty("PRODUCT_NM")
  private String productName;

  @JsonProperty("Tenure")
  private String tenure;

  @JsonProperty("TRM_FREQ")
  private String termFrequency;

  @JsonProperty("TRM_TOT_NO")
  private String totalTermNumber;

  @JsonProperty("FUTURE_AMT")
  private String futureAmount;

  @JsonProperty("INSTL_AMT")
  private String installmentAmount;

  @JsonProperty("MATURITY_AMT")
  private String maturityAmount;

  @JsonProperty("PRINCIPAL_AMT")
  private String principalAmount;

  @JsonProperty("ACC_OPEN_DT")
  private String accountOpenDate;

  @JsonProperty("ACC_MATURITY_DT")
  private String accountMaturityDate;
}
