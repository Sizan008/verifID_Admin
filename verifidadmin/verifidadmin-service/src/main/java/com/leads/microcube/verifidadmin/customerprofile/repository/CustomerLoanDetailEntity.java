package com.leads.microcube.verifidadmin.customerprofile.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CUSTOMER_LOAN_DTLS")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CustomerLoanDetailEntity {

  @Id
  @Column(name = "SERIAL_NO", nullable = false)
  private Integer serialNo;

  @Column(name = "TRACKING_NO", nullable = false)
  private Long trackingNo;

  @Column(name = "SOURCE_OF_INCOME")
  private String sourceOfIncome;

  @Column(name = "MONTHLY_INCOME")
  private Double monthlyIncome;

  @Column(name = "MONTHLY_INSTLMNT")
  private Double monthlyInstallment;

  @Column(name = "BRANCH_ID", length = 10)
  private String branchId;

  @Column(name = "BRANCH_NAME", length = 100)
  private String branchName;

  @Column(name = "BANK_ID", length = 10)
  private String bankId;

  @Column(name = "BANK_NAME", length = 100)
  private String bankName;

  @Column(name = "LOAN_AMNT_RQUSTD")
  private Double loanAmountRequested;

  @Column(name = "LOAN_TENURE", length = 100)
  private String loanTenure;

  @Column(name = "LOAN_TYPE", length = 10)
  private String loanType;

  @Column(name = "LOAN_AMNT_OTHER_BANK")
  private Double loanAmountOtherBank;

  @Column(name = "EXISTING_LOAN_FLAG")
  private Integer existingLoanFlag;

  @Column(name = "CURR_OUTSTNDNG_AMNT")
  private Double currentOutstandingAmount;

  @Column(name = "UDF_1", length = 100)
  private String udf1;

  @Column(name = "UDF_2", length = 100)
  private String udf2;

  @Column(name = "UDF_3", length = 100)
  private String udf3;

  @Column(name = "UDF_4", length = 100)
  private String udf4;

  @Column(name = "UDF_5", length = 100)
  private String udf5;

}
