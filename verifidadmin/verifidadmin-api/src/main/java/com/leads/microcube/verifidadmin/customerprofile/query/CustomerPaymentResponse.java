package com.leads.microcube.verifidadmin.customerprofile.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
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
public class CustomerPaymentResponse {

  @JsonProperty("TrackingNo") private Long trackingNo;
  @JsonProperty("CustomerId") private String customerId;
  @JsonProperty("BranchId") private String branchId;
  @JsonProperty("AccountNo") private String accountNo;
  @JsonProperty("RefaranceId") private String referenceId;
  @JsonProperty("MakeDt") private LocalDateTime makeDt;
  @JsonProperty("ResponseUrl") private String responseUrl;
  @JsonProperty("Status") private String status;
  @JsonProperty("TranDate") private LocalDateTime transactionDate;
  @JsonProperty("ValId") private String validationId;
  @JsonProperty("StoreAmount") private String storeAmount;
  @JsonProperty("Amount") private String amount;
  @JsonProperty("CardType") private String cardType;
  @JsonProperty("CardNo") private String cardNo;
  @JsonProperty("Currency") private String currency;
  @JsonProperty("BankTranId") private String bankTransactionId;
  @JsonProperty("CardIssuer") private String cardIssuer;
  @JsonProperty("CardBrand") private String cardBrand;
  @JsonProperty("CardIssuerCountry") private String cardIssuerCountry;
  @JsonProperty("CardIssuerCountryCode") private String cardIssuerCountryCode;
  @JsonProperty("CurrencyType") private String currencyType;
  @JsonProperty("CurrencyAmount") private String currencyAmount;
  @JsonProperty("RiskLevel") private String riskLevel;
  @JsonProperty("RiskTitle") private String riskTitle;
  @JsonProperty("AccountCreditAmount") private String accountCreditAmount;
  @JsonProperty("BankChange") private String bankChange;
  @JsonProperty("CbsbatchNo") private String cbsbatchNo;
  @JsonProperty("RefundStatus") private String refundStatus;
  @JsonProperty("RefundId") private String refundId;
  @JsonProperty("RefundError") private String refundError;
}
