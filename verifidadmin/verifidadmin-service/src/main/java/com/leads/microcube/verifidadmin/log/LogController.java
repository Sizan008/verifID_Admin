package com.leads.microcube.verifidadmin.log;

import com.leads.microcube.verifidadmin.common.response.ApiResponse;
import com.leads.microcube.verifidadmin.log.query.UserActivityLogExportResponse;
import com.leads.microcube.verifidadmin.log.query.UserActivityLogFilter;
import com.leads.microcube.verifidadmin.log.query.UserActivityLogPageResponse;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Log")
@RequiredArgsConstructor
public class LogController {

  private final UserActivityLogQueryService userActivityLogQueryService;

  @GetMapping("/Index")
  public ResponseEntity<ApiResponse<UserActivityLogPageResponse>> retrieveUserActivities(
      @RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber) {
    UserActivityLogFilter filter =
        UserActivityLogFilter.builder().pageNumber(pageNumber).pageSize(8).build();
    UserActivityLogPageResponse activities =
        userActivityLogQueryService.retrieveUserActivities(filter);
    return ResponseEntity.ok(ApiResponse.success(activities));
  }

  @GetMapping("/LogList")
  public ResponseEntity<ApiResponse<UserActivityLogPageResponse>> retrieveUserActivityPage(
      @RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber) {
    UserActivityLogFilter filter =
        UserActivityLogFilter.builder().pageNumber(pageNumber).pageSize(8).build();
    UserActivityLogPageResponse activities =
        userActivityLogQueryService.retrieveUserActivities(filter);
    return ResponseEntity.ok(ApiResponse.success(activities));
  }

  @GetMapping("/Search")
  public ResponseEntity<ApiResponse<UserActivityLogPageResponse>> searchUserActivities(
      @RequestParam(name = "TrackingNo", required = false) Long trackingNo,
      @RequestParam(name = "UserId", required = false) String userId,
      @RequestParam(name = "DateFrom", required = false)
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
          LocalDate dateFrom,
      @RequestParam(name = "DateTo", required = false)
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
          LocalDate dateTo,
      @RequestParam(name = "RequestChannel", required = false) String requestChannel,
      @RequestParam(name = "BranchId", required = false) String branchId,
      @RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
      @RequestParam(name = "customersPerPage", defaultValue = "8") int customersPerPage) {
    UserActivityLogFilter filter =
        createFilter(
            trackingNo,
            userId,
            dateFrom,
            dateTo,
            requestChannel,
            branchId,
            pageNumber,
            customersPerPage);
    UserActivityLogPageResponse activities =
        userActivityLogQueryService.retrieveUserActivities(filter);
    return ResponseEntity.ok(ApiResponse.success(activities));
  }

  @GetMapping("/Excel")
  public ResponseEntity<UserActivityLogExportResponse> retrieveUserActivityExcel(
      @RequestParam(name = "TrackingNo", required = false) Long trackingNo,
      @RequestParam(name = "UserId", required = false) String userId,
      @RequestParam(name = "DateFrom", required = false)
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
          LocalDate dateFrom,
      @RequestParam(name = "DateTo", required = false)
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
          LocalDate dateTo) {
    UserActivityLogFilter filter =
        createFilter(trackingNo, userId, dateFrom, dateTo, null, null, 1, 8);
    return ResponseEntity.ok(
        userActivityLogQueryService.retrieveUserActivityExcel(filter));
  }

  private UserActivityLogFilter createFilter(
      Long trackingNo,
      String userId,
      LocalDate dateFrom,
      LocalDate dateTo,
      String requestChannel,
      String branchId,
      int pageNumber,
      int pageSize) {
    return UserActivityLogFilter.builder()
        .trackingNo(trackingNo)
        .userId(userId)
        .dateFrom(dateFrom)
        .dateTo(dateTo)
        .requestChannel(requestChannel)
        .branchId(branchId)
        .pageNumber(pageNumber)
        .pageSize(pageSize)
        .build();
  }
}
