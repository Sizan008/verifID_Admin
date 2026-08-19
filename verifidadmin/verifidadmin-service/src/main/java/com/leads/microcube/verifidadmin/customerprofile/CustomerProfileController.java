package com.leads.microcube.verifidadmin.customerprofile;

import com.leads.microcube.verifidadmin.common.response.ApiResponse;
import com.leads.microcube.verifidadmin.common.security.FeatureAccessDeniedException;
import com.leads.microcube.verifidadmin.common.security.RequirePermission;
import com.leads.microcube.verifidadmin.customerprofile.command.CheckCustomerByBranchAdmin;
import com.leads.microcube.verifidadmin.customerprofile.command.DeclineCustomer;
import com.leads.microcube.verifidadmin.customerprofile.command.EnableCashTransaction;
import com.leads.microcube.verifidadmin.customerprofile.command.OpenCustomerAccount;
import com.leads.microcube.verifidadmin.customerprofile.command.RecordLoanBoAcceptReason;
import com.leads.microcube.verifidadmin.customerprofile.command.ReturnCustomer;
import com.leads.microcube.verifidadmin.customerprofile.command.SaveEddAnswers;
import com.leads.microcube.verifidadmin.customerprofile.command.SaveRiskGrading;
import com.leads.microcube.verifidadmin.customerprofile.command.UpdateCustomerServices;
import com.leads.microcube.verifidadmin.customerprofile.command.WithdrawDebitRestriction;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerActionResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerActivity;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerActivityPageResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerAuthTypeResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerBeneficiary;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerBeneficiaryResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerDetails;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerDetailsResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerDocument;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerDocumentResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerEddDetails;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerExport;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerExportResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerFilter;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerGuardian;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerGuardianResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerNominee;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerNomineeResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerPageResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerPayment;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerPaymentResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerPhotos;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerPhotosResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerProduct;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerProductResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerReport;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerReportResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerRiskScore;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerRiskScoreResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.DebitRestriction;
import com.leads.microcube.verifidadmin.customerprofile.query.EddAnswerResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.EddQuestionResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.EddQuestions;
import com.leads.microcube.verifidadmin.customerprofile.query.RiskGradingDetails;
import com.leads.microcube.verifidadmin.customerprofile.query.RiskGradingFormResponse;
import com.leads.microcube.verifidadmin.log.UserActivityLogService;
import com.leads.microcube.verifidadmin.log.command.RecordCurrentUserActivity;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/CustomerProfile")
@RequiredArgsConstructor
@Tag(name="Customer Profile")
public class CustomerProfileController {

  private static final int PAGE_SIZE = 8;

  private final CustomerProfileService customerProfileService;
  private final CustomerProfileQueryService customerProfileQueryService;
  private final CustomerProfilePermissionSupport permissionSupport;
  private final CustomerProfileSessionState sessionState;
  private final UserActivityLogService userActivityLogService;

  @GetMapping("/Index")
  @RequirePermission(targetPath = "CustomerProfile/Index")
  public ResponseEntity<ApiResponse<CustomerPageResponse>> retrieveCustomers(
      @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber) {
    String authType = sessionState.retrieveAuthType();
    CustomerPageResponse response = retrievePage(authType, "Index", pageNumber, false);
    recordActivity(0L, "Open", "is Opening Customer List of " + authType);
    return ResponseEntity.ok(ApiResponse.success(response));
  }

