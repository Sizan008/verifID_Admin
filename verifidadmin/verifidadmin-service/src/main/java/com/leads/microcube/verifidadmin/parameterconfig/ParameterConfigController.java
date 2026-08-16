package com.leads.microcube.verifidadmin.parameterconfig;

import com.leads.microcube.verifidadmin.common.response.ApiResponse;
import com.leads.microcube.verifidadmin.parameterconfig.command.CreateParameter;
import com.leads.microcube.verifidadmin.parameterconfig.command.UpdateParameter;
import com.leads.microcube.verifidadmin.parameterconfig.query.ParameterDetails;
import com.leads.microcube.verifidadmin.parameterconfig.query.ParameterResponse;
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
public class ParameterConfigController {

  private final ParameterConfigService parameterConfigService;
  private final ParameterConfigQueryService parameterConfigQueryService;

  @GetMapping("/Index")
  public ResponseEntity<ApiResponse<List<ParameterResponse>>> retrieveParameters() {
    List<ParameterResponse> parameters = parameterConfigQueryService.retrieveParameters();
    return ResponseEntity.ok(ApiResponse.success(parameters));
  }

  @GetMapping("/Create")
  public ResponseEntity<ApiResponse<CreateParameter>> retrieveCreateForm() {
    return ResponseEntity.ok(ApiResponse.success(new CreateParameter()));
  }

  @PostMapping("/Create")
  public ResponseEntity<ApiResponse<ParameterResponse>> registerParameter(
          @Valid @RequestBody CreateParameter command) {

    parameterConfigService.process(command);

    ParameterResponse parameter =
            parameterConfigQueryService.retrieveParameter(
                    new ParameterDetails(command.getParamName()));

    return ResponseEntity.ok(
            ApiResponse.success(
                    "Parameter configuration created successfully.",
                    parameter));
  }

  @GetMapping("/Edit/{id}")
  public ResponseEntity<ApiResponse<ParameterResponse>> retrieveParameter(
      @PathVariable("id") String id) {
    ParameterResponse parameter =
        parameterConfigQueryService.retrieveParameter(new ParameterDetails(id));
    return ResponseEntity.ok(ApiResponse.success(parameter));
  }

  @PostMapping("/Edit/{id}")
  public ResponseEntity<ApiResponse<ParameterResponse>> updateParameter(
          @PathVariable("id") String id,
          @Valid @RequestBody UpdateParameter command) {

    command.setCurrentParamName(id);
    parameterConfigService.process(command);

    ParameterResponse parameter =
            parameterConfigQueryService.retrieveParameter(
                    new ParameterDetails(id));

    return ResponseEntity.ok(
            ApiResponse.success(
                    "Parameter configuration updated successfully.",
                    parameter));
  }
}
