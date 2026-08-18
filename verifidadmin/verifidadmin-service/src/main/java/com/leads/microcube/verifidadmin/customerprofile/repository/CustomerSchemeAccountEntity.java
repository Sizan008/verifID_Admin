package com.leads.microcube.verifidadmin.customerprofile.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "RTL_SCHEME_AC_MAST")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CustomerSchemeAccountEntity {

  @Id
  @Column(name = "SERIAL_NO", nullable = false)
  private Integer serialNo;

  @Column(name = "TRACKING_NO", nullable = false)
  private Long trackingNo;

  @Column(name = "ACC_MATURITY_DT", length = 100)
  private String accMaturityDt;

  @Column(name = "ACC_OPEN_DT", length = 100)
  private String accountOpenDate;

  @Column(name = "ACC_STATUS", length = 100)
  private String accountStatus;

  @Column(name = "ACC_STATUS_DT", length = 100)
  private String accStatusDt;

  @Column(name = "ACC_TITLE", length = 100)
  private String accountTitle;

  @Column(name = "ACC_TYPE", length = 100)
  private String accountType;

  @Column(name = "ACCOUNT_NUMBER", length = 100)
  private String accountNumber;

  @Column(name = "AMOUNT_CCY", length = 100)
  private String amountCcy;

  @Column(name = "AMOUNT_LCY", length = 100)
  private String amountLcy;

  @Column(name = "AUTH_1ST_BY", length = 100)
  private String auth1StBy;

  @Column(name = "AUTH_1ST_DT", length = 100)
  private String auth1StDt;

  @Column(name = "AUTH_2ND_BY", length = 100)
  private String auth2NdBy;

  @Column(name = "AUTH_2ND_DT", length = 100)
  private String auth2NdDt;

  @Column(name = "AUTH_STATUS_ID", length = 100)
  private String authStatusId;

  @Column(name = "AVAIL_BALANCE")
  private BigDecimal availBalance;

  @Column(name = "BALANCE_CCY")
  private BigDecimal balanceCcy;

  @Column(name = "BALANCE_LCY")
  private BigDecimal balanceLcy;

  @Column(name = "BANK_STAF_FLAG")
  private Short bankStafFlag;

  @Column(name = "BANK_STAF_ID", length = 100)
  private String bankStafId;

  @Column(name = "BASE_DEPO_AMT")
  private BigDecimal baseDepoAmt;

  @Column(name = "BLOCKED_AMOUNT")
  private BigDecimal blockedAmount;

  @Column(name = "BONUS_AMT")
  private BigDecimal bonusAmt;

  @Column(name = "BRANCH_AUTH_FLAG", length = 100)
  private String branchAuthFlag;

  @Column(name = "BRANCH_ID", length = 100)
  private String branchId;

  @Column(name = "CHK_DIGIT", length = 100)
  private String chkDigit;

  @Column(name = "CLG_AMOUNT")
  private BigDecimal clgAmount;

  @Column(name = "CLOSING_BALANCE_CCY")
  private BigDecimal closingBalanceCcy;

  @Column(name = "CLOSING_BALANCE_LCY")
  private BigDecimal closingBalanceLcy;

  @Column(name = "CR_TRANS_FLAG")
  private Short crTransFlag;

  @Column(name = "CURR_ID", length = 100)
  private String currId;

  @Column(name = "CURR_TYPE", length = 100)
  private String currType;

  @Column(name = "CURRENT_BALANCE_CCY")
  private BigDecimal currentBalanceCcy;

  @Column(name = "CURRENT_BALANCE_LCY")
  private BigDecimal currentBalanceLcy;

  @Column(name = "CUSTOMER_ID", length = 100)
  private String customerId;

  @Column(name = "CUSTOMER_NM", length = 100)
  private String customerNm;

  @Column(name = "DECEASED_ACC_FLAG")
  private Short deceasedAccFlag;

  @Column(name = "DECEASED_DT", length = 100)
  private String deceasedDt;

  @Column(name = "DECEASED_SOURCE", length = 100)
  private String deceasedSource;

  @Column(name = "DR_TRANS_FLAG")
  private Short drTransFlag;

  @Column(name = "ERR_CODE", length = 100)
  private String errCode;

  @Column(name = "ERROR_MSG", length = 100)
  private String errorMsg;

  @Column(name = "FLOATING_AMOUNT")
  private BigDecimal floatingAmount;

  @Column(name = "FROM_ACCOUNT_NO", length = 100)
  private String fromAccountNo;

  @Column(name = "FROM_BRANCH_ID", length = 100)
  private String fromBranchId;

  @Column(name = "FUTURE_AMT")
  private BigDecimal futureAmount;

  @Column(name = "GAURDIAN_ID", length = 100)
  private String gaurdianId;

  @Column(name = "GROUP_ID", length = 100)
  private String groupId;

  @Column(name = "GROUP_NM", length = 100)
  private String groupNm;

  @Column(name = "HOLD_AMOUNT")
  private BigDecimal holdAmount;

  @Column(name = "IB_CUST_ID", length = 100)
  private String ibCustId;

  @Column(name = "IB_DATE", length = 100)
  private String ibDate;

  @Column(name = "IB_IP_ADDRESS", length = 100)
  private String ibIpAddress;

  @Column(name = "IB_LOG_SL", length = 100)
  private String ibLogSl;

  @Column(name = "IB_USER_ID", length = 100)
  private String ibUserId;

  @Column(name = "INSTL_AMT")
  private BigDecimal installmentAmount;

  @Column(name = "INSTL_AMT_PAID")
  private BigDecimal instlAmtPaid;

  @Column(name = "INT_CAL_BAL_FLAG")
  private Short intCalBalFlag;

  @Column(name = "INT_CAL_BALANCE")
  private BigDecimal intCalBalance;

  @Column(name = "INT_CAL_FLAG")
  private Short intCalFlag;

  @Column(name = "INT_CAPT_FLAG", length = 100)
  private String intCaptFlag;

  @Column(name = "INT_CAPT_FREQ", length = 100)
  private String intCaptFreq;

  @Column(name = "INT_CAPT_NET_CR")
  private BigDecimal intCaptNetCr;

  @Column(name = "INT_CAPT_YTD_CR")
  private BigDecimal intCaptYtdCr;

  @Column(name = "INT_CUR_MONTH_CR")
  private BigDecimal intCurMonthCr;

  @Column(name = "INT_FXD_CAPT_AMT")
  private BigDecimal intFxdCaptAmt;

  @Column(name = "INT_LST_MONTH_CR")
  private BigDecimal intLstMonthCr;

  @Column(name = "INT_PAYABLE_CR")
  private BigDecimal intPayableCr;

  @Column(name = "INT_RATE_ID_PRM", length = 100)
  private String intRateIdPrm;

  @Column(name = "INT_RATE_NEG")
  private BigDecimal intRateNeg;

  @Column(name = "INT_RATE_POS")
  private BigDecimal intRatePos;

  @Column(name = "INT_RATE_TOT")
  private BigDecimal intRateTot;

  @Column(name = "LAST_ACTION", length = 100)
  private String lastAction;

  @Column(name = "LIEN_ACCOUNT_NO", length = 100)
  private String lienAccountNo;

  @Column(name = "LIEN_AMOUNT")
  private BigDecimal lienAmount;

  @Column(name = "LIEN_BRANCH_ID", length = 100)
  private String lienBranchId;

  @Column(name = "LST_INST_PAY_DT", length = 100)
  private String lstInstPayDt;

  @Column(name = "LST_INT_CAL_DT_CR", length = 100)
  private String lstIntCalDtCr;

  @Column(name = "LST_INT_CAPT_AMT")
  private BigDecimal lstIntCaptAmt;

  @Column(name = "LST_INT_CAPT_DT_CR", length = 100)
  private String lstIntCaptDtCr;

  @Column(name = "LST_RENEW_DT", length = 100)
  private String lstRenewDt;

  @Column(name = "LST_STATE_GEN_DT", length = 100)
  private String lstStateGenDt;

  @Column(name = "LST_TRN_DT", length = 100)
  private String lstTrnDt;

  @Column(name = "MAIL_ADDRS", length = 100)
  private String mailAddrs;

  @Column(name = "MAKE_BY", length = 100)
  private String makeBy;

  @Column(name = "MAKE_DT", length = 100)
  private String makeDt;

  @Column(name = "MAT_PRE_ENCASH_FLAG")
  private Short matPreEncashFlag;

  @Column(name = "MAT_PST_DEPO_FLAG")
  private Short matPstDepoFlag;

  @Column(name = "MINOR_ACC_FLAG")
  private Short minorAccFlag;

  @Column(name = "NFT_LOG_MSG", length = 100)
  private String nftLogMsg;

  @Column(name = "NXT_INT_CAPT_DT_CR", length = 100)
  private String nxtIntCaptDtCr;

  @Column(name = "NXT_RENEW_DT", length = 100)
  private String nxtRenewDt;

  @Column(name = "OACCOUNT_NUMBER", length = 100)
  private String oaccountNumber;

  @Column(name = "OPENING_BALANCE_CCY")
  private BigDecimal openingBalanceCcy;

  @Column(name = "OPENING_BALANCE_LCY")
  private BigDecimal openingBalanceLcy;

  @Column(name = "PAY_IN_CHQ_BANK", length = 100)
  private String payInChqBank;

  @Column(name = "PAY_IN_CHQ_BRANCH", length = 100)
  private String payInChqBranch;

  @Column(name = "PAY_IN_CHQ_DT", length = 100)
  private String payInChqDt;

  @Column(name = "PAY_IN_CHQ_NO", length = 100)
  private String payInChqNo;

  @Column(name = "PAY_IN_FLAG")
  private Short payInFlag;

  @Column(name = "PAY_IN_TRF_ACNO", length = 100)
  private String payInTrfAcno;

  @Column(name = "PAY_IN_TRF_BR_ID", length = 100)
  private String payInTrfBrId;

  @Column(name = "PAY_OUT_ACCOUNT_NUMBER", length = 100)
  private String payOutAccountNumber;

  @Column(name = "PAY_OUT_BRANCH_ID", length = 100)
  private String payOutBranchId;

  @Column(name = "PAY_OUT_INT_ACC_NO", length = 100)
  private String payOutIntAccNo;

  @Column(name = "PAY_OUT_INT_BR_ID", length = 100)
  private String payOutIntBrId;

  @Column(name = "PAY_OUT_INT_TRN", length = 100)
  private String payOutIntTrn;

  @Column(name = "PAY_OUT_PRN_ACC_NO", length = 100)
  private String payOutPrnAccNo;

  @Column(name = "PAY_OUT_PRN_BR_ID", length = 100)
  private String payOutPrnBrId;

  @Column(name = "PAY_OUT_PRN_TRN", length = 100)
  private String payOutPrnTrn;

  @Column(name = "PEND_AUTH_CR")
  private BigDecimal pendAuthCr;

  @Column(name = "PEND_AUTH_DR")
  private BigDecimal pendAuthDr;

  @Column(name = "PNL_AMT_DUE")
  private BigDecimal pnlAmtDue;

  @Column(name = "PNL_AMT_PAID")
  private BigDecimal pnlAmtPaid;

  @Column(name = "PNSN_FURURE_AMT")
  private BigDecimal pnsnFurureAmt;

  @Column(name = "PNSN_INSTL_AMT")
  private BigDecimal pnsnInstlAmt;

  @Column(name = "PNSN_INT_RATE_ID", length = 100)
  private String pnsnIntRateId;

  @Column(name = "PNSN_INT_TOT_CR")
  private BigDecimal pnsnIntTotCr;

  @Column(name = "PNSN_MAT_FLAG")
  private Short pnsnMatFlag;

  @Column(name = "PNSN_MATURITY_DT", length = 100)
  private String pnsnMaturityDt;

  @Column(name = "PNSN_NEG_INT_CR_VARI")
  private BigDecimal pnsnNegIntCrVari;

  @Column(name = "PNSN_POS_INT_CR_VARI")
  private BigDecimal pnsnPosIntCrVari;

  @Column(name = "PNSN_TRM_FREQ", length = 100)
  private String pnsnTrmFreq;

  @Column(name = "PNSN_TRM_NO")
  private Short pnsnTrmNo;

  @Column(name = "PNSN_TRM_ON")
  private Short pnsnTrmOn;

  @Column(name = "PRINCIPAL_AMT")
  private BigDecimal principalAmount;

  @Column(name = "PROD_TYPE", length = 100)
  private String prodType;

  @Column(name = "PRODUCT_ID", length = 100)
  private String productId;

  @Column(name = "PRODUCT_NM", length = 100)
  private String productName;

  @Column(name = "PRODUCT_SHORT_NM", length = 50)
  private String productShortName;

  @Column(name = "REMOTE_TRANS_FLAG")
  private Short remoteTransFlag;

  @Column(name = "RENEW_BALANCE_FLAG")
  private Short renewBalanceFlag;

  @Column(name = "RENEW_FLAG")
  private Short renewFlag;

  @Column(name = "RENEW_INT_RT_FLAG")
  private Short renewIntRtFlag;

  @Column(name = "RENEW_MAX_NO")
  private Short renewMaxNo;

  @Column(name = "RENEW_PROD_FLAG")
  private Short renewProdFlag;

  @Column(name = "RENEW_PROD_ID", length = 100)
  private String renewProdId;

  @Column(name = "RENEW_TRM_FREQ", length = 100)
  private String renewTrmFreq;

  @Column(name = "RENEW_TRM_NO")
  private Short renewTrmNo;

  @Column(name = "RENEW_TRM_ON")
  private Short renewTrmOn;

  @Column(name = "RO_EMP_ID", length = 100)
  private String roEmpId;

  @Column(name = "RSTC_CLG_CR")
  private Short rstcClgCr;

  @Column(name = "RSTC_CSH_CR")
  private Short rstcCshCr;

  @Column(name = "RSTC_CSH_DR")
  private Short rstcCshDr;

  @Column(name = "RSTC_TRF_CR")
  private Short rstcTrfCr;

  @Column(name = "RSTC_TRF_DR")
  private Short rstcTrfDr;

  @Column(name = "SBS_DEPO_ID")
  private Short sbsDepoId;

  @Column(name = "SBS_ECO_PUR_ID")
  private Integer sbsEcoPurId;

  @Column(name = "SBS_PRIVLGE_ID")
  private Short sbsPrivlgeId;

  @Column(name = "SBS_SECTOR_ID", length = 100)
  private String sbsSectorId;

  @Column(name = "SBS_SECURITY_ID")
  private Short sbsSecurityId;

  @Column(name = "SPECIAL_INST", length = 100)
  private String specialInst;

  @Column(name = "STANDING_INSTRUCTION_FLAG", length = 100)
  private String standingInstructionFlag;

  @Column(name = "STATE_CYCLE", length = 100)
  private String stateCycle;

  @Column(name = "STND_INST_FLAG")
  private Short stndInstFlag;

  @Column(name = "SWP_IN_AMOUNT")
  private BigDecimal swpInAmount;

  @Column(name = "SWP_IN_FLAG")
  private Short swpInFlag;

  @Column(name = "TRANSFER_TYPE", length = 100)
  private String transferType;

  @Column(name = "TRM_FREQ", length = 100)
  private String termFrequency;

  @Column(name = "TRM_TOT_IRG_CONSQ")
  private Short trmTotIrgConsq;

  @Column(name = "TRM_TOT_IRG_NO")
  private Short trmTotIrgNo;

  @Column(name = "TRM_TOT_NO")
  private Short termTotalNumber;

  @Column(name = "TRM_TOT_ON")
  private Short trmTotOn;

}