  @GetMapping("/Search")
  public ResponseEntity<ApiResponse<CustomerPageResponse>> searchCustomers(
      @RequestParam(value = "MobileNo", required = false) String mobileNo,
      @RequestParam(value = "TrackingNo", required = false) Long trackingNo,
      @RequestParam(value = "NidNo", required = false) String nidNo,
      @RequestParam(value = "Fullname", required = false) String fullName,
      @RequestParam(value = "CustomerId", required = false) String customerId,
      @RequestParam(value = "DateFrom", required = false)
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
      @RequestParam(value = "DateTo", required = false)
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo,
      @RequestParam(value = "accountNoFrom", required = false) String accountNoFrom,
      @RequestParam(value = "accountNoTo", required = false) String accountNoTo,
      @RequestParam(value = "SelectedBranchId", required = false) String selectedBranchId,
      @RequestParam(value = "ProductTypeId", required = false) String productTypeId,
      @RequestParam(value = "requestChannel", required = false) String requestChannel,
      @RequestParam(value = "gender", required = false) String gender,
      @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber) {
    CustomerFilter filter =
        CustomerFilter.builder()
            .authType(sessionState.retrieveAuthType())
            .mobileNo(mobileNo)
            .trackingNo(trackingNo)
            .nidNo(nidNo)
            .fullName(fullName)
            .customerId(customerId)
            .dateFrom(dateFrom)
            .dateTo(dateTo)
            .accountNoFrom(accountNoFrom)
            .accountNoTo(accountNoTo)
            .selectedBranchId(selectedBranchId)
            .productTypeId(productTypeId)
            .requestChannel(requestChannel)
            .gender(gender)
            .ownerOnly(false)
            .pageNumber(pageNumber)
            .pageSize(PAGE_SIZE)
            .model("Search")
            .build();
    return ResponseEntity.ok(
        ApiResponse.success(customerProfileQueryService.retrieveCustomers(filter)));
  }

  @GetMapping("/UnauthorizedCustomers")
  @RequirePermission(targetPath = "CustomerProfile/UnauthorizedCustomers")
  public ResponseEntity<ApiResponse<CustomerPageResponse>> retrieveUnauthorizedCustomers(
      @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber) {
    CustomerPageResponse response = retrievePage("U", "UnauthorizedCustomers", pageNumber, false);
    recordActivity(0L, "Open", "is Opening Unauthorized Customers List");
    return ResponseEntity.ok(ApiResponse.success(response));
  }

  @GetMapping("/AuthorizedCustomers")
  @RequirePermission(targetPath = "CustomerProfile/AuthorizedCustomers")
  public ResponseEntity<ApiResponse<CustomerPageResponse>> retrieveAuthorizedCustomers(
      @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber) {
    CustomerPageResponse response = retrievePage("A", "AuthorizedCustomers", pageNumber, false);
    recordActivity(0L, "Open", "is Opening Authorized Customers List");
    return ResponseEntity.ok(ApiResponse.success(response));
  }

  @GetMapping("/PendingBranchCustomers")
  @RequirePermission(targetPath = "CustomerProfile/PendingBranchCustomers")
  public ResponseEntity<ApiResponse<CustomerPageResponse>> retrievePendingBranchCustomers(
      @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber) {
    CustomerPageResponse response = retrievePage("A", "PendingBranchCustomers", pageNumber, true);
    recordActivity(0L, "Open", "is Opening UPending Branch Customers List");
    return ResponseEntity.ok(ApiResponse.success(response));
  }

  @GetMapping("/DeclinedCustomers")
  @RequirePermission(targetPath = "CustomerProfile/DeclinedCustomers")
  public ResponseEntity<ApiResponse<CustomerPageResponse>> retrieveDeclinedCustomers(
      @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber) {
    CustomerPageResponse response = retrievePage("D", "DeclinedCustomers", pageNumber, false);
    recordActivity(0L, "Open", "is Opening Declined Customers List");
    return ResponseEntity.ok(ApiResponse.success(response));
  }

  @GetMapping("/IncompleteCustomers")
  @RequirePermission(targetPath = "CustomerProfile/IncompleteCustomers")
  public ResponseEntity<ApiResponse<CustomerPageResponse>> retrieveIncompleteCustomers(
      @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber) {
    CustomerPageResponse response = retrievePage("I", "IncompleteCustomers", pageNumber, false);
    recordActivity(0L, "Open", "is Opening Incomplete Customers List");
    return ResponseEntity.ok(ApiResponse.success(response));
  }

  @GetMapping("/KYCReportByYear")
  @RequirePermission(targetPath = "CustomerProfile/KYCReportByYear")
  public ResponseEntity<ApiResponse<CustomerPageResponse>> retrieveKycReportByYear(
      @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber) {
    CustomerPageResponse response = retrievePage("A", "KYCReportByYear", pageNumber, false);
    recordActivity(0L, "Open", "is Opening Authorized Customers List");
    return ResponseEntity.ok(ApiResponse.success(response));
  }

