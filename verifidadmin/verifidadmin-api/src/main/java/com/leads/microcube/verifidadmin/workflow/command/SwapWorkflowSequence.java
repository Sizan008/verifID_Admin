package com.leads.microcube.verifidadmin.workflow.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
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
public class SwapWorkflowSequence {

  @NotNull
  @Min(1)
  @JsonProperty("current_wf_sq_id")
  private Integer currentWorkflowSequenceId;

  @NotNull
  @Min(1)
  @JsonProperty("next_wf_sq_id")
  private Integer nextWorkflowSequenceId;
}
