package com.leads.microcube.verifidadmin.workflow;

import com.leads.microcube.verifidadmin.common.response.ApiResponse;
import com.leads.microcube.verifidadmin.common.security.PermissionType;
import com.leads.microcube.verifidadmin.common.security.RequirePermission;
import com.leads.microcube.verifidadmin.log.UserActivityLogService;
import com.leads.microcube.verifidadmin.log.command.RecordCurrentUserActivity;
import com.leads.microcube.verifidadmin.workflow.command.CreateWorkflow;
import com.leads.microcube.verifidadmin.workflow.command.SwapWorkflowSequence;
import com.leads.microcube.verifidadmin.workflow.query.WorkflowSequenceResponse;
import com.leads.microcube.verifidadmin.workflow.query.WorkflowSequences;
import com.leads.microcube.verifidadmin.workflow.query.WorkflowSummaryResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Workflow")
@RequiredArgsConstructor
public class WorkflowController {

  private final WorkflowService workflowService;
  private final WorkflowQueryService workflowQueryService;
  private final UserActivityLogService userActivityLogService;

  @GetMapping("/Index")
  @RequirePermission(targetPath = "Workflow/Index")
  public ResponseEntity<ApiResponse<List<WorkflowSummaryResponse>>> retrieveWorkflows() {
    List<WorkflowSummaryResponse> workflows = workflowQueryService.retrieveWorkflows();
    return ResponseEntity.ok(ApiResponse.success(workflows));
  }

  @GetMapping("/WorkflowSequences")
  @RequirePermission(targetPath = "Workflow/Index")
  public ResponseEntity<ApiResponse<List<WorkflowSequenceResponse>>> retrieveWorkflowSequences(
      @RequestParam("id") Integer id) {
    List<WorkflowSequenceResponse> sequences =
        workflowQueryService.retrieveWorkflowSequences(new WorkflowSequences(id));
    return ResponseEntity.ok(ApiResponse.success(sequences));
  }

  @GetMapping("/Create")
  @RequirePermission(targetPath = "Workflow/Index", value = PermissionType.ADD)
  public ResponseEntity<ApiResponse<CreateWorkflow>> retrieveCreateForm() {
    return ResponseEntity.ok(ApiResponse.success(new CreateWorkflow()));
  }

  @PostMapping("/Create")
  @RequirePermission(targetPath = "Workflow/Index", value = PermissionType.ADD)
  public ResponseEntity<ApiResponse<Void>> registerWorkflow(
      @Valid @RequestBody CreateWorkflow command) {
    workflowService.process(command);
    userActivityLogService.process(
        RecordCurrentUserActivity.builder()
            .trackingNo(0L)
            .stepId(0)
            .actionType("Create")
            .actionParticulars("is Creating New Workflow")
            .requestChannel("")
            .build());
    return ResponseEntity.ok(ApiResponse.success("Successfully new workflow created.", null));
  }

  @PostMapping("/SwapStepSequence")
  @RequirePermission(targetPath = "Workflow/Index", value = PermissionType.EDIT)
  public ResponseEntity<ApiResponse<Void>> swapStepSequence(
      @Valid @RequestBody SwapWorkflowSequence command) {
    workflowService.process(command);
    userActivityLogService.process(
        RecordCurrentUserActivity.builder()
            .trackingNo(0L)
            .stepId(0)
            .actionType("Edit")
            .actionParticulars("is Reordering Workflow Steps")
            .requestChannel("")
            .build());
    return ResponseEntity.ok(ApiResponse.success("Customer information updated", null));
  }
}
