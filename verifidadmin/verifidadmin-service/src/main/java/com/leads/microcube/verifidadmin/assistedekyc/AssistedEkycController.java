package com.leads.microcube.verifidadmin.assistedekyc;

import com.leads.microcube.verifidadmin.assistedekyc.query.AssistedEkycResponse;
import com.leads.microcube.verifidadmin.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/AssistedEkyc")
@RequiredArgsConstructor
@Tag(name="Assisted eKYC")
public class AssistedEkycController {

  private final AssistedEkycQueryService assistedEkycQueryService;

  @GetMapping("/Index")
  public ResponseEntity<ApiResponse<AssistedEkycResponse>> retrieveIndex() {
    return ResponseEntity.ok(
        ApiResponse.success(assistedEkycQueryService.retrieveIndex()));
  }
}