  @GetMapping("/Details")
  public ResponseEntity<ApiResponse<CustomerDetailsResponse>> retrieveDetails(
      @RequestParam("id") Long id) {
    requireStoredViewPermission();
    return retrieveDetailsResponse(id, false, false, false, false);
  }

  @GetMapping("/DetailsMain")
  public ResponseEntity<ApiResponse<CustomerDetailsResponse>> retrieveMainDetails(
      @RequestParam("id") Long id) {
    requireStoredViewPermission();
    CustomerDetailsResponse response = retrieveDetails(id, true, true, true, false);
    recordActivity(id, "Open", "is Opening Customer Details");
    return ResponseEntity.ok(ApiResponse.success(response));
  }

  @GetMapping("/CustomerDetails")
  public ResponseEntity<ApiResponse<CustomerDetailsResponse>> retrieveCustomerDetails(
      @RequestParam("id") Long id) {
    requireStoredViewPermission();
    recordActivity(id, "Open", "1. CustomerDetails calling");
    CustomerDetailsResponse response = retrieveDetails(id, false, true, true, false);
    recordActivity(id, "Open", "is Opening Customer Details - " + response.getAuthStatus());
    return ResponseEntity.ok(ApiResponse.success(response));
  }

  @GetMapping("/PartnerDetails")
  public ResponseEntity<ApiResponse<CustomerDetailsResponse>> retrievePartnerDetails(
      @RequestParam("id") Long id) {
    requireStoredViewPermission();
    CustomerDetailsResponse response = retrieveDetails(id, false, false, false, false);
    recordActivity(id, "Open", "is Opening Partner Details - " + response.getAuthStatus());
    return ResponseEntity.ok(ApiResponse.success(response));
  }

  @GetMapping("/IncompleteDetails")
  public ResponseEntity<ApiResponse<CustomerDetailsResponse>> retrieveIncompleteDetails(
      @RequestParam("id") Long id) {
    requireStoredViewPermission();
    CustomerDetailsResponse response = retrieveDetails(id, false, false, false, true);
    recordActivity(id, "Open", "is Opening Incomplete Customer Details");
    return ResponseEntity.ok(ApiResponse.success(response));
  }

  @GetMapping("/PartialDetails")
  public ResponseEntity<ApiResponse<CustomerDetailsResponse>> retrievePartialDetails(
      @RequestParam("id") Long id) {
    return retrieveDetailsResponse(id, false, false, false, false);
  }

  @GetMapping("/CustomerDocument")
  public ResponseEntity<ApiResponse<CustomerDocumentResponse>> retrieveDocuments(
      @RequestParam("id") Long id) {
    return ResponseEntity.ok(
        ApiResponse.success(
            customerProfileQueryService.retrieveDocuments(new CustomerDocument(id))));
  }

  @GetMapping("/NomineeDetails")
  public ResponseEntity<ApiResponse<CustomerNomineeResponse>> retrieveNominee(
      @RequestParam("id") Long id,
      @RequestParam("nomineeNo") Integer nomineeNo) {
    return ResponseEntity.ok(
        ApiResponse.success(
            customerProfileQueryService.retrieveNominee(new CustomerNominee(id, nomineeNo))));
  }

  @GetMapping("/GuardianDetails")
  public ResponseEntity<ApiResponse<CustomerGuardianResponse>> retrieveGuardian(
      @RequestParam("id") Long id,
      @RequestParam("nomineeNo") Integer nomineeNo) {
    return ResponseEntity.ok(
        ApiResponse.success(
            customerProfileQueryService.retrieveGuardian(new CustomerGuardian(id, nomineeNo))));
  }

  @GetMapping("/BeneficiaryDetails")
  public ResponseEntity<ApiResponse<CustomerBeneficiaryResponse>> retrieveBeneficiary(
      @RequestParam("id") Long id,
      @RequestParam("benifno") Integer beneficiaryNo) {
    return ResponseEntity.ok(
        ApiResponse.success(
            customerProfileQueryService.retrieveBeneficiary(
                new CustomerBeneficiary(id, beneficiaryNo))));
  }

