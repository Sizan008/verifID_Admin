package com.leads.microcube.verifidadmin.customerprofile.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
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
public class RiskGradingFormResponse {

  @JsonProperty("RiskGrading") private RiskGradingResponse riskGrading;
  @Builder.Default @JsonProperty("AverageYearTransactions")
  private List<RiskOption> averageYearTransactions = new ArrayList<>();
  @Builder.Default @JsonProperty("BusinessTypes")
  private List<RiskOption> businessTypes = new ArrayList<>();
  @Builder.Default @JsonProperty("ProfessionTypes")
  private List<RiskOption> professionTypes = new ArrayList<>();
  @Builder.Default @JsonProperty("OnboardingTypes")
  private List<RiskOption> onboardingTypes = new ArrayList<>();
  @Builder.Default @JsonProperty("ResidentTypes")
  private List<RiskOption> residentTypes = new ArrayList<>();
  @Builder.Default @JsonProperty("ProductTypes")
  private List<RiskOption> productTypes = new ArrayList<>();
  @JsonProperty("CAMLCO_APPROVAL_WARNING") private String camlcoApprovalWarning;
  @JsonProperty("CAMLCO_APPROVAL_WARNING_MSG") private String camlcoApprovalWarningMessage;
  @JsonProperty("RiskSubmit") private boolean riskSubmit;
}
