package com.leads.microcube.verifidadmin.home;

import com.leads.microcube.verifidadmin.account.LegacyLicenceValidator;
import com.leads.microcube.verifidadmin.account.exception.AccountValidationException;
import com.leads.microcube.verifidadmin.common.security.CurrentUser;
import com.leads.microcube.verifidadmin.common.security.CurrentUserProvider;
import com.leads.microcube.verifidadmin.common.security.FeatureAccessDeniedException;
import com.leads.microcube.verifidadmin.customerprofile.CustomerProfileQueryService;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerDashboard;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerDashboardMetrics;
import com.leads.microcube.verifidadmin.home.exception.HomeValidationException;
import com.leads.microcube.verifidadmin.home.query.DashboardResponse;
import com.leads.microcube.verifidadmin.home.query.SubBranchResponse;
import com.leads.microcube.verifidadmin.log.UserActivityLogService;
import com.leads.microcube.verifidadmin.log.command.RecordCurrentUserActivity;
import com.leads.microcube.verifidadmin.parameterconfig.ParameterConfigQueryService;
import com.leads.microcube.verifidadmin.parameterconfig.exception.ParameterConfigNotFoundException;
import com.leads.microcube.verifidadmin.parameterconfig.query.ParameterDetails;
import com.leads.microcube.verifidadmin.parameterconfig.query.ParameterResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class HomeQueryServiceImpl implements HomeQueryService {

  private static final String ALL_BRANCH_ACCESS_KEY = "ALL_BRANCH_SUPERADMIN_FULL_ACCESS";
  private static final String HEAD_OFFICE_BRANCH_KEY = "HEAD_OFFICE_BRANCH_ID";
  private static final String LICENCE_DATE_KEY = "LICENCE_DATE";
  private static final DateTimeFormatter LICENCE_DATE_FORMAT =
      DateTimeFormatter.ofPattern("dd-MMM-uuuu", Locale.ENGLISH);

  private final CustomerProfileQueryService customerProfileQueryService;
  private final ParameterConfigQueryService parameterConfigQueryService;
  private final CurrentUserProvider currentUserProvider;
  private final LegacyLicenceValidator legacyLicenceValidator;
  private final UserActivityLogService userActivityLogService;

  @Override
  public DashboardResponse retrieveDashboard() {
    CurrentUser currentUser = currentUserProvider.getCurrentUser();
    String branchId = requireBranchId(currentUser.getHomeBranchId());
    String headOfficeBranchId = retrieveHeadOfficeBranchId();
    recordDashboardActivity();

    LocalDateTime startDate = LocalDate.now().withDayOfMonth(1).atStartOfDay();
    LocalDateTime endDate = startDate.plusMonths(1).minusDays(1);
    CustomerDashboardMetrics metrics =
        customerProfileQueryService.retrieveDashboardMetrics(
            CustomerDashboard.builder()
                .branchId(branchId)
                .headOffice(headOfficeBranchId.equals(branchId))
                .startDate(startDate)
                .endDate(endDate)
                .build());

    return DashboardResponse.builder()
        .totalCustomers(metrics.getTotalCustomers())
        .totalAuthorizedCustomers(metrics.getTotalAuthorizedCustomers())
        .totalUnauthorizedCustomers(metrics.getTotalUnauthorizedCustomers())
        .totalVerifiedCustomers(metrics.getTotalVerifiedCustomers())
        .regularEkycCount(metrics.getRegularEkycCount())
        .simplifiedEkycCount(metrics.getSimplifiedEkycCount())
        .currentMonthCount(metrics.getCurrentMonthCount())
        .licenceError(retrieveLicenceWarning())
        .build();
  }

  @Override
  public SubBranchResponse retrieveSubBranchStatus() {
    CurrentUser currentUser = currentUserProvider.getCurrentUser();
    String branchId = requireBranchId(currentUser.getHomeBranchId());
    String allBranchAccess = retrieveRequiredSetting(ALL_BRANCH_ACCESS_KEY);
    if ("TRUE".equalsIgnoreCase(allBranchAccess)) {
      return new SubBranchResponse(false);
    }
    return new SubBranchResponse(!retrieveHeadOfficeBranchId().equals(branchId));
  }

  @Override
  public String retrieveHeadOfficeBranchId() {
    try {
      String value = retrieveOptionalSetting(HEAD_OFFICE_BRANCH_KEY);
      return value == null ? "" : value;
    } catch (RuntimeException exception) {
      log.warn("Unable to retrieve head-office branch ID.", exception);
      return "";
    }
  }

  private String retrieveLicenceWarning() {
    String encryptedLicenceDate = retrieveOptionalSetting(LICENCE_DATE_KEY);
    if (!StringUtils.hasText(encryptedLicenceDate)) {
      return "Licence is missing.";
    }

    LocalDate licenceDate;
    try {
      licenceDate = legacyLicenceValidator.decodeLicenceDate(encryptedLicenceDate);
    } catch (AccountValidationException exception) {
      throw new HomeValidationException(exception.getMessage(), exception);
    }

    LocalDate today = LocalDate.now();
    if (today.plusDays(30).isBefore(licenceDate)) {
      return null;
    }
    long daysRemaining = ChronoUnit.DAYS.between(today, licenceDate);
    return "Licence will expire on "
        + licenceDate.format(LICENCE_DATE_FORMAT)
        + " . You have only "
        + daysRemaining
        + " days remaing.";
  }

  private String retrieveRequiredSetting(String key) {
    String value = retrieveOptionalSetting(key);
    if (!StringUtils.hasText(value)) {
      throw new HomeValidationException(key + " is missing.");
    }
    return value;
  }

  private String retrieveOptionalSetting(String key) {
    try {
      ParameterResponse response =
          parameterConfigQueryService.retrieveParameter(new ParameterDetails(key));
      if (response == null || !StringUtils.hasText(response.getParamValue())) {
        return null;
      }
      return response.getParamValue().trim();
    } catch (ParameterConfigNotFoundException exception) {
      return null;
    }
  }

  private String requireBranchId(String branchId) {
    if (!StringUtils.hasText(branchId)) {
      throw new FeatureAccessDeniedException();
    }
    return branchId.trim();
  }

  private void recordDashboardActivity() {
    userActivityLogService.process(
        RecordCurrentUserActivity.builder()
            .trackingNo(0L)
            .stepId(0)
            .actionType("Index")
            .actionParticulars("is Opening Dashboard")
            .requestChannel("")
            .build());
  }
}
