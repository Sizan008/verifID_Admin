package com.leads.microcube.verifidadmin.workflow;

import com.leads.microcube.verifidadmin.common.response.ApiResponse;
import com.leads.microcube.verifidadmin.common.security.PermissionType;
import com.leads.microcube.verifidadmin.common.security.RequirePermission;
import com.leads.microcube.verifidadmin.log.UserActivityLogService;
import com.leads.microcube.verifidadmin.log.command.RecordCurrentUserActivity;
import com.leads.microcube.verifidadmin.workflow.command.ChangeWorkflowSequence;
import com.leads.microcube.verifidadmin.workflow.command.CreateWorkflow;
import com.leads.microcube.verifidadmin.workflow.command.SwapWorkflowSequence;
import com.leads.microcube.verifidadmin.workflow.query.WorkflowSequenceResponse;
import com.leads.microcube.verifidadmin.workflow.query.WorkflowSequences;
import com.leads.microcube.verifidadmin.workflow.query.WorkflowSummaryResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Workflow")
@RequiredArgsConstructor
@Tag(name="Workflow")
public class WorkflowController {

  private static final URI INDEX_URI = URI.create("/api/Workflow/Index");

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

  @GetMapping("/Edit/{id}")
  public ResponseEntity<Void> retrieveEdit(@PathVariable("id") Integer id) {
    return legacyViewUnavailable();
  }

  @PostMapping("/Edit/{id}")
  public ResponseEntity<Void> edit(@PathVariable("id") Integer id) {
    return redirectToIndex();
  }

  @GetMapping("/Delete/{id}")
  public ResponseEntity<Void> retrieveDelete(@PathVariable("id") Integer id) {
    return legacyViewUnavailable();
  }

  @PostMapping("/Delete/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
    return redirectToIndex();
  }

  @PostMapping("/UpdateSequence")
  public ResponseEntity<Void> updateSequence(
      @RequestBody ChangeWorkflowSequence command) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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

  private ResponseEntity<Void> legacyViewUnavailable() {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }

  private ResponseEntity<Void> redirectToIndex() {
    return ResponseEntity.status(HttpStatus.FOUND).location(INDEX_URI).build();
  }
}
