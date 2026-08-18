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
@Table(name = "CUSTOMER_RISK_GRADE")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CustomerRiskGradeEntity {

  @Id
  @Column(name = "TRACKING_NO", nullable = false)
  private Long trackingNo;

  @Column(name = "ONBOARD_TYPE_ID", nullable = false)
  private Integer onboardTypeId;

  @Column(name = "RESIDENT_TYPE_ID", nullable = false)
  private Integer residentTypeId;

  @Column(name = "PRODUCT_TYPE_ID", nullable = false, length = 5)
  private String productTypeId;

  @Column(name = "BIZ_PROF_FLAG", nullable = false)
  private Integer bizProfFlag;

  @Column(name = "BIZ_PROF_TYPE_ID", nullable = false)
  private Integer bizProfTypeId;

  @Column(name = "GREY_LISTED_CUST", nullable = false)
  private Integer greyListedCust;

  @Column(name = "SELF_PEP_COIO", nullable = false)
  private Integer selfPepCoio;

  @Column(name = "RELT_PEP_COIO", nullable = false)
  private Integer reltPepCoio;

  @Column(name = "SELF_IP_RELT_IP", nullable = false)
  private Integer selfIpReltIp;

  @Column(name = "AVG_YEAR_TRANS_ID", nullable = false)
  private Integer avgYearTransId;

  @Column(name = "CRDBL_SRC_FUND", nullable = false)
  private Integer crdblSrcFund;

}
