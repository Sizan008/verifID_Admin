package com.leads.microcube.verifidadmin.report;

import com.leads.microcube.verifidadmin.report.query.MergedCustomerPhoto;
import com.leads.microcube.verifidadmin.report.query.MergedCustomerPhotoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Report")
@RequiredArgsConstructor
public class ReportController {

  private final ReportQueryService reportQueryService;

  @GetMapping("/GetMergedCustPhotoAndSignature")
  public ResponseEntity<MergedCustomerPhotoResponse> retrieveMergedCustomerPhotoAndSignature(
      @RequestParam("trackingNo") String trackingNo) {
    return ResponseEntity.ok(
        reportQueryService.retrieveMergedCustomerPhoto(new MergedCustomerPhoto(trackingNo)));
  }
}