  @GetMapping("/ProductDetails")
  public ResponseEntity<ApiResponse<CustomerProductResponse>> retrieveProduct(
      @RequestParam("id") Long id) {
    return ResponseEntity.ok(
        ApiResponse.success(customerProfileQueryService.retrieveProduct(new CustomerProduct(id))));
  }

  @GetMapping("/SSLPaymentDetails")
  public ResponseEntity<ApiResponse<CustomerPaymentResponse>> retrievePayment(
      @RequestParam("id") Long id) {
    return ResponseEntity.ok(
        ApiResponse.success(customerProfileQueryService.retrievePayment(new CustomerPayment(id))));
  }

  @GetMapping("/CashTransactionEnable")
  public CustomerActionResponse enableCashTransaction(@RequestParam("id") Long id) {
    try {
      return customerProfileService.process(new EnableCashTransaction(id));
    } catch (RuntimeException exception) {
      return CustomerActionResponse.builder().result("Something is wrong").build();
    }
  }

  @GetMapping("/PhotosAndDocs")
  public ResponseEntity<ApiResponse<CustomerPhotosResponse>> retrievePhotos(
      @RequestParam("id") Long id) {
    return ResponseEntity.ok(
        ApiResponse.success(customerProfileQueryService.retrievePhotos(new CustomerPhotos(id))));
  }

  @GetMapping("/RiskGrading")
  public ResponseEntity<ApiResponse<RiskGradingFormResponse>> retrieveRiskGrading(
      @RequestParam("trackingNo") Long trackingNo) {
    return ResponseEntity.ok(
        ApiResponse.success(
            customerProfileQueryService.retrieveRiskGrading(new RiskGradingDetails(trackingNo))));
  }

  @PostMapping("/RiskGrading")
  public ResponseEntity<ApiResponse<Void>> saveRiskGrading(
      @Valid @RequestBody SaveRiskGrading command) {
    customerProfileService.process(command);
    return ResponseEntity.ok(ApiResponse.success("Successfully Saved", null));
  }

  @GetMapping("/CustomerRiskScore")
  public ResponseEntity<ApiResponse<CustomerRiskScoreResponse>> retrieveRiskScore(
      @RequestParam("TrackingNo") Long trackingNo) {
    return ResponseEntity.ok(
        ApiResponse.success(
            customerProfileQueryService.retrieveRiskScore(new CustomerRiskScore(trackingNo))));
  }

  @GetMapping("/EDDQuestions")
  public ResponseEntity<ApiResponse<List<EddQuestionResponse>>> retrieveEddQuestions(
      @RequestParam("trackingNo") Long trackingNo) {
    return ResponseEntity.ok(
        ApiResponse.success(
            customerProfileQueryService.retrieveEddQuestions(new EddQuestions(trackingNo))));
  }

  @PostMapping("/SaveEDDQuestions")
  public ResponseEntity<ApiResponse<Void>> saveEddAnswers(
      @Valid @RequestBody SaveEddAnswers command) {
    customerProfileService.process(command);
    return ResponseEntity.ok(ApiResponse.success("Successfully Saved", null));
  }

  @GetMapping("/CustomerEDDDTLS")
  public ResponseEntity<ApiResponse<List<EddAnswerResponse>>> retrieveEddDetails(
      @RequestParam("TrackingNo") Long trackingNo) {
    return ResponseEntity.ok(
        ApiResponse.success(
            customerProfileQueryService.retrieveEddDetails(new CustomerEddDetails(trackingNo))));
  }

  @GetMapping("/GenerateReport")
  public CustomerReportResponse generateReport(@RequestParam("trackingNo") Long trackingNo) {
    CustomerReportResponse response =
        customerProfileQueryService.retrieveReport(new CustomerReport(trackingNo));
    if (response.isSuccess()) {
      recordActivity(trackingNo, "Report", "is Generate Report");
    }
    return response;
  }

  @PostMapping("/DeclineAccount")
  public Map<String, String> declineCustomer(@RequestBody DeclineCustomer command) {
    customerProfileService.process(command);
    return Map.of("name", "test");
  }

