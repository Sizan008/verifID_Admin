package com.leads.microcube.verifidadmin.apimanagement;

import com.leads.microcube.verifidadmin.apimanagement.command.CreateApiConnection;
import com.leads.microcube.verifidadmin.apimanagement.command.UpdateApiConnection;
import com.leads.microcube.verifidadmin.apimanagement.query.ApiConnectionDetails;
import com.leads.microcube.verifidadmin.apimanagement.query.ApiConnectionResponse;
import com.leads.microcube.verifidadmin.common.response.ApiResponse;
import com.leads.microcube.verifidadmin.common.security.PermissionType;
import com.leads.microcube.verifidadmin.common.security.RequirePermission;
import com.leads.microcube.verifidadmin.log.UserActivityLogService;
import com.leads.microcube.verifidadmin.log.command.RecordCurrentUserActivity;
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
@RequestMapping("/api/ApiManagement")
@RequiredArgsConstructor
@Tag(name="API Management")
public class ApiManagementController {

  private static final URI INDEX_URI = URI.create("/api/ApiManagement/Index");

  private final ApiManagementService apiManagementService;
  private final ApiManagementQueryService apiManagementQueryService;
  private final UserActivityLogService userActivityLogService;

  @GetMapping("/Index")
  @RequirePermission(targetPath = "ApiManagement/Index")
  public ResponseEntity<ApiResponse<List<ApiConnectionResponse>>> retrieveApiConnections() {
    List<ApiConnectionResponse> connections =
        apiManagementQueryService.retrieveApiConnections();
    return ResponseEntity.ok(ApiResponse.success(connections));
  }

  @GetMapping("/Details/{id}")
  public ResponseEntity<Void> retrieveDetails(@PathVariable("id") Integer id) {
    return legacyViewUnavailable();
  }

  @GetMapping("/Create")
  @RequirePermission(targetPath = "ApiManagement/Index", value = PermissionType.ADD)
  public ResponseEntity<ApiResponse<CreateApiConnection>> retrieveCreateForm() {
    return ResponseEntity.ok(ApiResponse.success(new CreateApiConnection()));
  }

  @PostMapping("/Create")
  @RequirePermission(targetPath = "ApiManagement/Index", value = PermissionType.ADD)
  public ResponseEntity<ApiResponse<Void>> registerApiConnection(
      @Valid @RequestBody CreateApiConnection command) {
    apiManagementService.process(command);
    userActivityLogService.process(
        RecordCurrentUserActivity.builder()
            .trackingNo(0L)
            .stepId(0)
            .actionType("Create")
            .actionParticulars("is Create API Connection")
            .requestChannel("")
            .build());
    return ResponseEntity.ok(
        ApiResponse.success("API connection created successfully.", null));
  }

  @GetMapping("/Edit/{id}")
  @RequirePermission(targetPath = "ApiManagement/Index", value = PermissionType.EDIT)
  public ResponseEntity<ApiResponse<ApiConnectionResponse>> retrieveApiConnection(
      @PathVariable("id") Integer id) {
    ApiConnectionResponse connection =
        apiManagementQueryService.retrieveApiConnection(new ApiConnectionDetails(id));
    return ResponseEntity.ok(ApiResponse.success(connection));
  }

  @GetMapping(value = "/Edit", params = "id")
  @RequirePermission(targetPath = "ApiManagement/Index", value = PermissionType.EDIT)
  public ResponseEntity<ApiResponse<ApiConnectionResponse>> retrieveApiConnectionByQuery(
      @RequestParam("id") Integer id) {
    return retrieveApiConnection(id);
  }

  @PostMapping("/Edit")
  @RequirePermission(targetPath = "ApiManagement/Index", value = PermissionType.EDIT)
  public ResponseEntity<ApiResponse<ApiConnectionResponse>> updateApiConnection(
      @Valid @RequestBody UpdateApiConnection command) {
    apiManagementService.process(command);
    userActivityLogService.process(
        RecordCurrentUserActivity.builder()
            .trackingNo(0L)
            .stepId(0)
            .actionType("Edit")
            .actionParticulars("is Edit API Connection")
            .requestChannel("")
            .build());
    ApiConnectionResponse connection =
        apiManagementQueryService.retrieveApiConnection(
            new ApiConnectionDetails(command.getApiConnId()));
    return ResponseEntity.ok(
        ApiResponse.success("API connection updated successfully.", connection));
  }

  @GetMapping("/Delete/{id}")
  public ResponseEntity<Void> retrieveDelete(@PathVariable("id") Integer id) {
    return legacyViewUnavailable();
  }

  @PostMapping("/Delete/{id}")
  public ResponseEntity<Void> deleteApiConnection(@PathVariable("id") Integer id) {
    return redirectToIndex();
  }

  private ResponseEntity<Void> legacyViewUnavailable() {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }

  private ResponseEntity<Void> redirectToIndex() {
    return ResponseEntity.status(HttpStatus.FOUND).location(INDEX_URI).build();
  }
}
