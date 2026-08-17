package com.leads.microcube.verifidadmin.workflow;

import com.leads.microcube.verifidadmin.workflow.command.CreateWorkflow;
import com.leads.microcube.verifidadmin.workflow.command.SwapWorkflowSequence;

public interface WorkflowService {

  void process(CreateWorkflow command);

  void process(SwapWorkflowSequence command);
}
