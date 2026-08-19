package com.leads.microcube.verifidadmin.error;

import com.leads.microcube.verifidadmin.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Error")
@Tag(name="Error")
public class ErrorController {

  private static final String UNAUTHORIZED_MESSAGE = "Unauthorize access.";

  @GetMapping("/FullPage")
  public ResponseEntity<ApiResponse<Void>> retrieveFullPage() {
    return unauthorized();
  }

  @GetMapping("/PartialPage")
  public ResponseEntity<ApiResponse<Void>> retrievePartialPage() {
    return unauthorized();
  }

  private ResponseEntity<ApiResponse<Void>> unauthorized() {
    return ResponseEntity.ok(
        new ApiResponse<>("UNAUTH", UNAUTHORIZED_MESSAGE, null));
  }
}
