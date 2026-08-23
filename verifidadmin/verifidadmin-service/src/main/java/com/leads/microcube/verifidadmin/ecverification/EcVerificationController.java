package com.leads.microcube.verifidadmin.ecverification;

import com.leads.microcube.verifidadmin.ecverification.command.VerifyEcNid;
import com.leads.microcube.verifidadmin.ecverification.query.EcVerificationResponse;
import com.leads.microcube.verifidadmin.common.response.ApiResponse;
import com.leads.microcube.verifidadmin.ecverification.query.EcAddressOptionResponse;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ECVerification")
@RequiredArgsConstructor
@Tag(name="EC Verification")
public class EcVerificationController {

  private final EcVerificationService ecVerificationService;
  private final EcVerificationQueryService ecVerificationQueryService;

  @GetMapping("/Index")
  public ResponseEntity<Void> retrieveIndex() {
    return ResponseEntity.ok().build();
  }

  @GetMapping("/Divisions")
  public ResponseEntity<ApiResponse<List<EcAddressOptionResponse>>>
  retrieveDivisions() {

    return ResponseEntity.ok(
            ApiResponse.success(
                    ecVerificationQueryService.retrieveDivisions()));
  }

  @GetMapping("/Districts")
  public ResponseEntity<ApiResponse<List<EcAddressOptionResponse>>>
  retrieveDistricts(
          @RequestParam("divisionId") Integer divisionId) {

    return ResponseEntity.ok(
            ApiResponse.success(
                    ecVerificationQueryService.retrieveDistricts(divisionId)));
  }

  @GetMapping("/Upazilas")
  public ResponseEntity<ApiResponse<List<EcAddressOptionResponse>>>
  retrieveUpazilas(
          @RequestParam("districtId") Integer districtId) {

    return ResponseEntity.ok(
            ApiResponse.success(
                    ecVerificationQueryService.retrieveUpazilas(districtId)));
  }

  @GetMapping("/PostOffices")
  public ResponseEntity<ApiResponse<List<EcAddressOptionResponse>>>
  retrievePostOffices(
          @RequestParam("districtId") Integer districtId,
          @RequestParam("upazilaId") Integer upazilaId) {

    return ResponseEntity.ok(
            ApiResponse.success(
                    ecVerificationQueryService.retrievePostOffices(
                            districtId,
                            upazilaId)));
  }

  @PostMapping("/ECVerify")
  public ResponseEntity<EcVerificationResponse> verifyEcNid(
      @Valid @RequestBody VerifyEcNid command) {
    return ResponseEntity.ok(ecVerificationService.process(command));
  }
}
