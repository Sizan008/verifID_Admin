package com.leads.microcube.verifidadmin.customerprofile.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
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
public class SaveRiskGrading {

  @NotNull
  @JsonProperty("TrackingNo")
  private Long trackingNo;

  @JsonProperty("OnboardTypeId")
  private Integer onboardTypeId;

  @JsonProperty("ResidentTypeId")
  private Integer residentTypeId;

  @JsonProperty("ProductTypeId")
  private String productTypeId;

  @JsonProperty("BizProfFlag")
  private Integer bizProfFlag;

  @JsonProperty("BizProfTypeId")
  private Integer bizProfTypeId;

  @JsonProperty("ActivityTypeId")
  private Integer activityTypeId;

  @JsonProperty("SelfPepCoio")
  private Integer selfPepCoio;

  @JsonProperty("ReltPepCoio")
  private Integer reltPepCoio;

  @JsonProperty("SelfIpReltIp")
  private Integer selfIpReltIp;

  @JsonProperty("AvgYearTransId")
  private Integer avgYearTransId;

  @JsonProperty("CrdblSrcFund")
  private Integer crdblSrcFund;
}
