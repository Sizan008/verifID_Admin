package com.leads.microcube.verifidadmin.parameterconfig;

import com.leads.microcube.verifidadmin.common.response.ApiResponse;
import com.leads.microcube.verifidadmin.common.security.PermissionType;
import com.leads.microcube.verifidadmin.common.security.RequirePermission;
import com.leads.microcube.verifidadmin.log.UserActivityLogService;
import com.leads.microcube.verifidadmin.log.command.RecordCurrentUserActivity;
import com.leads.microcube.verifidadmin.parameterconfig.command.CreateParameter;
import com.leads.microcube.verifidadmin.parameterconfig.command.UpdateParameter;
import com.leads.microcube.verifidadmin.parameterconfig.query.ParameterDetails;
import com.leads.microcube.verifidadmin.parameterconfig.query.ParameterResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Provides parameter configuration endpoints matching the legacy controller actions. */
@RestController
@RequestMapping("/api/ParameterConfig")
@RequiredArgsConstructor
@Tag(name="Parameter Config")
public class ParameterConfigController {

  private final ParameterConfigService parameterConfigService;
  private final ParameterConfigQueryService parameterConfigQueryService;
  private final UserActivityLogService userActivityLogService;

  @GetMapping("/Index")
  @RequirePermission(targetPath = "ParameterConfig/Index")
  public ResponseEntity<ApiResponse<List<ParameterResponse>>> retrieveParameters() {
    List<ParameterResponse> parameters = parameterConfigQueryService.retrieveParameters();
    return ResponseEntity.ok(ApiResponse.success(parameters));
  }

  @GetMapping("/Create")
  @RequirePermission(targetPath = "ParameterConfig/Index", value = PermissionType.ADD)
  public ResponseEntity<ApiResponse<CreateParameter>> retrieveCreateForm() {
    return ResponseEntity.ok(ApiResponse.success(new CreateParameter()));
  }

  @PostMapping("/Create")
  @RequirePermission(targetPath = "ParameterConfig/Index", value = PermissionType.ADD)
  public ResponseEntity<ApiResponse<ParameterResponse>> registerParameter(
      @Valid @RequestBody CreateParameter command) {

    parameterConfigService.process(command);
    userActivityLogService.process(
        RecordCurrentUserActivity.builder()
            .trackingNo(0L)
            .stepId(0)
            .actionType("Create")
            .actionParticulars("is Create Parameter Config")
            .requestChannel("")
            .build());

    ParameterResponse parameter =
        parameterConfigQueryService.retrieveParameter(
            new ParameterDetails(command.getParamName()));

    return ResponseEntity.ok(
        ApiResponse.success(
            "Parameter configuration created successfully.",
            parameter));
  }

  @GetMapping("/Edit/{id}")
  @RequirePermission(targetPath = "ParameterConfig/Index", value = PermissionType.EDIT)
  public ResponseEntity<ApiResponse<ParameterResponse>> retrieveParameter(
      @PathVariable("id") String id) {
    ParameterResponse parameter =
        parameterConfigQueryService.retrieveParameter(new ParameterDetails(id));
    return ResponseEntity.ok(ApiResponse.success(parameter));
  }

  @PostMapping("/Edit/{id}")
  @RequirePermission(targetPath = "ParameterConfig/Index", value = PermissionType.EDIT)
  public ResponseEntity<ApiResponse<ParameterResponse>> updateParameter(
      @PathVariable("id") String id,
      @Valid @RequestBody UpdateParameter command) {

    command.setCurrentParamName(id);
    parameterConfigService.process(command);
    userActivityLogService.process(
        RecordCurrentUserActivity.builder()
            .trackingNo(0L)
            .stepId(0)
            .actionType("Edit")
            .actionParticulars("is Edit Parameter Config")
            .requestChannel("")
            .build());

    ParameterResponse parameter =
        parameterConfigQueryService.retrieveParameter(
            new ParameterDetails(id));

    return ResponseEntity.ok(
        ApiResponse.success(
            "Parameter configuration updated successfully.",
            parameter));
  }
}
