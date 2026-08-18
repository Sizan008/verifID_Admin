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
@Table(name = "RTL_TIME_AC_MAST")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CustomerTimeAccountEntity {

  @Id
  @Column(name = "SERIAL_NO", nullable = false)
  private Integer serialNo;

  @Column(name = "TRACKING_NO", nullable = false)
  private Long trackingNo;

  @Column(name = "ACC_OPEN_DT", length = 100)
  private String accountOpenDate;

  @Column(name = "ACC_RENEWAL_DT", length = 100)
  private String accountRenewalDate;

  @Column(name = "ACC_STATUS", length = 100)
  private String accountStatus;

  @Column(name = "ACC_TITLE", length = 100)
  private String accountTitle;

  @Column(name = "ACC_TYPE", length = 100)
  private String accountType;

  @Column(name = "ACCOUNT_NUMBER", length = 100)
  private String accountNumber;

  @Column(name = "ACCUMULATED_INTEREST_CR")
  private BigDecimal accumulatedInterestCr;

  @Column(name = "AFT_MAT_DEPO")
  private Integer aftMatDepo;

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

  @Column(name = "AUTO_RENEW_FIXED_PRIN")
  private Integer autoRenewFixedPrin;

  @Column(name = "AVAIL_BALANCE")
  private BigDecimal availBalance;

  @Column(name = "BALANCE_CCY")
  private BigDecimal balanceCcy;

  @Column(name = "BALANCE_LCY")
  private BigDecimal balanceLcy;

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

  @Column(name = "CR_PROD_CAPT_FREQ", length = 100)
  private String crProdCaptFreq;

  @Column(name = "CURR_ID", length = 100)
  private String currId;

  @Column(name = "CURR_TYPE", length = 100)
  private String currType;

  @Column(name = "CURRENCY_SH_NM", length = 50)
  private String currencyShNm;

  @Column(name = "CURRENT_BALANCE_CCY")
  private BigDecimal currentBalanceCcy;

  @Column(name = "CURRENT_BALANCE_LCY")
  private BigDecimal currentBalanceLcy;

  @Column(name = "CUSTOMER_FULL_NM", length = 100)
  private String customerFullNm;

  @Column(name = "CUSTOMER_ID", length = 100)
  private String customerId;

  @Column(name = "DECEASED_ACC")
  private Integer deceasedAcc;

  @Column(name = "DECEASED_DT", length = 100)
  private String deceasedDt;

  @Column(name = "DECEASED_SOURCE", length = 100)
  private String deceasedSource;

  @Column(name = "DORM_TRANS_PREIOD_FREQ", length = 100)
  private String dormTransPreiodFreq;

  @Column(name = "DORM_TRANS_PREIOD")
  private Integer dormTransPreiod;

  @Column(name = "ENCASH")
  private Integer encash;

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

  @Column(name = "FULL_ENCASH")
  private Integer fullEncash;

  @Column(name = "FUTURE_AMOUNT")
  private BigDecimal futureAmount;

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

  @Column(name = "INS_AUTO_RENEW_RT")
  private Integer insAutoRenewRt;

  @Column(name = "INS_AUTO_RENEW")
  private Integer insAutoRenew;

  @Column(name = "INS_MAX_AUTO_RENEW_NO")
  private Integer insMaxAutoRenewNo;

  @Column(name = "INSTRUMENT_NO", length = 100)
  private String instrumentNo;

  @Column(name = "INT_ACCRUED_TO_MONTH_CR")
  private BigDecimal intAccruedToMonthCr;

  @Column(name = "INT_CAL_BALANCE")
  private BigDecimal intCalBalance;

  @Column(name = "INT_CAL_ON")
  private Integer intCalOn;

  @Column(name = "INT_PAYABLE_FLAG")
  private Integer intPayableFlag;

  @Column(name = "INT_PAYABLE_FREQ", length = 100)
  private String intPayableFreq;

  @Column(name = "INT_PAYABLE_NO")
  private Integer intPayableNo;

  @Column(name = "INT_RATE_ID", length = 100)
  private String intRateId;

  @Column(name = "INTEREST_PAYABLE_CR")
  private BigDecimal interestPayableCr;

  @Column(name = "JOINT_ACC_HOLDER")
  private Integer jointAccHolder;

  @Column(name = "LAST_ACTION", length = 100)
  private String lastAction;

  @Column(name = "LIEN_AMOUNT")
  private BigDecimal lienAmount;

  @Column(name = "LST_ANNIVERSARY_DT", length = 100)
  private String lstAnniversaryDt;

  @Column(name = "LST_INT_APPLY_AMOUNT_CR")
  private BigDecimal lstIntApplyAmountCr;

  @Column(name = "LST_INT_APPLY_DT_CR", length = 100)
  private String lstIntApplyDtCr;

  @Column(name = "LST_INT_CAL_DT_CR", length = 100)
  private String lstIntCalDtCr;

  @Column(name = "LST_PAYABLE_DT", length = 100)
  private String lstPayableDt;

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

  @Column(name = "MAT_DT_CAL_FREQ", length = 100)
  private String matDtCalFreq;

  @Column(name = "MAT_DT_CAL_METHOD", length = 100)
  private String matDtCalMethod;

  @Column(name = "MAT_DUE_NOTICE_DAY")
  private Integer matDueNoticeDay;

  @Column(name = "MATU_AUTO_RENEW")
  private Integer matuAutoRenew;

  @Column(name = "MATURITY_AMT")
  private BigDecimal maturityAmount;

  @Column(name = "MATURITY_DT", length = 100)
  private String maturityDate;

  @Column(name = "MIN_BALANCE_REQ")
  private BigDecimal minBalanceReq;

  @Column(name = "NEXT_INT_APPLY_DT_CR", length = 100)
  private String nextIntApplyDtCr;

  @Column(name = "NFT_LOG_MSG", length = 100)
  private String nftLogMsg;

  @Column(name = "NO_INT_CAL_CR")
  private Integer noIntCalCr;

  @Column(name = "NOINS_AUTO_RENEW_RT", length = 100)
  private String noinsAutoRenewRt;

  @Column(name = "NOINS_AUTO_RENEW_TERM")
  private Integer noinsAutoRenewTerm;

  @Column(name = "NOINS_AUTO_RENEW")
  private Integer noinsAutoRenew;

  @Column(name = "NOINS_MAX_AUTO_RENEW_NO")
  private Integer noinsMaxAutoRenewNo;

  @Column(name = "NOINST_ACC_PREFIX", length = 100)
  private String noinstAccPrefix;

  @Column(name = "OACCOUNT_NUMBER", length = 100)
  private String oaccountNumber;

  @Column(name = "OPENING_BALANCE_CCY")
  private BigDecimal openingBalanceCcy;

  @Column(name = "OPENING_BALANCE_LCY")
  private BigDecimal openingBalanceLcy;

  @Column(name = "PART_ENCASH")
  private Integer partEncash;

  @Column(name = "PAY_IN_CASH")
  private Integer payInCash;

  @Column(name = "PAY_IN_CHQ_BANK", length = 100)
  private String payInChqBank;

  @Column(name = "PAY_IN_CHQ_BRANCH", length = 100)
  private String payInChqBranch;

  @Column(name = "PAY_IN_CHQ_DT", length = 100)
  private String payInChqDt;

  @Column(name = "PAY_IN_CHQ_NO", length = 100)
  private String payInChqNo;

  @Column(name = "PAY_IN_CHQ")
  private Integer payInChq;

  @Column(name = "PAY_IN_TRF_ACNO", length = 100)
  private String payInTrfAcno;

  @Column(name = "PAY_IN_TRF_BR_ID", length = 100)
  private String payInTrfBrId;

  @Column(name = "PAY_IN_TRF")
  private Integer payInTrf;

  @Column(name = "PAY_OUT_ACCOUNT_NUMBER", length = 100)
  private String payOutAccountNumber;

  @Column(name = "PAY_OUT_BRANCH_ID", length = 100)
  private String payOutBranchId;

  @Column(name = "PAY_OUT_INT_ACNO", length = 100)
  private String payOutIntAcno;

  @Column(name = "PAY_OUT_INT_BANKERS_CHQ_ACC")
  private Integer payOutIntBankersChqAcc;

  @Column(name = "PAY_OUT_INT_BR_ID", length = 100)
  private String payOutIntBrId;

  @Column(name = "PAY_OUT_INT_CSH")
  private Integer payOutIntCsh;

  @Column(name = "PAY_OUT_INT_SUND_ACC")
  private Integer payOutIntSundAcc;

  @Column(name = "PAY_OUT_INT_TRN", length = 100)
  private String payOutIntTrn;

  @Column(name = "PAY_OUT_PRIN_ACNO", length = 100)
  private String payOutPrinAcno;

  @Column(name = "PAY_OUT_PRIN_BANKERS_CHQ_ACC")
  private Integer payOutPrinBankersChqAcc;

  @Column(name = "PAY_OUT_PRIN_BR_ID", length = 100)
  private String payOutPrinBrId;

  @Column(name = "PAY_OUT_PRIN_CSH")
  private Integer payOutPrinCsh;

  @Column(name = "PAY_OUT_PRIN_SUND_ACC")
  private Integer payOutPrinSundAcc;

  @Column(name = "PAY_OUT_PRIN_TRN", length = 100)
  private String payOutPrinTrn;

  @Column(name = "PAYIN_BANKERS_CHQ_ACC")
  private Integer payinBankersChqAcc;

  @Column(name = "PAYIN_SUND_ACC")
  private Integer payinSundAcc;

  @Column(name = "PENAL_AMT_ENCASH")
  private BigDecimal penalAmtEncash;

  @Column(name = "PEND_AUTH_CR")
  private BigDecimal pendAuthCr;

  @Column(name = "PEND_AUTH_DR")
  private BigDecimal pendAuthDr;

  @Column(name = "PREVIOUS_ACC_RENEW_DT", length = 100)
  private String previousAccRenewDt;

  @Column(name = "PRINCIPAL_AMT")
  private BigDecimal principalAmount;

  @Column(name = "PROD_CAL_ON_BAL")
  private Integer prodCalOnBal;

  @Column(name = "PROD_CAL_ON_CR")
  private Integer prodCalOnCr;

  @Column(name = "PROD_CAL")
  private Integer prodCal;

  @Column(name = "PROD_INT_TOT_CR")
  private BigDecimal prodIntTotCr;

  @Column(name = "PROD_NEG_INT_CR_VARI")
  private BigDecimal prodNegIntCrVari;

  @Column(name = "PROD_POS_INT_CR_VARI")
  private BigDecimal prodPosIntCrVari;

  @Column(name = "PROD_TYPE", length = 100)
  private String prodType;

  @Column(name = "PRODUCT_ID", length = 100)
  private String productId;

  @Column(name = "PRODUCT_NM", length = 100)
  private String productName;

  @Column(name = "REMOTE_FLAG")
  private Integer remoteFlag;

  @Column(name = "RENEWAL_INST")
  private Integer renewalInst;

  @Column(name = "RESTRICT_ACC_ID")
  private Integer restrictAccId;

  @Column(name = "RESTRICT_CLG")
  private Integer restrictClg;

  @Column(name = "RESTRICT_CSH")
  private Integer restrictCsh;

  @Column(name = "RESTRICT_TRF")
  private Integer restrictTrf;

  @Column(name = "RO_EMP_ID", length = 100)
  private String roEmpId;

  @Column(name = "SBS_ECO_PUR_ID")
  private Integer sbsEcoPurId;

  @Column(name = "SBS_PRIVLGE_ID")
  private Integer sbsPrivlgeId;

  @Column(name = "SBS_SECURITY_ID")
  private Integer sbsSecurityId;

  @Column(name = "SHORT_NM", length = 50)
  private String shortNm;

  @Column(name = "SPECIAL_INST", length = 100)
  private String specialInst;

  @Column(name = "STATE_CYCLE", length = 100)
  private String stateCycle;

  @Column(name = "SWP_IN_AMOUNT")
  private BigDecimal swpInAmount;

  @Column(name = "TOT_TERM_FREQ", length = 100)
  private String totalTermFrequency;

  @Column(name = "TOT_TERM_NO")
  private Integer totalTermNumber;

  @Column(name = "TOT_TERM_PAID")
  private Integer totTermPaid;

  @Column(name = "TRANSFER_TYPE", length = 100)
  private String transferType;

  @Column(name = "UNCLAIM_DEPO_TERM_FREQ", length = 100)
  private String unclaimDepoTermFreq;

  @Column(name = "UNCLAIM_DEPO_TERM")
  private Integer unclaimDepoTerm;

  @Column(name = "WITHHELD_INTEREST")
  private BigDecimal withheldInterest;

  @Column(name = "YTD_INTEREST_EARN")
  private BigDecimal ytdInterestEarn;

  @Column(name = "YTD_INTEREST_PAID")
  private BigDecimal ytdInterestPaid;

}
