package com.leads.microcube.verifidadmin.workflow.command;

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
public class ChangeWorkflowSequence {

  @JsonProperty("OldSeqNo")
  private Integer oldSequenceNumber;

  @JsonProperty("NewSeqNo")
  private Integer newSequenceNumber;

  @JsonProperty("WorkflowId")
  private Integer workflowId;
}