  @PostMapping("/ReturnAccount")
  public Map<String, String> returnCustomer(@RequestBody ReturnCustomer command) {
    customerProfileService.process(command);
    return Map.of("name", "test");
  }

  @PostMapping("/LoanBOAcceptReason")
  public Map<String, String> recordLoanBoAcceptReason(
      @RequestParam("LBACReason") String reason,
      @RequestParam("trackingNo") Long trackingNo) {
    customerProfileService.process(new RecordLoanBoAcceptReason(trackingNo, reason));
    return Map.of("name", "test");
  }

  @GetMapping("/CheckedByBranchAdmin")
  public ResponseEntity<CustomerActionResponse> checkCustomerByBranchAdmin(
      @RequestParam("id") Long id) {
    try {
      customerProfileService.process(new CheckCustomerByBranchAdmin(id));
      return ResponseEntity.ok().build();
    } catch (RuntimeException exception) {
      return ResponseEntity.ok(
          CustomerActionResponse.builder()
              .message(exception.toString())
              .status("FAILED")
              .build());
    }
  }

  @GetMapping("/OpenAccount")
  public CustomerActionResponse openCustomerAccount(@RequestParam("id") Long id) {
    try {
      return customerProfileService.process(new OpenCustomerAccount(id));
    } catch (RuntimeException exception) {
      return CustomerActionResponse.builder().result("Something is wrong").build();
    }
  }

  @GetMapping("/WithdrawDrRestriction")
  public CustomerActionResponse withdrawDebitRestriction(
      @RequestParam("trackingNo") Long trackingNo) {
    return customerProfileService.process(new WithdrawDebitRestriction(trackingNo));
  }

  @GetMapping("/CheckDebitRestriction")
  public CustomerActionResponse checkDebitRestriction(
      @RequestParam("trackingNo") Long trackingNo) {
    try {
      CustomerActionResponse response =
          customerProfileQueryService.retrieveDebitRestriction(new DebitRestriction(trackingNo));
      recordActivity(trackingNo, "Check", "CheckDebitRestriction");
      return response;
    } catch (RuntimeException exception) {
      return CustomerActionResponse.builder()
          .result("Something went wrong for CheckDebitRestriction")
          .build();
    }
  }

  @GetMapping("/getAuthType")
  public CustomerAuthTypeResponse retrieveAuthType() {
    return customerProfileQueryService.retrieveAuthType();
  }

  @PostMapping("/UpdateServices")
  public CustomerActionResponse updateServices(
      @RequestParam("trackingNo") Long trackingNo,
      @RequestParam(value = "smsAlertFlag", required = false) Integer smsAlertFlag,
      @RequestParam(value = "emailAlertFlag", required = false) Integer emailAlertFlag,
      @RequestParam(value = "debitCardFlag", required = false) Integer debitCardFlag,
      @RequestParam(value = "chqBookFlag", required = false) Integer chequeBookFlag) {
    try {
      return customerProfileService.process(
          UpdateCustomerServices.builder()
              .trackingNo(trackingNo)
              .smsAlertFlag(smsAlertFlag)
              .emailAlertFlag(emailAlertFlag)
              .debitCardFlag(debitCardFlag)
              .chequeBookFlag(chequeBookFlag)
              .build());
    } catch (RuntimeException exception) {
      return CustomerActionResponse.builder()
          .message("Exception Occurred : " + exception)
          .status("FAILED")
          .build();
    }
  }

  @GetMapping("/UserLog")
  public ResponseEntity<ApiResponse<CustomerActivityPageResponse>> retrieveUserLog(
      @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber) {
    CustomerActivity query =
        CustomerActivity.builder().pageNumber(pageNumber).pageSize(PAGE_SIZE).build();
    return ResponseEntity.ok(
        ApiResponse.success(customerProfileQueryService.retrieveActivity(query)));
  }

