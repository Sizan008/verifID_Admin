package com.leads.microcube.verifidadmin.customerprofile.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CUSTOMER_SSL_COMMERZ_PAYMENT")
@IdClass(CustomerSslCommerzPaymentId.class)
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CustomerSslCommerzPaymentEntity {

  @Id
  @Column(name = "TRACKING_NO", nullable = false)
  private Long trackingNo;

  @Column(name = "CUSTOMER_ID", nullable = false)
  private String customerId;

  @Column(name = "BRANCH_ID", nullable = false)
  private String branchId;

  @Column(name = "ACCOUNT_NO", nullable = false)
  private String accountNo;

  @Id
  @Column(name = "REFARANCE_ID", nullable = false)
  private String referenceId;

  @Column(name = "MAKE_DT", nullable = false)
  private LocalDateTime makeDt;

  @Column(name = "RESPONSE_URL")
  private String responseUrl;

  @Column(name = "STATUS")
  private String status;

  @Column(name = "TRAN_DATE")
  private LocalDateTime tranDate;

  @Column(name = "VAL_ID")
  private String valId;

  @Column(name = "STORE_AMOUNT")
  private String storeAmount;

  @Column(name = "AMOUNT")
  private String amount;

  @Column(name = "CARD_TYPE")
  private String cardType;

  @Column(name = "CARD_NO")
  private String cardNo;

  @Column(name = "CURRENCY")
  private String currency;

  @Column(name = "BANK_TRAN_ID")
  private String bankTranId;

  @Column(name = "CARD_ISSUER")
  private String cardIssuer;

  @Column(name = "CARD_BRAND")
  private String cardBrand;

  @Column(name = "CARD_ISSUER_COUNTRY")
  private String cardIssuerCountry;

  @Column(name = "CARD_ISSUER_COUNTRY_CODE")
  private String cardIssuerCountryCode;

  @Column(name = "CURRENCY_TYPE")
  private String currencyType;

  @Column(name = "CURRENCY_AMOUNT")
  private String currencyAmount;

  @Column(name = "RISK_LEVEL")
  private String riskLevel;

  @Column(name = "RISK_TITLE")
  private String riskTitle;

  @Column(name = "ACCOUNT_CREDIT_AMOUNT")
  private String accountCreditAmount;

  @Column(name = "BANK_CHARGE")
  private String bankChange;

  @Column(name = "CBS_BATCH_NO")
  private String cbsbatchNo;

  @Column(name = "REFUND_STATUS")
  private String refundStatus;

  @Column(name = "REFUND_ID")
  private String refundId;

  @Column(name = "REFUND_ERROR")
  private String refundError;

}
