package com.leads.microcube.verifidadmin.report;

import com.leads.microcube.verifidadmin.report.query.MergedCustomerPhoto;
import com.leads.microcube.verifidadmin.report.query.MergedCustomerPhotoResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Report")
@RequiredArgsConstructor
@Tag(name="Report")
public class ReportController {

  private final ReportQueryService reportQueryService;

  @GetMapping("/Index")
  public ResponseEntity<Void> retrieveIndex() {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }

  @GetMapping("/GetMergedCustPhotoAndSignature")
  public ResponseEntity<MergedCustomerPhotoResponse> retrieveMergedCustomerPhotoAndSignature(
      @RequestParam("trackingNo") String trackingNo) {
    return ResponseEntity.ok(
        reportQueryService.retrieveMergedCustomerPhoto(new MergedCustomerPhoto(trackingNo)));
  }

  @GetMapping("/Pdf")
  public ResponseEntity<Void> retrievePdf() {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }
}
