package com.leads.microcube.verifidadmin.assistedekyc;

import com.leads.microcube.verifidadmin.assistedekyc.query.AssistedEkycResponse;
import com.leads.microcube.verifidadmin.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/AssistedEkyc")
@RequiredArgsConstructor
@Tag(name="Assisted eKYC")
public class AssistedEkycController {

  private static final URI INDEX_URI = URI.create("/api/AssistedEkyc/Index");

  private final AssistedEkycQueryService assistedEkycQueryService;

  @GetMapping("/Index")
  public ResponseEntity<ApiResponse<AssistedEkycResponse>> retrieveIndex() {
    return ResponseEntity.ok(
        ApiResponse.success(assistedEkycQueryService.retrieveIndex()));
  }

  @GetMapping("/Details/{id}")
  public ResponseEntity<Void> retrieveDetails(@PathVariable("id") Integer id) {
    return legacyViewUnavailable();
  }

  @GetMapping("/Create")
  public ResponseEntity<Void> retrieveCreate() {
    return legacyViewUnavailable();
  }

  @PostMapping("/Create")
  public ResponseEntity<Void> create() {
    return redirectToIndex();
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

  private ResponseEntity<Void> legacyViewUnavailable() {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }

  private ResponseEntity<Void> redirectToIndex() {
    return ResponseEntity.status(HttpStatus.FOUND).location(INDEX_URI).build();
  }
}
