package com.leads.microcube.verifidadmin.apimanagement;

import com.leads.microcube.verifidadmin.apimanagement.command.CreateApiConnection;
import com.leads.microcube.verifidadmin.apimanagement.command.UpdateApiConnection;
import com.leads.microcube.verifidadmin.apimanagement.query.ApiConnectionDetails;
import com.leads.microcube.verifidadmin.apimanagement.query.ApiConnectionResponse;
import com.leads.microcube.verifidadmin.common.response.ApiResponse;
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

@RestController
@RequestMapping("/api/ApiManagement")
@RequiredArgsConstructor
public class ApiManagementController {

  private final ApiManagementService apiManagementService;
  private final ApiManagementQueryService apiManagementQueryService;

  @GetMapping("/Index")
  public ResponseEntity<ApiResponse<List<ApiConnectionResponse>>> retrieveApiConnections() {
    List<ApiConnectionResponse> connections =
        apiManagementQueryService.retrieveApiConnections();
    return ResponseEntity.ok(ApiResponse.success(connections));
  }

  @GetMapping("/Create")
  public ResponseEntity<ApiResponse<CreateApiConnection>> retrieveCreateForm() {
    return ResponseEntity.ok(ApiResponse.success(new CreateApiConnection()));
  }

  @PostMapping("/Create")
  public ResponseEntity<ApiResponse<Void>> registerApiConnection(
      @Valid @RequestBody CreateApiConnection command) {
    apiManagementService.process(command);
    return ResponseEntity.ok(
        ApiResponse.success("API connection created successfully.", null));
  }

  @GetMapping("/Edit/{id}")
  public ResponseEntity<ApiResponse<ApiConnectionResponse>> retrieveApiConnection(
      @PathVariable("id") Integer id) {
    ApiConnectionResponse connection =
        apiManagementQueryService.retrieveApiConnection(new ApiConnectionDetails(id));
    return ResponseEntity.ok(ApiResponse.success(connection));
  }

  @PostMapping("/Edit")
  public ResponseEntity<ApiResponse<ApiConnectionResponse>> updateApiConnection(
      @Valid @RequestBody UpdateApiConnection command) {
    apiManagementService.process(command);
    ApiConnectionResponse connection =
        apiManagementQueryService.retrieveApiConnection(
            new ApiConnectionDetails(command.getApiConnId()));
    return ResponseEntity.ok(
        ApiResponse.success("API connection updated successfully.", connection));
  }
}
