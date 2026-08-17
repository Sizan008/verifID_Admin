package com.leads.microcube.verifidadmin.workflow;

import com.leads.microcube.verifidadmin.workflow.query.WorkflowSequenceResponse;
import com.leads.microcube.verifidadmin.workflow.query.WorkflowSummaryResponse;
import com.leads.microcube.verifidadmin.workflow.repository.WorkflowEntity;
import com.leads.microcube.verifidadmin.workflow.repository.WorkflowSequenceEntity;
import org.springframework.stereotype.Component;

@Component
public class WorkflowMapper {

  public WorkflowSummaryResponse toSummaryResponse(WorkflowEntity entity) {
    return WorkflowSummaryResponse.builder()
        .workflowId(entity.getWorkflowId())
        .workflowName(entity.getWorkflowName())
        .build();
  }

  public WorkflowSequenceResponse toSequenceResponse(
      WorkflowSequenceEntity entity, String stepName) {
    return WorkflowSequenceResponse.builder()
        .workflowSequenceId(entity.getWorkflowSequenceId())
        .stepSequenceNumber(entity.getStepSequenceNumber())
        .stepId(entity.getStepId())
        .stepName(stepName)
        .build();
  }
}
