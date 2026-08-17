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
public class WorkflowSummaryResponse {

  @JsonProperty("WorkflowId")
  private Integer workflowId;

  @JsonProperty("WorkflowName")
  private String workflowName;
}
