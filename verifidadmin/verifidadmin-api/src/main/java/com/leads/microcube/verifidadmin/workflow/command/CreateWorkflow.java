package com.leads.microcube.verifidadmin.workflow.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
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
public class CreateWorkflow {

  @Size(max = 2000)
  @JsonProperty("WorkflowName")
  private String workflowName;

  @Size(max = 2000)
  @JsonProperty("WorkflowDesc")
  private String workflowDesc;

  @Min(0)
  @Max(255)
  @JsonProperty("AccOpnFlag")
  private Integer accOpnFlag;

  @Min(0)
  @Max(255)
  @JsonProperty("AccAuthStep")
  private Integer accAuthStep;
}
