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
public class CustomerRiskScoreResponse {

  @JsonProperty("CustomerRiskScore")
  private BigDecimal customerRiskScore;

  @JsonProperty("RiskGrading")
  private RiskGradingResponse riskGrading;
}
