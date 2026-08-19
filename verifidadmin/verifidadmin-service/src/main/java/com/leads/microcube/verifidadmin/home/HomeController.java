package com.leads.microcube.verifidadmin.home;

import com.leads.microcube.verifidadmin.common.response.ApiResponse;
import com.leads.microcube.verifidadmin.common.security.RequirePermission;
import com.leads.microcube.verifidadmin.home.query.DashboardResponse;
import com.leads.microcube.verifidadmin.home.query.SubBranchResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Home")
@RequiredArgsConstructor
@Tag(name="Home")
public class HomeController {

  private final HomeQueryService homeQueryService;

  @GetMapping("/Index")
  @RequirePermission(targetPath = "Home/Index")
  public ResponseEntity<ApiResponse<DashboardResponse>> retrieveDashboard() {
    return ResponseEntity.ok(ApiResponse.success(homeQueryService.retrieveDashboard()));
  }

  @GetMapping("/Privacy")
  public ResponseEntity<Void> retrievePrivacy() {
    return ResponseEntity.ok().build();
  }

  @GetMapping("/Error")
  public ResponseEntity<Void> retrieveError() {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .cacheControl(CacheControl.noStore())
        .build();
  }

  @GetMapping("/IsSubBranch")
  public ResponseEntity<SubBranchResponse> retrieveSubBranchStatus() {
    return ResponseEntity.ok(homeQueryService.retrieveSubBranchStatus());
  }

  @GetMapping("/GetHeadOfficeBranchId")
  public ResponseEntity<String> retrieveHeadOfficeBranchId() {
    return ResponseEntity.ok(homeQueryService.retrieveHeadOfficeBranchId());
  }
}