  @GetMapping("/Excel")
  public CustomerExportResponse exportCustomers(
      @RequestParam(value = "MobileNo", required = false) String mobileNo,
      @RequestParam(value = "TrackingNo", required = false) Long trackingNo,
      @RequestParam(value = "NidNo", required = false) String nidNo,
      @RequestParam(value = "Fullname", required = false) String fullName,
      @RequestParam(value = "CustomerId", required = false) String customerId,
      @RequestParam(value = "DateFrom", required = false)
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
      @RequestParam(value = "DateTo", required = false)
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo,
      @RequestParam(value = "accountNoFrom", required = false) String accountNoFrom,
      @RequestParam(value = "accountNoTo", required = false) String accountNoTo,
      @RequestParam(value = "SelectedBranchId", required = false) String selectedBranchId,
      @RequestParam(value = "ProductTypeId", required = false) String productTypeId,
      @RequestParam(value = "requestChannel", required = false) String requestChannel,
      @RequestParam(value = "year", required = false) String year,
      @RequestParam(value = "onlyAccounts", defaultValue = "false") boolean onlyAccounts) {
    return customerProfileQueryService.retrieveExcel(
        CustomerExport.builder()
            .authType(sessionState.retrieveAuthType())
            .mobileNo(mobileNo)
            .trackingNo(trackingNo)
            .nidNo(nidNo)
            .fullName(fullName)
            .customerId(customerId)
            .dateFrom(dateFrom)
            .dateTo(dateTo)
            .accountNoFrom(accountNoFrom)
            .accountNoTo(accountNoTo)
            .selectedBranchId(selectedBranchId)
            .productTypeId(productTypeId)
            .requestChannel(requestChannel)
            .year(year)
            .onlyAccounts(onlyAccounts)
            .build());
  }

  @GetMapping("/ExcelDetails")
  public CustomerExportResponse exportDetails(
      @RequestParam(value = "DateFrom", required = false)
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
      @RequestParam(value = "DateTo", required = false)
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo,
      @RequestParam(value = "SelectedBranchId", required = false) String selectedBranchId) {
    return customerProfileQueryService.retrieveExcelDetails(
        CustomerExport.builder()
            .authType(sessionState.retrieveAuthType())
            .dateFrom(dateFrom)
            .dateTo(dateTo)
            .selectedBranchId(selectedBranchId)
            .build());
  }

  private CustomerPageResponse retrievePage(
      String authType,
      String actionName,
      int pageNumber,
      boolean pendingBranchAuthorization) {
    sessionState.store(authType, "CustomerProfile/" + actionName);
    return customerProfileQueryService.retrieveCustomers(
        CustomerFilter.builder()
            .authType(authType)
            .pageNumber(pageNumber)
            .pageSize(PAGE_SIZE)
            .ownerOnly(!pendingBranchAuthorization)
            .pendingBranchAuthorization(pendingBranchAuthorization)
            .model(actionName)
            .build());
  }

  private ResponseEntity<ApiResponse<CustomerDetailsResponse>> retrieveDetailsResponse(
      Long id,
      boolean resolveOwner,
      boolean checkDebitRestriction,
      boolean includeAdminSettings,
      boolean requireMobileForPhotos) {
    return ResponseEntity.ok(
        ApiResponse.success(
            retrieveDetails(
                id,
                resolveOwner,
                checkDebitRestriction,
                includeAdminSettings,
                requireMobileForPhotos)));
  }

  private CustomerDetailsResponse retrieveDetails(
      Long id,
      boolean resolveOwner,
      boolean checkDebitRestriction,
      boolean includeAdminSettings,
      boolean requireMobileForPhotos) {
    return customerProfileQueryService.retrieveCustomer(
        new CustomerDetails(
            id,
            resolveOwner,
            checkDebitRestriction,
            includeAdminSettings,
            requireMobileForPhotos));
  }

  private void requireStoredViewPermission() {
    if (!permissionSupport.hasViewPermission()) {
      throw new FeatureAccessDeniedException();
    }
  }

  private void recordActivity(Long trackingNo, String actionType, String particulars) {
    userActivityLogService.process(
        RecordCurrentUserActivity.builder()
            .trackingNo(trackingNo)
            .stepId(0)
            .actionType(actionType)
            .actionParticulars(particulars)
            .requestChannel("")
            .build());
  }
}
