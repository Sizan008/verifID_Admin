package com.leads.microcube.verifidadmin.customerprofile;

import com.leads.microcube.verifidadmin.common.security.CurrentUser;
import com.leads.microcube.verifidadmin.common.security.CurrentUserProvider;
import com.leads.microcube.verifidadmin.customerprofile.client.CustomerProfileGateway;
import com.leads.microcube.verifidadmin.customerprofile.client.CustomerProfileImageFallback;
import com.leads.microcube.verifidadmin.customerprofile.client.CustomerProfilePhotoGateway;
import com.leads.microcube.verifidadmin.customerprofile.exception.CustomerProfileNotFoundException;
import com.leads.microcube.verifidadmin.customerprofile.exception.CustomerProfileValidationException;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerActionResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerActivity;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerActivityPageResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerAuthTypeResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerBeneficiary;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerBeneficiaryResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerDashboard;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerDashboardMetrics;
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
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerListItem;
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
import com.leads.microcube.verifidadmin.customerprofile.query.DocumentFileResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.EddAnswerResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.EddQuestionResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.EddQuestions;
import com.leads.microcube.verifidadmin.customerprofile.query.ProductTypeOption;
import com.leads.microcube.verifidadmin.customerprofile.query.RiskGradingDetails;
import com.leads.microcube.verifidadmin.customerprofile.query.RiskGradingFormResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.RiskGradingResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.RiskOption;
import com.leads.microcube.verifidadmin.customerprofile.repository.BranchOfficeEntity;
import com.leads.microcube.verifidadmin.customerprofile.repository.BranchOfficeRepository;
import com.leads.microcube.verifidadmin.customerprofile.repository.CrgAverageYearTransactionEntity;
import com.leads.microcube.verifidadmin.customerprofile.repository.CrgAverageYearTransactionRepository;
import com.leads.microcube.verifidadmin.customerprofile.repository.CrgBusinessProfessionTypeEntity;
import com.leads.microcube.verifidadmin.customerprofile.repository.CrgBusinessProfessionTypeRepository;
import com.leads.microcube.verifidadmin.customerprofile.repository.CrgEddRepository;
import com.leads.microcube.verifidadmin.customerprofile.repository.CrgOnboardingTypeEntity;
import com.leads.microcube.verifidadmin.customerprofile.repository.CrgOnboardingTypeRepository;
import com.leads.microcube.verifidadmin.customerprofile.repository.CrgProductTypeEntity;
import com.leads.microcube.verifidadmin.customerprofile.repository.CrgProductTypeRepository;
import com.leads.microcube.verifidadmin.customerprofile.repository.CrgResidentTypeEntity;
import com.leads.microcube.verifidadmin.customerprofile.repository.CrgResidentTypeRepository;
import com.leads.microcube.verifidadmin.customerprofile.repository.CrgValueEntity;
import com.leads.microcube.verifidadmin.customerprofile.repository.CrgValueRepository;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerAdditionalInfoDetailRepository;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerBeneficialOwnerEntity;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerBeneficialOwnerRepository;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerBoDetailEntity;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerBoDetailRepository;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerDocumentEntity;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerDocumentRepository;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerEddDetailRepository;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerLoanDetailEntity;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerLoanDetailRepository;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerNomineeEntity;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerNomineeRepository;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerProfileEntity;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerProfileRepository;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerRiskGradeEntity;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerRiskGradeRepository;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerSchemeAccountEntity;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerSchemeAccountRepository;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerSslCommerzPaymentEntity;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerSslCommerzPaymentRepository;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerTimeAccountEntity;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerTimeAccountRepository;
import com.leads.microcube.verifidadmin.customerprofile.repository.NomineeGuardianEntity;
import com.leads.microcube.verifidadmin.customerprofile.repository.NomineeGuardianRepository;
import com.leads.microcube.verifidadmin.log.query.UserActivityLogResponse;
import com.leads.microcube.verifidadmin.log.repository.UserActivityLogEntity;
import com.leads.microcube.verifidadmin.log.repository.UserActivityLogRepository;
import com.leads.microcube.verifidadmin.parameterconfig.ParameterConfigQueryService;
import com.leads.microcube.verifidadmin.parameterconfig.query.ParameterDetails;
import com.leads.microcube.verifidadmin.parameterconfig.query.ParameterResponse;
import com.leads.microcube.verifidadmin.product.repository.ProductEntity;
import com.leads.microcube.verifidadmin.product.repository.ProductRepository;
import com.leads.microcube.verifidadmin.product.repository.ProductTypeEntity;
import com.leads.microcube.verifidadmin.product.repository.ProductTypeRepository;
import jakarta.persistence.criteria.Predicate;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CustomerProfileQueryServiceImpl implements CustomerProfileQueryService {

  private static final int DEFAULT_PAGE_SIZE = 8;
  private static final String AUTHORIZED_STATUS = "A";
  private static final String DECLINED_STATUS = "D";
  private static final String INCOMPLETE_STATUS = "I";
  private static final String UNAUTHORIZED_STATUS = "U";
  private static final int REGULAR_EKYC = 2;
  private static final int SIMPLIFIED_EKYC = 1;
  private static final String HEAD_OFFICE_BRANCH_ID = "HEAD_OFFICE_BRANCH_ID";
  private static final String SDN_SCORE = "SDN_SCORE";
  private static final String SSL_PAYMENT_ENABLE = "SSL_COMMERZ_PAYMENT_ENABLE";
  private static final String SSL_ALLOWED_PRODUCT_TYPES = "SSL_COMMERZ_ALLOWED_PRODUCTS_TYPES";
  private static final String CAMLCO_WARNING = "CAMLCO_APPROVAL_WARNING";
  private static final String CAMLCO_WARNING_MESSAGE = "CAMLCO_APPROVAL_WARNING_MSG";
  private static final String RISK_GRADING_DETAILS = "RISK_GRADING_DETAILS";
  private static final String EDD_DETAILS = "EDD_DETAILS";
  private static final String SHOW_ALERTS_ADMIN = "SHOW_ALERTS_ADMIN";
  private static final String SHOW_ALERTS_ADMIN_ALL_CHANNELS = "SHOW_ALERTS_ADMIN_ALL_CHANNELS";
  private static final String RISK_GRADING_SHOW_ALL_EKYC = "RISK_GRADING_SHOW_ALL_EKYC";
  private static final String CUS_RETURN_PREVIOUS_STEPS = "CUS_RETURN_PREVIOUS_STEPS";

  private final CustomerProfileRepository customerProfileRepository;
  private final CustomerNomineeRepository customerNomineeRepository;
  private final NomineeGuardianRepository nomineeGuardianRepository;
  private final CustomerBeneficialOwnerRepository customerBeneficialOwnerRepository;
  private final CustomerDocumentRepository customerDocumentRepository;
  private final CustomerAdditionalInfoDetailRepository additionalInfoRepository;
  private final BranchOfficeRepository branchOfficeRepository;
  private final CustomerLoanDetailRepository customerLoanDetailRepository;
  private final CustomerBoDetailRepository customerBoDetailRepository;
  private final CustomerSchemeAccountRepository customerSchemeAccountRepository;
  private final CustomerTimeAccountRepository customerTimeAccountRepository;
  private final CustomerSslCommerzPaymentRepository paymentRepository;
  private final CustomerRiskGradeRepository customerRiskGradeRepository;
  private final CustomerEddDetailRepository customerEddDetailRepository;
  private final CrgAverageYearTransactionRepository averageYearTransactionRepository;
  private final CrgBusinessProfessionTypeRepository businessProfessionTypeRepository;
  private final CrgOnboardingTypeRepository onboardingTypeRepository;
  private final CrgResidentTypeRepository residentTypeRepository;
  private final CrgProductTypeRepository crgProductTypeRepository;
  private final CrgValueRepository crgValueRepository;
  private final CrgEddRepository crgEddRepository;
  private final ProductRepository productRepository;
  private final ProductTypeRepository productTypeRepository;
  private final UserActivityLogRepository userActivityLogRepository;
  private final ParameterConfigQueryService parameterConfigQueryService;
  private final CurrentUserProvider currentUserProvider;
  private final CustomerProfileSessionState sessionState;
  private final CustomerProfilePermissionSupport permissionSupport;
  private final CustomerProfilePhotoGateway photoGateway;
  private final CustomerProfileGateway customerProfileGateway;
  private final CustomerProfileReportService reportService;
  private final CustomerProfileSettings settings;
  private final CustomerProfileMapper mapper;

  @Override
  public CustomerDashboardMetrics retrieveDashboardMetrics(CustomerDashboard query) {
    if (query == null || query.getStartDate() == null || query.getEndDate() == null) {
      throw new CustomerProfileValidationException("Dashboard date range is required.");
    }
    if (query.isHeadOffice()) {
      return retrieveHeadOfficeDashboardMetrics(query);
    }
    if (!StringUtils.hasText(query.getBranchId())) {
      throw new CustomerProfileValidationException("Branch ID is required.");
    }
    return retrieveBranchDashboardMetrics(query);
  }

  private CustomerDashboardMetrics retrieveHeadOfficeDashboardMetrics(CustomerDashboard query) {
    long totalCustomers =
        customerProfileRepository.count()
            - customerProfileRepository.countByAuthStatus(DECLINED_STATUS);
    return CustomerDashboardMetrics.builder()
        .totalCustomers(totalCustomers)
        .totalAuthorizedCustomers(
            customerProfileRepository.countByAuthStatus(AUTHORIZED_STATUS))
        .totalUnauthorizedCustomers(
            customerProfileRepository.countByAuthStatus(UNAUTHORIZED_STATUS))
        .totalVerifiedCustomers(
            customerProfileRepository.countByAuthStatus(INCOMPLETE_STATUS))
        .regularEkycCount(customerProfileRepository.countByEkycFlag(REGULAR_EKYC))
        .simplifiedEkycCount(customerProfileRepository.countByEkycFlag(SIMPLIFIED_EKYC))
        .currentMonthCount(
            customerProfileRepository.countByUpdateDtBetween(
                query.getStartDate(), query.getEndDate()))
        .build();
  }

  private CustomerDashboardMetrics retrieveBranchDashboardMetrics(CustomerDashboard query) {
    String branchId = query.getBranchId().trim();
    long totalCustomers =
        customerProfileRepository.countByBranchId(branchId)
            - customerProfileRepository.countByBranchIdAndAuthStatus(
                branchId, DECLINED_STATUS);
    return CustomerDashboardMetrics.builder()
        .totalCustomers(totalCustomers)
        .totalAuthorizedCustomers(
            customerProfileRepository.countByBranchIdAndAuthStatus(
                branchId, AUTHORIZED_STATUS))
        .totalUnauthorizedCustomers(
            customerProfileRepository.countByBranchIdAndAuthStatus(
                branchId, UNAUTHORIZED_STATUS))
        .totalVerifiedCustomers(
            customerProfileRepository.countByBranchIdAndAuthStatus(
                branchId, INCOMPLETE_STATUS))
        .regularEkycCount(
            customerProfileRepository.countByBranchIdAndEkycFlag(branchId, REGULAR_EKYC))
        .simplifiedEkycCount(
            customerProfileRepository.countByBranchIdAndEkycFlag(
                branchId, SIMPLIFIED_EKYC))
        .currentMonthCount(
            customerProfileRepository.countByBranchIdAndUpdateDtBetween(
                branchId, query.getStartDate(), query.getEndDate()))
        .build();
  }

  @Override
  public CustomerPageResponse retrieveCustomers(CustomerFilter filter) {
    CustomerFilter request = filter == null ? new CustomerFilter() : filter;
    String authType = resolveAuthType(request.getAuthType());
    CurrentUser currentUser = currentUserProvider.getCurrentUser();
    String branchId = resolveBranch(request, currentUser);
    boolean headOffice = sameText(branchId, retrieveSetting(HEAD_OFFICE_BRANCH_ID));
    int pageNumber = Math.max(request.getPageNumber(), 1);
    int pageSize = request.getPageSize() <= 0 ? DEFAULT_PAGE_SIZE : request.getPageSize();

    Specification<CustomerProfileEntity> specification =
        buildCustomerSpecification(request, authType, branchId, headOffice, currentUser);
    Sort sort = "AuthorizedCustomers".equalsIgnoreCase(request.getModel())
        ? Sort.by(Sort.Direction.DESC, "trackingNo")
        : Sort.by(Sort.Direction.DESC, "makeDt");
    Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
    Page<CustomerProfileEntity> page = customerProfileRepository.findAll(specification, pageable);
    List<CustomerListItem> customers = page.getContent().stream().map(mapper::toListItem).toList();
    enrichProductDetails(customers);

    return CustomerPageResponse.builder()
        .customers(customers)
        .productTypes(retrieveProductTypes())
        .pageNumber(pageNumber)
        .pageSize(pageSize)
        .totalCount(page.getTotalElements())
        .totalPages(page.getTotalPages())
        .authType(authType)
        .headOffice(headOffice)
        .model(request.getModel())
        .build();
  }

  @Override
  public CustomerDetailsResponse retrieveCustomer(CustomerDetails query) {
    Long trackingNo = requireTrackingNo(query == null ? null : query.getTrackingNo());
    CustomerProfileEntity requested = retrieveProfile(trackingNo);
    CustomerProfileEntity profile = requested;
    if (query.isResolveOwner()
        && isJoint(requested)
        && requested.getReferenceNo() != null
        && requested.getReferenceNo() > 0) {
      profile = retrieveProfile(requested.getReferenceNo());
    }

    boolean loadPhotos =
        !query.isRequireMobileForPhotos() || StringUtils.hasText(profile.getMobileNo());
    CustomerDetailsResponse response = mapDetails(profile, loadPhotos);
    if (query.isIncludeAdminSettings()) {
      response.setRiskGradingDetails(retrieveSetting(RISK_GRADING_DETAILS));
      response.setEddDetails(retrieveSetting(EDD_DETAILS));
      response.setShowAlerts(retrieveUppercaseSetting(SHOW_ALERTS_ADMIN));
      response.setShowAlertsAllChannels(retrieveUppercaseSetting(SHOW_ALERTS_ADMIN_ALL_CHANNELS));
      response.setShowRiskGradeAllEkyc(retrieveUppercaseSetting(RISK_GRADING_SHOW_ALL_EKYC));
      response.setReturnThisCustomerToPreviousStep(
          retrieveUppercaseSetting(CUS_RETURN_PREVIOUS_STEPS));
    }
    if (isJoint(profile)) {
      List<CustomerDetailsResponse> partners = retrieveJointPartnerResponses(profile);
      response.setJointPartners(partners);
      BigDecimal maximumRisk = partners.stream()
          .map(CustomerDetailsResponse::getRiskGrading)
          .filter(Objects::nonNull)
          .max(Comparator.naturalOrder())
          .orElse(response.getRiskGrading());
      if (response.getRiskGrading() != null
          && (maximumRisk == null || response.getRiskGrading().compareTo(maximumRisk) > 0)) {
        maximumRisk = response.getRiskGrading();
      }
      response.setMaximumRiskGrading(maximumRisk);
    }

    CustomerPaymentResponse payment = retrievePaymentForProfile(profile);
    response.setSslPaymentEnabled(isSslPaymentEnabled(profile, payment));
    if (response.isSslPaymentEnabled() && !isValidPayment(payment)) {
      response.setAuthPermission(false);
    }
    if (query.isCheckDebitRestriction()) {
      response.setDebitRestriction(
          customerProfileGateway.checkDebitRestriction(profile.getTrackingNo()));
    }
    return response;
  }

  @Override
  public CustomerNomineeResponse retrieveNominee(CustomerNominee query) {
    Long trackingNo = requireTrackingNo(query == null ? null : query.getTrackingNo());
    Integer nomineeNo =
        requirePositive(query == null ? null : query.getNomineeNo(), "nominee number");
    Optional<CustomerNomineeEntity> entity =
        customerNomineeRepository.findFirstByTrackingNoAndNomineeNo(trackingNo, nomineeNo);
    if (entity.isEmpty()) {
      return emptyNomineeResponse();
    }
    CustomerNomineeResponse response = mapper.toNominee(entity.get());
    response.setNomineePhoto(photoGateway.retrieveNomineePhoto(trackingNo, nomineeNo));
    response.setNomineeNidFront(photoGateway.retrieveNomineeNidFront(trackingNo, nomineeNo));
    response.setNomineeNidBack(photoGateway.retrieveNomineeNidBack(trackingNo, nomineeNo));
    return response;
  }

  @Override
  public CustomerGuardianResponse retrieveGuardian(CustomerGuardian query) {
    Long trackingNo = requireTrackingNo(query == null ? null : query.getTrackingNo());
    Integer nomineeNo =
        requirePositive(query == null ? null : query.getNomineeNo(), "nominee number");
    Optional<NomineeGuardianEntity> entity =
        nomineeGuardianRepository.findFirstByTrackingNoAndNomineeNo(trackingNo, nomineeNo);
    if (entity.isEmpty()) {
      return emptyGuardianResponse();
    }
    CustomerGuardianResponse response = mapper.toGuardian(entity.get());
    response.setGuardianPhoto(photoGateway.retrieveGuardianPhoto(trackingNo, nomineeNo));
    response.setGuardianNidFront(photoGateway.retrieveGuardianNidFront(trackingNo, nomineeNo));
    response.setGuardianNidBack(photoGateway.retrieveGuardianNidBack(trackingNo, nomineeNo));
    return response;
  }

  @Override
  public CustomerBeneficiaryResponse retrieveBeneficiary(CustomerBeneficiary query) {
    Long trackingNo = requireTrackingNo(query == null ? null : query.getTrackingNo());
    Integer beneficiaryNo =
        requirePositive(query == null ? null : query.getBeneficiaryNo(), "beneficiary number");
    Optional<CustomerBeneficialOwnerEntity> entity =
        customerBeneficialOwnerRepository.findFirstByTrackingNoAndBenifOwnerNo(
            trackingNo, beneficiaryNo);
    if (entity.isEmpty()) {
      return emptyBeneficiaryResponse();
    }
    CustomerBeneficiaryResponse response = mapper.toBeneficiary(entity.get());
    response.setBeneficiaryIdPhoto(
        photoGateway.retrieveBeneficiaryPhoto(trackingNo, beneficiaryNo));
    response.setBeneficiaryIdFront(
        photoGateway.retrieveBeneficiaryNidFront(trackingNo, beneficiaryNo));
    response.setBeneficiaryIdBack(
        photoGateway.retrieveBeneficiaryNidBack(trackingNo, beneficiaryNo));
    return response;
  }

  @Override
  public CustomerDocumentResponse retrieveDocuments(CustomerDocument query) {
    Long trackingNo = requireTrackingNo(query == null ? null : query.getTrackingNo());
    List<DocumentFileResponse> documents = customerDocumentRepository
        .findAllByTrackingNoOrderByDocumentCodeAsc(trackingNo).stream()
        .map(this::mapDocument)
        .toList();
    return CustomerDocumentResponse.builder().documentList(documents).build();
  }

  @Override
  public CustomerProductResponse retrieveProduct(CustomerProduct query) {
    Long trackingNo = requireTrackingNo(query == null ? null : query.getTrackingNo());
    CustomerProfileEntity profile = retrieveProfile(trackingNo);
    ProductEntity product = retrieveProductEntity(profile.getProductId()).orElse(null);
    String productTypeId = product == null ? "00001" : product.getProductType();
    String productTypeName = retrieveProductTypeName(productTypeId);
    Map<String, Object> details = new LinkedHashMap<>();

    if ("00002".equals(productTypeId)) {
      customerSchemeAccountRepository.findFirstByTrackingNo(trackingNo)
          .ifPresent(entity -> mapSchemeDetails(details, entity));
    } else if ("00003".equals(productTypeId)) {
      customerTimeAccountRepository.findFirstByTrackingNo(trackingNo)
          .ifPresent(entity -> mapTimeDetails(details, entity));
    } else if ("00004".equals(productTypeId)) {
      customerLoanDetailRepository.findFirstByTrackingNo(trackingNo)
          .ifPresent(entity -> mapLoanDetails(details, entity));
    } else if ("00005".equals(productTypeId)) {
      customerBoDetailRepository.findFirstByTrackingNo(trackingNo)
          .ifPresent(entity -> mapBoDetails(details, entity));
    }

    return CustomerProductResponse.builder()
        .trackingNo(trackingNo)
        .productTypeId(productTypeId)
        .productTypeName(productTypeName)
        .productName(product == null ? null : product.getProductName())
        .details(details)
        .build();
  }

  @Override
  public CustomerPaymentResponse retrievePayment(CustomerPayment query) {
    Long trackingNo = requireTrackingNo(query == null ? null : query.getTrackingNo());
    Optional<CustomerProfileEntity> profile = customerProfileRepository.findById(trackingNo);
    if (profile.isEmpty()) {
      return CustomerPaymentResponse.builder().trackingNo(trackingNo).build();
    }
    CustomerPaymentResponse response = retrievePaymentForProfile(profile.get());
    if (response == null) {
      return CustomerPaymentResponse.builder().trackingNo(trackingNo).build();
    }
    if (response.getTrackingNo() == null || response.getTrackingNo() == 0) {
      response.setTrackingNo(trackingNo);
    }
    return response;
  }

  @Override
  public CustomerPhotosResponse retrievePhotos(CustomerPhotos query) {
    Long trackingNo = requireTrackingNo(query == null ? null : query.getTrackingNo());
    return photoGateway.retrieveCustomerPhotos(trackingNo);
  }

  @Override
  public RiskGradingFormResponse retrieveRiskGrading(RiskGradingDetails query) {
    Long trackingNo = requireTrackingNo(query == null ? null : query.getTrackingNo());
    CustomerProfileEntity profile = retrieveProfile(trackingNo);
    RiskGradingResponse risk = customerRiskGradeRepository.findFirstByTrackingNo(trackingNo)
        .map(this::mapRiskGrading)
        .orElse(RiskGradingResponse.builder().trackingNo(trackingNo).build());
    boolean showCamlcoWarning = profile.getRiskGrading() == null
        || profile.getRiskGrading().compareTo(BigDecimal.ZERO) <= 0;
    return RiskGradingFormResponse.builder()
        .riskGrading(risk)
        .averageYearTransactions(
            averageYearTransactionRepository.findAllByOrderByAvgYearTransIdAsc().stream()
                .map(entity -> option(entity.getAvgYearTransId(), entity.getAvgYearTransNm()))
                .toList())
        .businessTypes(
            businessProfessionTypeRepository.findAllByBizProfFlagOrderByBizProfTypeIdAsc(1).stream()
                .map(entity -> option(entity.getBizProfTypeId(), entity.getBizProfTypeNm()))
                .toList())
        .professionTypes(
            businessProfessionTypeRepository.findAllByBizProfFlagOrderByBizProfTypeIdAsc(0).stream()
                .map(entity -> option(entity.getBizProfTypeId(), entity.getBizProfTypeNm()))
                .toList())
        .onboardingTypes(onboardingTypeRepository.findAllByOrderByOnboardTypeIdAsc().stream()
            .map(entity -> option(entity.getOnboardTypeId(), entity.getOnboardTypeNm())).toList())
        .residentTypes(residentTypeRepository.findAllByOrderByResidentTypeIdAsc().stream()
            .map(entity -> option(entity.getResidentTypeId(), entity.getResidentTypeNm())).toList())
        .productTypes(crgProductTypeRepository.findAllByOrderByProductTypeIdAsc().stream()
            .map(entity -> option(entity.getProductTypeId(), entity.getProductTypeNm())).toList())
        .camlcoApprovalWarning(showCamlcoWarning ? retrieveSetting(CAMLCO_WARNING) : null)
        .camlcoApprovalWarningMessage(
            showCamlcoWarning ? retrieveSetting(CAMLCO_WARNING_MESSAGE) : null)
        .riskSubmit(true)
        .build();
  }

  @Override
  public CustomerRiskScoreResponse retrieveRiskScore(CustomerRiskScore query) {
    Long trackingNo = requireTrackingNo(query == null ? null : query.getTrackingNo());
    CustomerProfileEntity profile = retrieveProfile(trackingNo);
    RiskGradingResponse risk = customerRiskGradeRepository.findFirstByTrackingNo(trackingNo)
        .map(this::mapRiskScoreDetails)
        .orElse(null);
    return CustomerRiskScoreResponse.builder()
        .customerRiskScore(profile.getRiskGrading())
        .riskGrading(risk)
        .build();
  }

  @Override
  public List<EddQuestionResponse> retrieveEddQuestions(EddQuestions query) {
    Long trackingNo = requireTrackingNo(query == null ? null : query.getTrackingNo());
    return crgEddRepository.findAllByOrderByCrgEddIdAsc().stream()
        .map(entity -> EddQuestionResponse.builder()
            .trackingNo(trackingNo)
            .crgEddId(entity.getCrgEddId())
            .question(entity.getQuestion())
            .questionType(entity.getQuestionType())
            .labelEn(entity.getLabelEn())
            .requiredFlag(entity.getRequiredFlag())
            .placeholderText(entity.getPlaceholderText())
            .minLength(entity.getMinLength())
            .maxLength(entity.getMaxLength())
            .build())
        .toList();
  }

  @Override
  public List<EddAnswerResponse> retrieveEddDetails(CustomerEddDetails query) {
    Long trackingNo = requireTrackingNo(query == null ? null : query.getTrackingNo());
    return customerEddDetailRepository.findAllByTrackingNoOrderByCrgEddIdAsc(trackingNo).stream()
        .map(entity -> EddAnswerResponse.builder()
            .trackingNo(entity.getTrackingNo())
            .crgEddId(entity.getCrgEddId())
            .question(entity.getQuestion())
            .questionAnswer(entity.getQuestionAnswer())
            .build())
        .toList();
  }

  @Override
  public CustomerActionResponse retrieveDebitRestriction(DebitRestriction query) {
    Long trackingNo = requireTrackingNo(query == null ? null : query.getTrackingNo());
    return customerProfileGateway.checkDebitRestrictionDirect(trackingNo);
  }

  @Override
  public CustomerAuthTypeResponse retrieveAuthType() {
    return CustomerAuthTypeResponse.builder()
        .status(true)
        .data(sessionState.retrieveAuthType())
        .build();
  }

  @Override
  public CustomerActivityPageResponse retrieveActivity(CustomerActivity query) {
    CustomerActivity request = query == null ? new CustomerActivity() : query;
    int pageNumber = Math.max(request.getPageNumber(), 1);
    int pageSize = request.getPageSize() <= 0 ? DEFAULT_PAGE_SIZE : request.getPageSize();
    Specification<UserActivityLogEntity> specification = buildActivitySpecification(request);
    Pageable pageable = PageRequest.of(
        pageNumber - 1, pageSize, Sort.by(Sort.Direction.DESC, "actionDate"));
    Page<UserActivityLogEntity> page = userActivityLogRepository.findAll(specification, pageable);
    return CustomerActivityPageResponse.builder()
        .logs(page.getContent().stream().map(this::mapActivity).toList())
        .pageNumber(pageNumber)
        .pageSize(pageSize)
        .totalCount(page.getTotalElements())
        .totalPages(page.getTotalPages())
        .build();
  }

  @Override
  public CustomerExportResponse retrieveExcel(CustomerExport query) {
    CustomerExport request = query == null ? new CustomerExport() : query;
    LocalDate dateFrom = request.getDateFrom();
    LocalDate dateTo = request.getDateTo();
    if (StringUtils.hasText(request.getYear())
        && !"null".equalsIgnoreCase(request.getYear().trim())) {
      int year = parseYear(request.getYear());
      LocalDate today = LocalDate.now();
      dateTo = LocalDate.of(year, today.getMonthValue(), today.getDayOfMonth());
      dateFrom = LocalDate.of(year - 1, 1, 1);
    }
    if (dateFrom == null) {
      dateFrom = LocalDate.now();
    }
    if (dateTo == null) {
      dateTo = LocalDate.now();
    }

    boolean ownerOnly = !StringUtils.hasText(request.getMobileNo())
        && request.getTrackingNo() == null
        && !StringUtils.hasText(request.getNidNo())
        && !StringUtils.hasText(request.getFullName())
        && !StringUtils.hasText(request.getCustomerId())
        && !StringUtils.hasText(request.getAccountNoFrom())
        && !StringUtils.hasText(request.getAccountNoTo())
        && !StringUtils.hasText(request.getSelectedBranchId())
        && "0".equals(request.getProductTypeId())
        && !StringUtils.hasText(request.getRequestChannel());

    CustomerFilter filter = CustomerFilter.builder()
        .authType(resolveAuthType(request.getAuthType()))
        .mobileNo(request.getMobileNo())
        .trackingNo(request.getTrackingNo())
        .nidNo(request.getNidNo())
        .fullName(request.getFullName())
        .customerId(request.getCustomerId())
        .dateFrom(dateFrom)
        .dateTo(dateTo)
        .accountNoFrom(request.getAccountNoFrom())
        .accountNoTo(request.getAccountNoTo())
        .selectedBranchId(request.getSelectedBranchId())
        .productTypeId(request.getProductTypeId())
        .requestChannel(request.getRequestChannel())
        .logMode(true)
        .ownerOnly(ownerOnly)
        .pageNumber(1)
        .pageSize(Integer.MAX_VALUE)
        .model("Excel")
        .build();
    List<CustomerListItem> rows = retrieveAllCustomers(filter);
    if (request.isOnlyAccounts()) {
      rows = rows.stream()
          .filter(item -> StringUtils.hasText(item.getAccountNo()))
          .filter(item -> !item.getAccountNo().equals(item.getCustomerId()))
          .toList();
    }
    byte[] workbook = createCustomerWorkbook(rows, "Customer List");
    String documentName =
        mapAuthStatus(resolveAuthType(request.getAuthType()))
            + " Customer Profile List - " + rows.size() + " Data";
    return CustomerExportResponse.builder()
        .data(workbook)
        .documentName(documentName)
        .build();
  }

  @Override
  public CustomerExportResponse retrieveExcelDetails(CustomerExport query) {
    CustomerExport request = query == null ? new CustomerExport() : query;
    boolean ownerOnly = request.getDateFrom() == null
        && request.getDateTo() == null
        && !StringUtils.hasText(request.getSelectedBranchId());
    CustomerFilter filter = CustomerFilter.builder()
        .authType(resolveAuthType(request.getAuthType()))
        .dateFrom(request.getDateFrom())
        .dateTo(request.getDateTo())
        .selectedBranchId(request.getSelectedBranchId())
        .logMode(true)
        .ownerOnly(ownerOnly)
        .pageNumber(1)
        .pageSize(Integer.MAX_VALUE)
        .model("ExcelDetails")
        .build();
    List<CustomerListItem> rows = retrieveAllCustomers(filter);
    byte[] workbook = createCustomerDetailsWorkbook(rows);
    return CustomerExportResponse.builder()
        .data(workbook)
        .documentName("Customer Deatils Profile List")
        .build();
  }

  @Override
  public CustomerReportResponse retrieveReport(CustomerReport query) {
    Long trackingNo = requireTrackingNo(query == null ? null : query.getTrackingNo());
    return reportService.generate(trackingNo);
  }

  private CustomerDetailsResponse mapDetails(CustomerProfileEntity profile, boolean loadPhotos) {
    Optional<ProductEntity> product = retrieveProductEntity(profile.getProductId());
    String productTypeId = product.map(ProductEntity::getProductType).orElse("00001");
    String branchName = branchOfficeRepository.findById(profile.getBranchId())
        .map(BranchOfficeEntity::getBranchName)
        .orElse("");
    String rmCode = additionalInfoRepository
        .findFirstByTrackingNoAndPropertyName(profile.getTrackingNo(), "RMCode")
        .map(entity -> entity.getPropertyAnswer())
        .orElse("");
    String sdnScoreSetting = retrieveSetting(SDN_SCORE);
    String sanctionScreening = "";
    if (StringUtils.hasText(sdnScoreSetting) && profile.getSdnFlag() != null) {
      BigDecimal sdnScore = parseDecimal(sdnScoreSetting);
      sanctionScreening = BigDecimal.valueOf(profile.getSdnFlag()).compareTo(sdnScore) <= 0
          ? "Passed" : "Failed";
    }
    CustomerPhotosResponse photos = loadPhotos
        ? photoGateway.retrieveCustomerPhotos(profile.getTrackingNo()) : null;
    return CustomerDetailsResponse.builder()
        .trackingNo(profile.getTrackingNo())
        .trackingStatus(profile.getTrackingStatus())
        .mobileNo(nullSafe(profile.getMobileNo()))
        .email(nullSafe(profile.getEmail()))
        .nidNo(nullSafe(profile.getNidNo()))
        .fullNameEn(nullSafe(profile.getFullNameEn()))
        .fullNameBn(nullSafe(profile.getFullNameBn()))
        .birthdate(nullSafe(mapper.formatDate(profile.getBirthdate())))
        .gender(nullSafe(profile.getGender()))
        .religion(profile.getReligion())
        .profession(nullSafe(profile.getProfession()))
        .depositPerMonth(profile.getDepositPerMonth())
        .withdrawPerMonth(profile.getWithdrawPerMonth())
        .riskGrading(profile.getRiskGrading())
        .maximumRiskGrading(profile.getRiskGrading())
        .motherNameEn(nullSafe(profile.getMotherNameEn()))
        .motherNameBn(nullSafe(profile.getMotherNameBn()))
        .fatherNameEn(nullSafe(profile.getFatherNameEn()))
        .fatherNameBn(nullSafe(profile.getFatherNameBn()))
        .spouseName(nullSafe(profile.getSpouseName()))
        .presentAddressEn(nullSafe(profile.getPresentAddressEn()))
        .presentAddressBn(profile.getPresentAddressBn())
        .permanentAddress(nullSafe(profile.getPermanentAddress()))
        .country(profile.getCountry())
        .division(profile.getDivision())
        .district(profile.getDistrict())
        .subDistrict(profile.getSubDistrict())
        .thana(profile.getThana())
        .branchId(profile.getBranchId())
        .branchName(branchName)
        .productId(profile.getProductId())
        .productName(product.map(ProductEntity::getProductName).orElse(""))
        .productTypeId(productTypeId)
        .productTypeName(mapLegacyProductTypeName(productTypeId))
        .productCount(hasProductDetails(profile.getTrackingNo(), productTypeId) ? 1 : 0)
        .customerId(nullSafe(profile.getCustomerId()))
        .accountNo(nullSafe(profile.getAccountNo()))
        .faceMatchScoreCard(profile.getFaceMatchScoreRpa())
        .authStatus(mapAuthStatus(profile.getAuthStatus()))
        .authByDisplay("")
        .checkBoxValue(false)
        .remark(null)
        .active(false)
        .requestChannel(profile.getMakeBy())
        .eddCheck(profile.getEddCheck())
        .declineReason(profile.getDeclineReason())
        .customerPhoto(photos == null ? null : photos.getFromUploaded())
        .nidFront(photos == null ? null : photos.getNidFront())
        .nidBack(photos == null ? null : photos.getNidBack())
        .porichoyPhoto(photos == null ? null : photos.getFromPorichoy())
        .nidPhoto(photos == null ? null : photos.getFromNid())
        .signaturePhoto(photos == null ? null : photos.getFromSignature())
        .sanctionScreening(sanctionScreening)
        .customerEkycType(
            Integer.valueOf(2).equals(profile.getEkycFlag()) ? "Regular" : "Simplified")
        .checkBy(profile.getCheckBy() == null ? "" : profile.getCheckBy())
        .checkDate("")
        .authBy("")
        .authDate("")
        .smsAlertFlag(zeroIfNull(profile.getSmsAlertFlag()))
        .emailAlertFlag(zeroIfNull(profile.getEmailAlertFlag()))
        .chequeBookFlag(zeroIfNull(profile.getChequeBookFlag()))
        .debitCardFlag(zeroIfNull(profile.getDebitCardFlag()))
        .fatkaChecked(Integer.valueOf(1).equals(profile.getFatkaCheked()))
        .updateBy(profile.getUpdateBy())
        .rmCode(rmCode)
        .accountStatus("J".equalsIgnoreCase(profile.getAccountStatus()) ? "Joint" : "Individual")
        .referenceNo(profile.getReferenceNo())
        .authPermission(permissionSupport.hasAuthorizationPermission())
        .pendingBranchAuthorization(!StringUtils.hasText(profile.getCheckBy()))
        .nomineeCount(customerNomineeRepository.countByTrackingNo(profile.getTrackingNo()))
        .guardianCount(nomineeGuardianRepository.countByTrackingNo(profile.getTrackingNo()))
        .beneficiaryCount(
            customerBeneficialOwnerRepository.countByTrackingNo(profile.getTrackingNo()))
        .documentCount(customerDocumentRepository.countByTrackingNo(profile.getTrackingNo()))
        .bankShortName(settings.getBankShortName())
        .guardianList(
            nomineeGuardianRepository
                .findAllByTrackingNoOrderByNomineeNoAscGuardianNoAsc(profile.getTrackingNo())
                .stream()
                .map(mapper::toGuardian)
                .toList())
        .photos(photos)
        .build();
  }

  private Specification<CustomerProfileEntity> buildCustomerSpecification(
      CustomerFilter request,
      String authType,
      String branchId,
      boolean headOffice,
      CurrentUser currentUser) {
    return (root, query, cb) -> {
      List<Predicate> predicates = new ArrayList<>();
      predicates.add(cb.equal(root.get("authStatus"), authType));
      if (request.isOwnerOnly()) {
        predicates.add(
            cb.or(cb.isNull(root.get("referenceNo")), cb.equal(root.get("referenceNo"), 0L)));
      }
      if (!headOffice && StringUtils.hasText(branchId)) {
        predicates.add(cb.equal(root.get("branchId"), branchId));
      }
      if (!request.isLogMode() && StringUtils.hasText(currentUser.getUserId())) {
        predicates.add(cb.or(
            cb.isNull(root.get("checkBy")),
            cb.notEqual(root.get("checkBy"), currentUser.getUserId())));
      }
      if (request.isPendingBranchAuthorization()) {
        predicates.add(cb.or(cb.isNull(root.get("checkBy")), cb.equal(root.get("checkBy"), "")));
      }
      addTextPredicate(predicates, cb, root.get("mobileNo"), request.getMobileNo(), false);
      if (request.getTrackingNo() != null && request.getTrackingNo() != 0L) {
        predicates.add(cb.equal(root.get("trackingNo"), request.getTrackingNo()));
      }
      addTextPredicate(predicates, cb, root.get("nidNo"), request.getNidNo(), false);
      if (StringUtils.hasText(request.getFullName())) {
        predicates.add(cb.like(
            cb.lower(root.get("fullNameEn")),
            "%" + request.getFullName().trim().toLowerCase(Locale.ENGLISH) + "%"));
      }
      addTextPredicate(predicates, cb, root.get("customerId"), request.getCustomerId(), false);
      addTextPredicate(predicates, cb, root.get("makeBy"), request.getRequestChannel(), false);
      addTextPredicate(predicates, cb, root.get("gender"), request.getGender(), false);
      if (request.getDateFrom() != null) {
        predicates.add(cb.greaterThanOrEqualTo(
            root.get("makeDt"), request.getDateFrom().atStartOfDay()));
      }
      if (request.getDateTo() != null) {
        predicates.add(cb.lessThan(
            root.get("makeDt"), request.getDateTo().plusDays(1).atStartOfDay()));
      }
      if ("A".equalsIgnoreCase(authType)
          && StringUtils.hasText(request.getAccountNoFrom())
          && StringUtils.hasText(request.getAccountNoTo())) {
        predicates.add(cb.greaterThanOrEqualTo(
            root.get("accountNo"), request.getAccountNoFrom().trim()));
        predicates.add(cb.lessThanOrEqualTo(
            root.get("accountNo"), request.getAccountNoTo().trim()));
      }
      List<String> productIds = resolveProductIds(request.getProductTypeId());
      if (!productIds.isEmpty()) {
        predicates.add(root.get("productId").in(productIds));
      }
      return cb.and(predicates.toArray(Predicate[]::new));
    };
  }

  private Specification<UserActivityLogEntity> buildActivitySpecification(
      CustomerActivity request) {
    return (root, query, cb) -> {
      List<Predicate> predicates = new ArrayList<>();
      if (request.getTrackingNo() != null && request.getTrackingNo() != 0L) {
        predicates.add(cb.equal(root.get("trackingNo"), request.getTrackingNo()));
      }
      if (StringUtils.hasText(request.getUserId())) {
        predicates.add(cb.equal(root.get("userId"), request.getUserId().trim()));
      }
      if (request.getDateFrom() != null) {
        predicates.add(cb.greaterThanOrEqualTo(
            root.get("actionDate"), request.getDateFrom().atStartOfDay()));
      }
      if (request.getDateTo() != null) {
        predicates.add(cb.lessThan(
            root.get("actionDate"), request.getDateTo().plusDays(1).atStartOfDay()));
      }
      return cb.and(predicates.toArray(Predicate[]::new));
    };
  }

  private List<CustomerListItem> retrieveAllCustomers(CustomerFilter filter) {
    CurrentUser currentUser = currentUserProvider.getCurrentUser();
    String branchId = resolveBranch(filter, currentUser);
    boolean headOffice = sameText(branchId, retrieveSetting(HEAD_OFFICE_BRANCH_ID));
    Specification<CustomerProfileEntity> specification = buildCustomerSpecification(
        filter, resolveAuthType(filter.getAuthType()), branchId, headOffice, currentUser);
    List<CustomerListItem> result = customerProfileRepository.findAll(
        specification, Sort.by(Sort.Direction.DESC, "makeDt")).stream()
        .map(mapper::toListItem)
        .toList();
    enrichProductDetails(result);
    return result;
  }

  private List<UserActivityLogResponse> retrieveAllActivity(CustomerActivity request) {
    return userActivityLogRepository.findAll(
        buildActivitySpecification(request), Sort.by(Sort.Direction.DESC, "actionDate")).stream()
        .map(this::mapActivity)
        .toList();
  }

  private void enrichProductDetails(List<CustomerListItem> customers) {
    Set<String> productIds = customers.stream()
        .map(CustomerListItem::getProduct)
        .filter(StringUtils::hasText)
        .collect(Collectors.toSet());
    Map<String, ProductEntity> products = productRepository.findAll().stream()
        .filter(product -> productIds.contains(product.getProductId()))
        .collect(Collectors.toMap(ProductEntity::getProductId, product -> product, (a, b) -> a));
    for (CustomerListItem customer : customers) {
      ProductEntity product = products.get(customer.getProduct());
      if (product != null) {
        customer.setProductName(product.getProductName());
      }
      enrichDepositDetails(customer, product);
    }
  }

  private void enrichDepositDetails(CustomerListItem customer, ProductEntity product) {
    if (product == null) {
      return;
    }
    String productType = product.getProductType();
    if ("00002".equals(productType)) {
      customerSchemeAccountRepository
          .findFirstByTrackingNo(customer.getTrackingNo())
          .ifPresent(entity -> {
            customer.setTenure(
                entity.getTermTotalNumber() == null
                    ? null
                    : entity.getTermTotalNumber().toString());
            customer.setTermFrequency(entity.getTermFrequency());
            customer.setTotalTermNumber(
                entity.getTermTotalNumber() == null
                    ? null
                    : entity.getTermTotalNumber().toString());
            customer.setFutureAmount(stringValue(entity.getFutureAmount()));
            customer.setInstallmentAmount(stringValue(entity.getInstallmentAmount()));
            customer.setPrincipalAmount(stringValue(entity.getPrincipalAmount()));
            customer.setAccountOpenDate(entity.getAccountOpenDate());
            customer.setAccountMaturityDate(entity.getAccMaturityDt());
          });
    } else if ("00003".equals(productType)) {
      customerTimeAccountRepository
          .findFirstByTrackingNo(customer.getTrackingNo())
          .ifPresent(entity -> {
            customer.setTenure(
                entity.getTotalTermNumber() == null
                    ? null
                    : entity.getTotalTermNumber().toString());
            customer.setTermFrequency(entity.getTotalTermFrequency());
            customer.setTotalTermNumber(
                entity.getTotalTermNumber() == null
                    ? null
                    : entity.getTotalTermNumber().toString());
            customer.setFutureAmount(stringValue(entity.getFutureAmount()));
            customer.setMaturityAmount(stringValue(entity.getMaturityAmount()));
            customer.setPrincipalAmount(stringValue(entity.getPrincipalAmount()));
            customer.setAccountOpenDate(entity.getAccountOpenDate());
            customer.setAccountMaturityDate(entity.getMaturityDate());
          });
    } else if ("00004".equals(productType)) {
      customerLoanDetailRepository
          .findFirstByTrackingNo(customer.getTrackingNo())
          .ifPresent(entity -> {
            customer.setTenure(entity.getLoanTenure());
            customer.setInstallmentAmount(stringValue(entity.getMonthlyInstallment()));
          });
    }
  }

  private List<CustomerDetailsResponse> retrieveJointPartnerResponses(CustomerProfileEntity owner) {
    List<CustomerProfileEntity> partners = customerProfileRepository
        .findAllByReferenceNoOrderByTrackingNoAsc(owner.getTrackingNo());
    String requiredAuth = owner.getAuthStatus();
    return partners.stream()
        .filter(partner -> "U".equalsIgnoreCase(requiredAuth)
            ? "U".equalsIgnoreCase(partner.getAuthStatus())
                || "I".equalsIgnoreCase(partner.getAuthStatus())
            : Objects.equals(requiredAuth, partner.getAuthStatus()))
        .map(partner -> mapDetails(partner, false))
        .toList();
  }

  private CustomerNomineeResponse emptyNomineeResponse() {
    String placeholder = CustomerProfileImageFallback.apply(null);
    return CustomerNomineeResponse.builder()
        .nomineeName("")
        .nomineeIdNo("")
        .birthdate("")
        .gender("")
        .fatherNameEn("")
        .fatherNameBn("")
        .motherNameEn("")
        .motherNameBn("")
        .presentAddressEn("")
        .permanentAddress("")
        .sharePercent(0D)
        .nomineePhoto(placeholder)
        .nomineeNidFront(placeholder)
        .nomineeNidBack(placeholder)
        .build();
  }

  private CustomerGuardianResponse emptyGuardianResponse() {
    String placeholder = CustomerProfileImageFallback.apply(null);
    return CustomerGuardianResponse.builder()
        .guardianName("")
        .guardianIdNo("")
        .birthdate("")
        .gender("")
        .fatherNameEn("")
        .fatherNameBn("")
        .motherNameEn("")
        .motherNameBn("")
        .presentAddressEn("")
        .permanentAddress("")
        .sharePercent(0D)
        .guardianPhoto(placeholder)
        .guardianNidFront(placeholder)
        .guardianNidBack(placeholder)
        .build();
  }

  private CustomerBeneficiaryResponse emptyBeneficiaryResponse() {
    String placeholder = CustomerProfileImageFallback.apply(null);
    return CustomerBeneficiaryResponse.builder()
        .beneficiaryName("")
        .beneficiaryIdNo("")
        .birthdate("")
        .gender("")
        .fatherNameEn("")
        .fatherNameBn("")
        .motherNameEn("")
        .motherNameBn("")
        .presentAddressEn("")
        .permanentAddress("")
        .beneficiaryIdPhoto(placeholder)
        .beneficiaryIdFront(placeholder)
        .beneficiaryIdBack(placeholder)
        .build();
  }

  private CustomerPaymentResponse retrievePaymentForProfile(CustomerProfileEntity profile) {
    for (Long trackingNo : retrieveGroupTrackingNumbers(profile)) {
      Optional<CustomerSslCommerzPaymentEntity> payment =
          paymentRepository.retrieveLatest(trackingNo);
      if (payment.isPresent()) {
        return mapper.toPayment(payment.get());
      }
    }
    return null;
  }

  private List<Long> retrieveGroupTrackingNumbers(CustomerProfileEntity profile) {
    if (!isJoint(profile)) {
      return List.of(profile.getTrackingNo());
    }
    Long ownerTrackingNo = profile.getReferenceNo() != null && profile.getReferenceNo() > 0
        ? profile.getReferenceNo() : profile.getTrackingNo();
    List<Long> trackingNumbers = customerProfileRepository
        .findAllByReferenceNoOrderByTrackingNoAsc(ownerTrackingNo).stream()
        .map(CustomerProfileEntity::getTrackingNo)
        .collect(Collectors.toCollection(ArrayList::new));
    trackingNumbers.add(ownerTrackingNo);
    return trackingNumbers;
  }

  private boolean isSslPaymentEnabled(
      CustomerProfileEntity profile, CustomerPaymentResponse payment) {
    boolean enabled = parseBoolean(retrieveSetting(SSL_PAYMENT_ENABLE));
    if (!enabled) {
      return false;
    }
    String allowed = retrieveSetting(SSL_ALLOWED_PRODUCT_TYPES);
    String productType = retrieveProductEntity(profile.getProductId())
        .map(ProductEntity::getProductType)
        .orElse("");
    return StringUtils.hasText(allowed) && allowed.contains(productType);
  }

  private boolean isValidPayment(CustomerPaymentResponse payment) {
    return payment != null && StringUtils.hasText(payment.getStatus())
        && ("VALID".equalsIgnoreCase(payment.getStatus())
            || "VALIDATED".equalsIgnoreCase(payment.getStatus()));
  }

  private RiskGradingResponse mapRiskGrading(CustomerRiskGradeEntity entity) {
    return RiskGradingResponse.builder()
        .trackingNo(entity.getTrackingNo())
        .onboardTypeId(entity.getOnboardTypeId())
        .residentTypeId(entity.getResidentTypeId())
        .productTypeId(entity.getProductTypeId())
        .bizProfFlag(entity.getBizProfFlag())
        .bizProfTypeId(entity.getBizProfTypeId())
        .selfPepCoio(entity.getSelfPepCoio())
        .reltPepCoio(entity.getReltPepCoio())
        .selfIpReltIp(entity.getSelfIpReltIp())
        .avgYearTransId(entity.getAvgYearTransId())
        .crdblSrcFund(entity.getCrdblSrcFund())
        .build();
  }

  private RiskGradingResponse mapRiskScoreDetails(CustomerRiskGradeEntity entity) {
    RiskGradingResponse response = mapRiskGrading(entity);
    averageYearTransactionRepository.findById(entity.getAvgYearTransId()).ifPresent(value -> {
      response.setAvgYearTrans(value.getAvgYearTransNm());
      response.setAvgYearTransRiskValue(value.getRiskValue());
    });
    businessProfessionTypeRepository.findById(entity.getBizProfTypeId()).ifPresent(value -> {
          response.setBizProfType(value.getBizProfTypeNm());
          response.setBizProfTypeRiskValue(value.getRiskValue());
        });
    onboardingTypeRepository.findById(entity.getOnboardTypeId()).ifPresent(value -> {
      response.setOnboardType(value.getOnboardTypeNm());
      response.setOnboardTypeRiskValue(value.getRiskValue());
    });
    residentTypeRepository.findById(entity.getResidentTypeId()).ifPresent(value -> {
      response.setResidentType(value.getResidentTypeNm());
      response.setResidentTypeRiskValue(value.getRiskValue());
    });
    crgProductTypeRepository.findById(entity.getProductTypeId()).ifPresent(value -> {
      response.setProductType(value.getProductTypeNm());
      response.setProductTypeRiskValue(value.getRiskValue());
    });
    Map<Integer, CrgValueEntity> values = crgValueRepository.findAll().stream()
        .collect(Collectors.toMap(CrgValueEntity::getCrgValuesId, value -> value));
    applyRiskFlag(response, values.get(1), entity.getSelfPepCoio(), "selfPep");
    applyRiskFlag(response, values.get(2), entity.getReltPepCoio(), "reltPep");
    applyRiskFlag(response, values.get(3), entity.getSelfIpReltIp(), "selfIp");
    applyRiskFlag(response, values.get(4), entity.getCrdblSrcFund(), "sourceFund");
    return response;
  }

  private void applyRiskFlag(
      RiskGradingResponse response, CrgValueEntity value, Integer flag, String target) {
    if (value == null) {
      return;
    }
    int risk = Integer.valueOf(1).equals(flag) ? value.getRiskValueY() : value.getRiskValueN();
    String name = Integer.valueOf(0).equals(flag) ? "No" : "Yes";
    switch (target) {
      case "selfPep" -> {
        response.setSelfPepCoioName(name);
        response.setSelfPepCoioRiskValue(risk);
      }
      case "reltPep" -> {
        response.setReltPepCoioName(name);
        response.setReltPepCoioRiskValue(risk);
      }
      case "selfIp" -> {
        response.setSelfIpReltIpName(name);
        response.setSelfIpReltIpRiskValue(risk);
      }
      case "sourceFund" -> {
        response.setCrdblSrcFundName(name);
        response.setCrdblSrcFundRiskValue(risk);
      }
      default -> throw new IllegalArgumentException("Unknown risk target.");
    }
  }

  private DocumentFileResponse mapDocument(CustomerDocumentEntity entity) {
    String encoded = null;
    if (StringUtils.hasText(entity.getDocumentFilePath())) {
      try {
        Path path = Path.of(entity.getDocumentFilePath());
        if (Files.exists(path)) {
          encoded = Base64.getEncoder().encodeToString(Files.readAllBytes(path));
        }
      } catch (IOException | RuntimeException exception) {
        log.warn("Unable to read customer document {}.", entity.getDocumentFilePath(), exception);
      }
    }
    return DocumentFileResponse.builder()
        .documentCode(entity.getDocumentCode())
        .trackingNo(entity.getTrackingNo())
        .documentName(entity.getDocumentName())
        .documentFilePath(entity.getDocumentFilePath())
        .documentFile(encoded)
        .build();
  }

  private UserActivityLogResponse mapActivity(UserActivityLogEntity entity) {
    return UserActivityLogResponse.builder()
        .userId(entity.getUserId())
        .activitySlNo(entity.getActivitySlNo())
        .trackingNo(entity.getTrackingNo())
        .stepId(entity.getStepId())
        .actionType(entity.getActionType())
        .actionParticulars(entity.getActionParticulars())
        .actionDate(entity.getActionDate())
        .actionTerminalIp(entity.getActionTerminalIp())
        .build();
  }

  private byte[] createCustomerWorkbook(List<CustomerListItem> rows, String sheetName) {
    try (Workbook workbook = new XSSFWorkbook();
        java.io.ByteArrayOutputStream output = new java.io.ByteArrayOutputStream()) {
      Sheet sheet = workbook.createSheet(sheetName);
      String[] headers = {
        "Tracking No", "Customer ID", "Account No", "Name", "Mobile", "NID", "Branch",
        "Product", "Status", "Risk Score", "Make Date", "Make By", "Auth By"
      };
      Row header = sheet.createRow(0);
      for (int i = 0; i < headers.length; i++) {
        header.createCell(i).setCellValue(headers[i]);
      }
      int rowIndex = 1;
      for (CustomerListItem item : rows) {
        Row row = sheet.createRow(rowIndex++);
        row.createCell(0).setCellValue(stringValue(item.getTrackingNo()));
        row.createCell(1).setCellValue(nullSafe(item.getCustomerId()));
        row.createCell(2).setCellValue(nullSafe(item.getAccountNo()));
        row.createCell(3).setCellValue(nullSafe(item.getFullName()));
        row.createCell(4).setCellValue(nullSafe(item.getMobileNo()));
        row.createCell(5).setCellValue(nullSafe(item.getNidNo()));
        row.createCell(6).setCellValue(nullSafe(item.getBranch()));
        row.createCell(7).setCellValue(nullSafe(item.getProductName()));
        row.createCell(8).setCellValue(nullSafe(item.getAuthStatus()));
        row.createCell(9).setCellValue(stringValue(item.getRiskScore()));
        row.createCell(10).setCellValue(nullSafe(item.getMakeDate()));
        row.createCell(11).setCellValue(nullSafe(item.getMakeBy()));
        row.createCell(12).setCellValue(nullSafe(item.getAuthBy()));
      }
      workbook.write(output);
      return output.toByteArray();
    } catch (IOException exception) {
      throw new CustomerProfileValidationException("Unable to generate Excel report.", exception);
    }
  }

  private byte[] createCustomerDetailsWorkbook(List<CustomerListItem> rows) {
    try (Workbook workbook = new XSSFWorkbook();
        java.io.ByteArrayOutputStream output = new java.io.ByteArrayOutputStream()) {
      Sheet sheet = workbook.createSheet("Customer Details");
      String[] headers = {
        "Tracking No", "Customer ID", "Account No", "Name", "Mobile", "NID",
        "Branch", "Product", "Product Name", "Status", "Tenure", "Term Frequency",
        "Total Terms", "Future Amount", "Installment Amount", "Maturity Amount",
        "Principal Amount", "Account Open Date", "Account Maturity Date", "Make Date",
        "Make By", "Auth By", "Auth Date"
      };
      Row header = sheet.createRow(0);
      for (int index = 0; index < headers.length; index++) {
        header.createCell(index).setCellValue(headers[index]);
      }
      int rowIndex = 1;
      for (CustomerListItem item : rows) {
        Row row = sheet.createRow(rowIndex++);
        row.createCell(0).setCellValue(stringValue(item.getTrackingNo()));
        row.createCell(1).setCellValue(nullSafe(item.getCustomerId()));
        row.createCell(2).setCellValue(nullSafe(item.getAccountNo()));
        row.createCell(3).setCellValue(nullSafe(item.getFullName()));
        row.createCell(4).setCellValue(nullSafe(item.getMobileNo()));
        row.createCell(5).setCellValue(nullSafe(item.getNidNo()));
        row.createCell(6).setCellValue(nullSafe(item.getBranch()));
        row.createCell(7).setCellValue(nullSafe(item.getProduct()));
        row.createCell(8).setCellValue(nullSafe(item.getProductName()));
        row.createCell(9).setCellValue(nullSafe(item.getAuthStatus()));
        row.createCell(10).setCellValue(nullSafe(item.getTenure()));
        row.createCell(11).setCellValue(nullSafe(item.getTermFrequency()));
        row.createCell(12).setCellValue(nullSafe(item.getTotalTermNumber()));
        row.createCell(13).setCellValue(nullSafe(item.getFutureAmount()));
        row.createCell(14).setCellValue(nullSafe(item.getInstallmentAmount()));
        row.createCell(15).setCellValue(nullSafe(item.getMaturityAmount()));
        row.createCell(16).setCellValue(nullSafe(item.getPrincipalAmount()));
        row.createCell(17).setCellValue(nullSafe(item.getAccountOpenDate()));
        row.createCell(18).setCellValue(nullSafe(item.getAccountMaturityDate()));
        row.createCell(19).setCellValue(nullSafe(item.getMakeDate()));
        row.createCell(20).setCellValue(nullSafe(item.getMakeBy()));
        row.createCell(21).setCellValue(nullSafe(item.getAuthBy()));
        row.createCell(22).setCellValue(nullSafe(item.getAuthDate()));
      }
      workbook.write(output);
      return output.toByteArray();
    } catch (IOException exception) {
      throw new CustomerProfileValidationException(
          "Unable to generate customer details Excel report.", exception);
    }
  }

  private byte[] createActivityWorkbook(List<UserActivityLogResponse> rows) {
    try (Workbook workbook = new XSSFWorkbook();
        java.io.ByteArrayOutputStream output = new java.io.ByteArrayOutputStream()) {
      Sheet sheet = workbook.createSheet("Activity Log");
      String[] headers = {
        "Activity Sl", "Tracking No", "User ID", "Action", "Particulars", "Date", "Terminal IP"
      };
      Row header = sheet.createRow(0);
      for (int i = 0; i < headers.length; i++) {
        header.createCell(i).setCellValue(headers[i]);
      }
      int rowIndex = 1;
      for (UserActivityLogResponse item : rows) {
        Row row = sheet.createRow(rowIndex++);
        row.createCell(0).setCellValue(stringValue(item.getActivitySlNo()));
        row.createCell(1).setCellValue(stringValue(item.getTrackingNo()));
        row.createCell(2).setCellValue(nullSafe(item.getUserId()));
        row.createCell(3).setCellValue(nullSafe(item.getActionType()));
        row.createCell(4).setCellValue(nullSafe(item.getActionParticulars()));
        row.createCell(5).setCellValue(stringValue(item.getActionDate()));
        row.createCell(6).setCellValue(nullSafe(item.getActionTerminalIp()));
      }
      workbook.write(output);
      return output.toByteArray();
    } catch (IOException exception) {
      throw new CustomerProfileValidationException("Unable to generate Excel report.", exception);
    }
  }

  private void mapSchemeDetails(Map<String, Object> details, CustomerSchemeAccountEntity entity) {
    put(details, "TRACKING_NO", entity.getTrackingNo());
    put(details, "ACCOUNT_NO", entity.getAccountNumber());
    put(details, "PRODUCT_ID", entity.getProductId());
    put(details, "PRODUCT_NM", entity.getProductName());
    put(details, "ACC_TITLE", entity.getAccountTitle());
    put(details, "TRM_FREQ", entity.getTermFrequency());
    put(details, "TRM_TOT_NO", entity.getTermTotalNumber());
    put(details, "FUTURE_AMT", entity.getFutureAmount());
    put(details, "INSTL_AMT", entity.getInstallmentAmount());
    put(details, "PRINCIPAL_AMT", entity.getPrincipalAmount());
    put(details, "ACC_OPEN_DT", entity.getAccountOpenDate());
    put(details, "ACC_MATURITY_DT", entity.getAccMaturityDt());
    put(details, "BASE_DEPO_AMT", entity.getBaseDepoAmt());
  }

  private void mapTimeDetails(Map<String, Object> details, CustomerTimeAccountEntity entity) {
    put(details, "TRACKING_NO", entity.getTrackingNo());
    put(details, "ACCOUNT_NO", entity.getAccountNumber());
    put(details, "PRODUCT_ID", entity.getProductId());
    put(details, "PRODUCT_NM", entity.getProductName());
    put(details, "ACC_TITLE", entity.getAccountTitle());
    put(details, "TOT_TERM_FREQ", entity.getTotalTermFrequency());
    put(details, "TOT_TERM_NO", entity.getTotalTermNumber());
    put(details, "FUTURE_AMOUNT", entity.getFutureAmount());
    put(details, "MATURITY_AMT", entity.getMaturityAmount());
    put(details, "PRINCIPAL_AMT", entity.getPrincipalAmount());
    put(details, "ACC_OPEN_DT", entity.getAccountOpenDate());
    put(details, "MATURITY_DT", entity.getMaturityDate());
  }

  private void mapLoanDetails(Map<String, Object> details, CustomerLoanDetailEntity entity) {
    put(details, "TrackingNo", entity.getTrackingNo());
    put(details, "SourceOfIncome", entity.getSourceOfIncome());
    put(details, "MonthlyIncome", entity.getMonthlyIncome());
    put(details, "MonthlyInstallment", entity.getMonthlyInstallment());
    put(details, "BranchId", entity.getBranchId());
    put(details, "BranchName", entity.getBranchName());
    put(details, "BankId", entity.getBankId());
    put(details, "BankName", entity.getBankName());
    put(details, "LoanAmountRequested", entity.getLoanAmountRequested());
    put(details, "LoanAmountOtherBank", entity.getLoanAmountOtherBank());
    put(details, "ExistingLoanFlag", entity.getExistingLoanFlag());
    put(details, "CurrentOutstandingAmount", entity.getCurrentOutstandingAmount());
    put(details, "LoanTenure", entity.getLoanTenure());
    put(details, "LoanType", entity.getLoanType());
    put(details, "Udf_5", entity.getUdf5());
  }

  private void mapBoDetails(Map<String, Object> details, CustomerBoDetailEntity entity) {
    put(details, "TrackingNo", entity.getTrackingNo());
    put(details, "SourceOfIncome", entity.getSourceOfIncome());
    put(details, "MonthlyIncome", entity.getMonthlyIncome());
    put(details, "InitialDeposit", entity.getInitialDeposit());
    put(details, "BankId", entity.getBankId());
    put(details, "BankName", entity.getBankName());
    put(details, "BankAccNo", entity.getBankAccNo());
    put(details, "Udf_2", entity.getUdf2());
    put(details, "Udf_3", entity.getUdf3());
    put(details, "Udf_5", entity.getUdf5());
  }

  private boolean hasProductDetails(Long trackingNo, String productTypeId) {
    return switch (productTypeId) {
      case "00002" -> customerSchemeAccountRepository.findFirstByTrackingNo(trackingNo).isPresent();
      case "00003" -> customerTimeAccountRepository.findFirstByTrackingNo(trackingNo).isPresent();
      case "00004" -> customerLoanDetailRepository.findFirstByTrackingNo(trackingNo).isPresent();
      case "00005" -> customerBoDetailRepository.findFirstByTrackingNo(trackingNo).isPresent();
      default -> false;
    };
  }

  private List<ProductTypeOption> retrieveProductTypes() {
    return productTypeRepository.findAllByOrderByProductTypeIdAsc().stream()
        .map(entity -> ProductTypeOption.builder()
            .productTypeId(entity.getProductTypeId())
            .productTypeName(entity.getProductTypeName())
            .productTypeShortName(entity.getProductTypeShortName())
            .build())
        .toList();
  }

  private List<String> resolveProductIds(String productTypeId) {
    if (!StringUtils.hasText(productTypeId)) {
      return List.of();
    }
    String normalized = normalizeProductType(productTypeId);
    return productRepository.findAllByProductType(normalized).stream()
        .map(ProductEntity::getProductId)
        .filter(StringUtils::hasText)
        .toList();
  }

  private int parseYear(String value) {
    try {
      return Integer.parseInt(value.trim());
    } catch (NumberFormatException exception) {
      throw new CustomerProfileValidationException("Invalid year.", exception);
    }
  }

  private String normalizeProductType(String value) {
    String input = value.trim();
    if (input.length() == 5) {
      return input;
    }
    try {
      return String.format("%05d", Integer.parseInt(input));
    } catch (NumberFormatException exception) {
      return input;
    }
  }

  private String resolveBranch(CustomerFilter request, CurrentUser currentUser) {
    if (StringUtils.hasText(request.getSelectedBranchId())) {
      return request.getSelectedBranchId().trim();
    }
    if (StringUtils.hasText(request.getBranchId())) {
      return request.getBranchId().trim();
    }
    return currentUser.getHomeBranchId();
  }

  private String resolveAuthType(String value) {
    return StringUtils.hasText(value) ? value.trim() : sessionState.retrieveAuthType();
  }

  private CustomerProfileEntity retrieveProfile(Long trackingNo) {
    return customerProfileRepository.findById(trackingNo)
        .orElseThrow(() -> new CustomerProfileNotFoundException("Customer not found."));
  }

  private Optional<ProductEntity> retrieveProductEntity(String productId) {
    return StringUtils.hasText(productId)
        ? productRepository.findFirstByProductId(productId) : Optional.empty();
  }


  private String mapLegacyProductTypeName(String productTypeId) {
    if (!StringUtils.hasText(productTypeId)) {
      return "DEMAND";
    }
    return switch (productTypeId.trim()) {
      case "00002" -> "SCHEME";
      case "00003" -> "TIME";
      case "00004" -> "LOAN";
      case "00005" -> "BO";
      default -> "DEMAND";
    };
  }

  private String retrieveProductTypeName(String productTypeId) {
    if (!StringUtils.hasText(productTypeId)) {
      return "";
    }
    return productTypeRepository.findById(productTypeId)
        .map(ProductTypeEntity::getProductTypeName)
        .orElse("");
  }

  private String retrieveSetting(String key) {
    try {
      ParameterResponse response =
          parameterConfigQueryService.retrieveParameter(new ParameterDetails(key));
      return response == null || response.getParamValue() == null ? "" : response.getParamValue();
    } catch (RuntimeException exception) {
      log.debug("Parameter {} is unavailable.", key, exception);
      return "";
    }
  }

  private String retrieveUppercaseSetting(String key) {
    return retrieveSetting(key).toUpperCase(Locale.ENGLISH);
  }

  private String mapAuthStatus(String value) {
    if (!StringUtils.hasText(value)) {
      return value;
    }
    return switch (value.toUpperCase(Locale.ENGLISH)) {
      case "A" -> "Authorized";
      case "U" -> "Unauthorized";
      case "I" -> "Incomplete";
      case "D" -> "Declined";
      default -> value;
    };
  }

  private RiskOption option(Object value, String text) {
    return RiskOption.builder().value(String.valueOf(value)).text(text).build();
  }

  private void addTextPredicate(
      List<Predicate> predicates,
      jakarta.persistence.criteria.CriteriaBuilder cb,
      jakarta.persistence.criteria.Path<String> path,
      String value,
      boolean contains) {
    if (!StringUtils.hasText(value)) {
      return;
    }
    String normalized = value.trim();
    predicates.add(contains
        ? cb.like(cb.lower(path), "%" + normalized.toLowerCase(Locale.ENGLISH) + "%")
        : cb.equal(path, normalized));
  }

  private Long requireTrackingNo(Long value) {
    if (value == null || value <= 0) {
      throw new CustomerProfileValidationException("Invalid tracking number.");
    }
    return value;
  }

  private Integer requirePositive(Integer value, String name) {
    if (value == null || value <= 0) {
      throw new CustomerProfileValidationException("Invalid " + name + ".");
    }
    return value;
  }

  private boolean isJoint(CustomerProfileEntity profile) {
    return profile != null && "J".equalsIgnoreCase(profile.getAccountStatus());
  }

  private boolean sameText(String first, String second) {
    return StringUtils.hasText(first) && StringUtils.hasText(second)
        && first.trim().equalsIgnoreCase(second.trim());
  }

  private boolean parseBoolean(String value) {
    return StringUtils.hasText(value)
        && ("TRUE".equalsIgnoreCase(value.trim()) || "1".equals(value.trim()));
  }

  private BigDecimal parseDecimal(String value) {
    try {
      return StringUtils.hasText(value) ? new BigDecimal(value.trim()) : BigDecimal.ZERO;
    } catch (NumberFormatException exception) {
      return BigDecimal.ZERO;
    }
  }

  private String nullSafe(String value) {
    return value == null ? "" : value;
  }

  private int zeroIfNull(Integer value) {
    return value == null ? 0 : value;
  }

  private String stringValue(Object value) {
    return value == null ? "" : String.valueOf(value);
  }

  private void put(Map<String, Object> target, String key, Object value) {
    if (value != null) {
      target.put(key, value);
    }
  }
}
