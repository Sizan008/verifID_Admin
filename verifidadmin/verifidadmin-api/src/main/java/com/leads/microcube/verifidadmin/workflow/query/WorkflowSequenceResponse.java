package com.leads.microcube.verifidadmin.workflow.query;

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
public class WorkflowSequenceResponse {

  @JsonProperty("wf_seq_id")
  private Integer workflowSequenceId;

  @JsonProperty("StepSeqNo")
  private Integer stepSequenceNumber;

  @JsonProperty("StepId")
  private Integer stepId;

  @JsonProperty("StepName")
  private String stepName;
}
