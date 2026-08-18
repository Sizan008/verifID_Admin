package com.leads.microcube.verifidadmin.customerprofile.query;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class RiskGradingResponse {

  @JsonProperty("TrackingNo") private Long trackingNo;
  @JsonProperty("OnboardTypeId") private Integer onboardTypeId;
  @JsonProperty("ResidentTypeId") private Integer residentTypeId;
  @JsonProperty("ProductTypeId") private String productTypeId;
  @JsonProperty("BizProfFlag") private Integer bizProfFlag;
  @JsonProperty("BizProfTypeId") private Integer bizProfTypeId;
  @JsonProperty("SelfPepCoio") private Integer selfPepCoio;
  @JsonProperty("ReltPepCoio") private Integer reltPepCoio;
  @JsonProperty("SelfIpReltIp") private Integer selfIpReltIp;
  @JsonProperty("AvgYearTransId") private Integer avgYearTransId;
  @JsonProperty("CrdblSrcFund") private Integer crdblSrcFund;
  @JsonProperty("OnboardType") private String onboardType;
  @JsonProperty("ResidentType") private String residentType;
  @JsonProperty("ProductType") private String productType;
  @JsonProperty("AvgYearTrans") private String avgYearTrans;
  @JsonProperty("BizProfType") private String bizProfType;
  @JsonProperty("OnboardTypeRISKValue") private Integer onboardTypeRiskValue;
  @JsonProperty("ResidentTypeRISKValue") private Integer residentTypeRiskValue;
  @JsonProperty("ProductTypeRISKValue") private Integer productTypeRiskValue;
  @JsonProperty("AvgYearTransRISKValue") private Integer avgYearTransRiskValue;
  @JsonProperty("BizProfTypeRISKValue") private Integer bizProfTypeRiskValue;
  @JsonProperty("SelfPepCoioNm") private String selfPepCoioName;
  @JsonProperty("ReltPepCoioNm") private String reltPepCoioName;
  @JsonProperty("SelfIpReltIpNm") private String selfIpReltIpName;
  @JsonProperty("CrdblSrcFundNm") private String crdblSrcFundName;
  @JsonProperty("SelfPepCoioRiskValue") private Integer selfPepCoioRiskValue;
  @JsonProperty("ReltPepCoioRiskValue") private Integer reltPepCoioRiskValue;
  @JsonProperty("SelfIpReltIpRiskValue") private Integer selfIpReltIpRiskValue;
  @JsonProperty("CrdblSrcFundRiskValue") private Integer crdblSrcFundRiskValue;
}
