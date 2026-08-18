package com.leads.microcube.verifidadmin.ecverification;

import com.leads.microcube.verifidadmin.ecverification.command.VerifyEcNid;
import com.leads.microcube.verifidadmin.ecverification.query.EcVerificationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ECVerification")
@RequiredArgsConstructor
public class EcVerificationController {

  private final EcVerificationService ecVerificationService;

  @PostMapping("/ECVerify")
  public ResponseEntity<EcVerificationResponse> verifyEcNid(
      @Valid @RequestBody VerifyEcNid command) {
    return ResponseEntity.ok(ecVerificationService.process(command));
  }
}
