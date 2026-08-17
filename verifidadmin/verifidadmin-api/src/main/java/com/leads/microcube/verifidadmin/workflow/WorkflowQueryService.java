package com.leads.microcube.verifidadmin.workflow;

import com.leads.microcube.verifidadmin.workflow.query.WorkflowSequenceResponse;
import com.leads.microcube.verifidadmin.workflow.query.WorkflowSequences;
import com.leads.microcube.verifidadmin.workflow.query.WorkflowSummaryResponse;
import java.util.List;

public interface WorkflowQueryService {

  List<WorkflowSummaryResponse> retrieveWorkflows();

  List<WorkflowSequenceResponse> retrieveWorkflowSequences(WorkflowSequences query);
}
