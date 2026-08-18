package com.leads.microcube.verifidadmin.customerprofile;

import com.leads.microcube.verifidadmin.common.security.CurrentUser;
import com.leads.microcube.verifidadmin.common.security.CurrentUserProvider;
import com.leads.microcube.verifidadmin.customerprofile.client.CustomerProfileGateway;
import com.leads.microcube.verifidadmin.customerprofile.command.CheckCustomerByBranchAdmin;
import com.leads.microcube.verifidadmin.customerprofile.command.DeclineCustomer;
import com.leads.microcube.verifidadmin.customerprofile.command.EddAnswer;
import com.leads.microcube.verifidadmin.customerprofile.command.EnableCashTransaction;
import com.leads.microcube.verifidadmin.customerprofile.command.OpenCustomerAccount;
import com.leads.microcube.verifidadmin.customerprofile.command.RecordLoanBoAcceptReason;
import com.leads.microcube.verifidadmin.customerprofile.command.ReturnCustomer;
import com.leads.microcube.verifidadmin.customerprofile.command.SaveEddAnswers;
import com.leads.microcube.verifidadmin.customerprofile.command.SaveRiskGrading;
import com.leads.microcube.verifidadmin.customerprofile.command.UpdateCustomerServices;
import com.leads.microcube.verifidadmin.customerprofile.command.WithdrawDebitRestriction;
import com.leads.microcube.verifidadmin.customerprofile.exception.CustomerProfileNotFoundException;
import com.leads.microcube.verifidadmin.customerprofile.exception.CustomerProfileValidationException;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerActionResponse;
import com.leads.microcube.verifidadmin.customerprofile.repository.CrgAverageYearTransactionRepository;
import com.leads.microcube.verifidadmin.customerprofile.repository.CrgBusinessProfessionTypeRepository;
import com.leads.microcube.verifidadmin.customerprofile.repository.CrgOnboardingTypeRepository;
import com.leads.microcube.verifidadmin.customerprofile.repository.CrgProductTypeRepository;
import com.leads.microcube.verifidadmin.customerprofile.repository.CrgResidentTypeRepository;
import com.leads.microcube.verifidadmin.customerprofile.repository.CrgValueEntity;
import com.leads.microcube.verifidadmin.customerprofile.repository.CrgValueRepository;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerBoDetailEntity;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerBoDetailRepository;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerEddDetailEntity;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerEddDetailRepository;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerLoanDetailEntity;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerLoanDetailRepository;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerProfileEntity;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerProfileRepository;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerRiskGradeEntity;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerRiskGradeRepository;
import com.leads.microcube.verifidadmin.log.UserActivityLogService;
import com.leads.microcube.verifidadmin.log.command.RecordCurrentUserActivity;
import com.leads.microcube.verifidadmin.product.repository.ProductEntity;
import com.leads.microcube.verifidadmin.product.repository.ProductRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerProfileServiceImpl implements CustomerProfileService {

  private static final String AUTHORIZED = "A";
  private static final String DECLINED = "D";
  private static final String INCOMPLETE = "I";
  private static final short RETURN_TRACKING_STATUS = 7;

  private final CustomerProfileRepository customerProfileRepository;
  private final CustomerLoanDetailRepository customerLoanDetailRepository;
  private final CustomerBoDetailRepository customerBoDetailRepository;
  private final CustomerRiskGradeRepository customerRiskGradeRepository;
  private final CustomerEddDetailRepository customerEddDetailRepository;
  private final CrgAverageYearTransactionRepository averageYearTransactionRepository;
  private final CrgBusinessProfessionTypeRepository businessProfessionTypeRepository;
  private final CrgOnboardingTypeRepository onboardingTypeRepository;
  private final CrgProductTypeRepository productTypeRepository;
  private final CrgResidentTypeRepository residentTypeRepository;
  private final CrgValueRepository crgValueRepository;
  private final ProductRepository productRepository;
  private final CustomerProfileGateway customerProfileGateway;
  private final CurrentUserProvider currentUserProvider;
  private final UserActivityLogService userActivityLogService;

  @Override
  public void process(DeclineCustomer command) {
    Long trackingNo = requireTrackingNo(command == null ? null : command.getTrackingNo());
    String reason = command == null ? null : command.getDeclineReason();
    CustomerProfileEntity requested = retrieveProfile(trackingNo);
    LocalDateTime now = LocalDateTime.now();
    String userId = currentUserId();

    if (isJoint(requested)) {
      Long ownerTrackingNo = requested.getReferenceNo();
      if (ownerTrackingNo == null || ownerTrackingNo == 0) {
        ownerTrackingNo = requested.getTrackingNo();
      } else {
        declineJointCustomer(retrieveProfile(ownerTrackingNo), reason, userId, now);
      }
      for (CustomerProfileEntity partner
          : customerProfileRepository.findAllByReferenceNoOrderByTrackingNoAsc(ownerTrackingNo)) {
        declineJointCustomer(partner, reason, userId, now);
      }
    } else {
      requested.setDeclineReason(reason);
      requested.setAuthStatus(DECLINED);
      requested.setCheckBy(userId);
      requested.setCheckDt(now);
      customerProfileRepository.save(requested);
    }

    recordActivity(
        trackingNo,
        "Decline",
        "is DECLINED - " + nullSafe(reason),
        0);
  }

  @Override
  public void process(ReturnCustomer command) {
    Long trackingNo = requireTrackingNo(command == null ? null : command.getTrackingNo());
    String reason = command == null ? null : command.getReturnReason();
    CustomerProfileEntity profile = retrieveProfile(trackingNo);
    profile.setDeclineReason("Return reason : " + nullSafe(reason));
    profile.setAuthStatus(INCOMPLETE);
    profile.setTrackingStatus(RETURN_TRACKING_STATUS);
    profile.setCheckBy(currentUserId());
    profile.setCheckDt(LocalDateTime.now());
    customerProfileRepository.save(profile);
    recordActivity(trackingNo, "Return", "is Returned - " + nullSafe(reason), 0);
  }

  @Override
  public void process(RecordLoanBoAcceptReason command) {
    Long trackingNo = requireTrackingNo(command == null ? null : command.getTrackingNo());
    String reason = command == null ? null : command.getReason();
    CustomerProfileEntity profile = retrieveProfile(trackingNo);
    String productType = retrieveProductType(profile.getProductId());

    if ("00004".equals(productType)) {
      customerLoanDetailRepository.findFirstByTrackingNo(trackingNo).ifPresent(loan -> {
        loan.setUdf5(reason);
        customerLoanDetailRepository.save(loan);
      });
    } else if ("00005".equals(productType)) {
      customerBoDetailRepository.findFirstByTrackingNo(trackingNo).ifPresent(bo -> {
        bo.setUdf5(reason);
        customerBoDetailRepository.save(bo);
      });
    }

    recordActivity(
        trackingNo,
        "AcceptReason",
        "is Acceptance Reason - " + nullSafe(reason),
        0);
  }

  @Override
  public CustomerActionResponse process(CheckCustomerByBranchAdmin command) {
    Long trackingNo = requireTrackingNo(command == null ? null : command.getTrackingNo());
    CustomerActionResponse response =
        customerProfileGateway.requireDebitRestrictionWithdrawal(trackingNo);
    CustomerProfileEntity profile = retrieveProfile(trackingNo);
    profile.setCheckBy(currentUserId());
    profile.setCheckDt(LocalDateTime.now());
    customerProfileRepository.save(profile);
    recordActivity(trackingNo, "Withdraw", "is WithdrawDrRestriction", 0);
    return response;
  }

  @Override
  public CustomerActionResponse process(OpenCustomerAccount command) {
    Long trackingNo = requireTrackingNo(command == null ? null : command.getTrackingNo());
    recordActivity(trackingNo, "Open", "is Trying to Open Account of " + trackingNo, 0);

    CustomerActionResponse response = customerProfileGateway.openAccount(trackingNo);
    CustomerProfileEntity profile = retrieveProfile(trackingNo);
    if (isOk(response)) {
      String userId = currentUserId();
      LocalDateTime now = LocalDateTime.now();
      profile.setVerifyBy(userId);
      profile.setVerifyDt(now);
      profile.setAuthBy(userId);
      profile.setAuthDt(now);
      recordActivity(trackingNo, "Open", "is SUCCESS - Open Account of " + trackingNo, 19);
    } else {
      recordActivity(
          trackingNo,
          "Open",
          "is FAIL - Open Account of " + trackingNo + "-" + nullSafe(response.getMessage()),
          19);
    }
    customerProfileRepository.save(profile);
    return response;
  }

  @Override
  public CustomerActionResponse process(WithdrawDebitRestriction command) {
    Long trackingNo = requireTrackingNo(command == null ? null : command.getTrackingNo());
    try {
      CustomerActionResponse response = customerProfileGateway.withdrawDebitRestriction(trackingNo);
      boolean success = isOk(response);
      String message = success
          ? "WithdrawDrRestriction saved successfully"
          : "WithdrawDrRestriction not saved successfully";
      recordActivity(trackingNo, "Withdraw", message, 0);
      return CustomerActionResponse.builder()
          .message(message)
          .status(success ? "OK" : "FAILED")
          .build();
    } catch (RuntimeException exception) {
      return CustomerActionResponse.builder()
          .result("Something went wrong for WithdrawDrRestriction")
          .build();
    }
  }

  @Override
  public CustomerActionResponse process(EnableCashTransaction command) {
    Long trackingNo = requireTrackingNo(command == null ? null : command.getTrackingNo());
    recordActivity(
        trackingNo,
        "Cash",
        "is Trying to do cash Payment of " + trackingNo,
        0);
    return customerProfileGateway.enableCashTransaction(trackingNo);
  }

  @Override
  public CustomerActionResponse process(UpdateCustomerServices command) {
    Long trackingNo = requireTrackingNo(command == null ? null : command.getTrackingNo());
    CustomerProfileEntity profile = retrieveProfile(trackingNo);
    profile.setSmsAlertFlag(zeroIfNull(command.getSmsAlertFlag()));
    profile.setEmailAlertFlag(zeroIfNull(command.getEmailAlertFlag()));
    profile.setDebitCardFlag(zeroIfNull(command.getDebitCardFlag()));
    profile.setChequeBookFlag(zeroIfNull(command.getChequeBookFlag()));
    customerProfileRepository.save(profile);
    recordActivity(trackingNo, "Update", "is Updated Services", 0);
    return CustomerActionResponse.builder()
        .message("Saved the data Successfully")
        .status("OK")
        .build();
  }

  @Override
  public void process(SaveRiskGrading command) {
    if (command == null) {
      throw new CustomerProfileValidationException("Risk grading request is required.");
    }
    Long trackingNo = requireTrackingNo(command.getTrackingNo());
    int bizProfFlag = requireInteger(command.getBizProfFlag(), "business/profession flag");
    Integer businessProfessionId = bizProfFlag == 0
        ? command.getActivityTypeId()
        : command.getBizProfTypeId();
    if (businessProfessionId == null) {
      throw new CustomerProfileValidationException("Business/profession type is required.");
    }

    CustomerRiskGradeEntity riskGrade =
        customerRiskGradeRepository.findFirstByTrackingNo(trackingNo)
            .orElseGet(() -> CustomerRiskGradeEntity.builder().trackingNo(trackingNo).build());
    riskGrade.setBizProfFlag(bizProfFlag);
    riskGrade.setBizProfTypeId(businessProfessionId);
    riskGrade.setCrdblSrcFund(command.getCrdblSrcFund());
    riskGrade.setAvgYearTransId(command.getAvgYearTransId());
    riskGrade.setOnboardTypeId(command.getOnboardTypeId());
    riskGrade.setResidentTypeId(command.getResidentTypeId());
    riskGrade.setProductTypeId(command.getProductTypeId());
    riskGrade.setSelfIpReltIp(command.getSelfIpReltIp());
    riskGrade.setSelfPepCoio(command.getSelfPepCoio());
    riskGrade.setReltPepCoio(command.getReltPepCoio());
    if (riskGrade.getGreyListedCust() == null) {
      riskGrade.setGreyListedCust(0);
    }
    customerRiskGradeRepository.save(riskGrade);

    int score = calculateRiskScore(riskGrade);
    CustomerProfileEntity profile = retrieveProfile(trackingNo);
    profile.setRiskGrading(BigDecimal.valueOf(score));
    customerProfileRepository.save(profile);
    recordActivity(trackingNo, "Risk", "Calculating Risk Grading - " + score, 0);
  }

  @Override
  public void process(SaveEddAnswers command) {
    if (command == null || command.getAnswers() == null || command.getAnswers().isEmpty()) {
      throw new CustomerProfileValidationException("EDD answers are required.");
    }
    Long trackingNo = requireTrackingNo(command.getTrackingNo());
    if (customerEddDetailRepository.countByTrackingNo(trackingNo) == 0) {
      LocalDateTime now = LocalDateTime.now();
      List<CustomerEddDetailEntity> entities = new ArrayList<>();
      for (EddAnswer answer : command.getAnswers()) {
        if (answer == null || answer.getCrgEddId() == null) {
          throw new CustomerProfileValidationException("EDD question identifier is required.");
        }
        entities.add(
            CustomerEddDetailEntity.builder()
                .trackingNo(trackingNo)
                .crgEddId(answer.getCrgEddId())
                .question(answer.getQuestion())
                .questionAnswer(answer.getQuestionAnswer())
                .makeBy("Admin")
                .makeDt(now)
                .authStatus(AUTHORIZED)
                .build());
      }
      customerEddDetailRepository.saveAll(entities);
    }

    CustomerProfileEntity profile = retrieveProfile(trackingNo);
    profile.setEddCheck(1);
    customerProfileRepository.save(profile);
    recordActivity(trackingNo, "EDD", "EDD Checked", 0);
  }

  private void declineJointCustomer(
      CustomerProfileEntity profile,
      String reason,
      String userId,
      LocalDateTime now) {
    profile.setDeclineReason(reason);
    profile.setAuthStatus(DECLINED);
    profile.setAuthBy(userId);
    profile.setAuthDt(now);
    customerProfileRepository.save(profile);
  }

  private CustomerProfileEntity retrieveProfile(Long trackingNo) {
    return customerProfileRepository.findById(trackingNo)
        .orElseThrow(() -> new CustomerProfileNotFoundException("Customer profile not found."));
  }

  private int calculateRiskScore(CustomerRiskGradeEntity riskGrade) {
    int score = 0;
    score += averageYearTransactionRepository.findById(
            requireInteger(riskGrade.getAvgYearTransId(), "average yearly transaction"))
        .orElseThrow(() -> new CustomerProfileValidationException(
            "Average yearly transaction risk value is missing."))
        .getRiskValue();
    score += businessProfessionTypeRepository.findAll().stream()
        .filter(item -> item.getBizProfTypeId().equals(riskGrade.getBizProfTypeId()))
        .findFirst()
        .orElseThrow(() -> new CustomerProfileValidationException(
            "Business/profession risk value is missing."))
        .getRiskValue();
    score += onboardingTypeRepository.findById(
            requireInteger(riskGrade.getOnboardTypeId(), "onboarding type"))
        .orElseThrow(() -> new CustomerProfileValidationException(
            "Onboarding risk value is missing."))
        .getRiskValue();
    score += productTypeRepository.findById(
            requireText(riskGrade.getProductTypeId(), "product type"))
        .orElseThrow(() -> new CustomerProfileValidationException(
            "Product type risk value is missing."))
        .getRiskValue();
    score += residentTypeRepository.findById(
            requireInteger(riskGrade.getResidentTypeId(), "resident type"))
        .orElseThrow(() -> new CustomerProfileValidationException(
            "Resident risk value is missing."))
        .getRiskValue();
    score += flagRisk(1, riskGrade.getSelfPepCoio());
    score += flagRisk(2, riskGrade.getReltPepCoio());
    score += flagRisk(3, riskGrade.getSelfIpReltIp());
    score += flagRisk(4, riskGrade.getCrdblSrcFund());
    return score;
  }

  private int flagRisk(int riskValueId, Integer flag) {
    CrgValueEntity value = crgValueRepository.findById(riskValueId)
        .orElseThrow(() -> new CustomerProfileValidationException(
            "Risk grading value is missing."));
    return Integer.valueOf(0).equals(flag) ? value.getRiskValueN() : value.getRiskValueY();
  }

  private String retrieveProductType(String productId) {
    if (!StringUtils.hasText(productId)) {
      return "00001";
    }
    ProductEntity product = productRepository.findFirstByProductId(productId).orElse(null);
    return product == null || !StringUtils.hasText(product.getProductType())
        ? "00001"
        : product.getProductType();
  }

  private void recordActivity(Long trackingNo, String actionType, String particulars, int stepId) {
    userActivityLogService.process(
        RecordCurrentUserActivity.builder()
            .trackingNo(trackingNo)
            .stepId(stepId)
            .actionType(actionType)
            .actionParticulars(particulars)
            .requestChannel("")
            .build());
  }

  private String currentUserId() {
    CurrentUser user = currentUserProvider.getCurrentUser();
    if (user == null || !StringUtils.hasText(user.getUserId())) {
      throw new CustomerProfileValidationException("Current user is not available.");
    }
    return user.getUserId().trim();
  }

  private boolean isJoint(CustomerProfileEntity profile) {
    return profile != null && "J".equalsIgnoreCase(profile.getAccountStatus());
  }

  private boolean isOk(CustomerActionResponse response) {
    return response != null && "OK".equalsIgnoreCase(response.getStatus());
  }

  private Long requireTrackingNo(Long trackingNo) {
    if (trackingNo == null || trackingNo <= 0) {
      throw new CustomerProfileValidationException("Invalid tracking number.");
    }
    return trackingNo;
  }

  private Integer requireInteger(Integer value, String name) {
    if (value == null) {
      throw new CustomerProfileValidationException(name + " is required.");
    }
    return value;
  }

  private String requireText(String value, String name) {
    if (!StringUtils.hasText(value)) {
      throw new CustomerProfileValidationException(name + " is required.");
    }
    return value.trim();
  }

  private int zeroIfNull(Integer value) {
    return value == null ? 0 : value;
  }

  private String nullSafe(String value) {
    return value == null ? "" : value;
  }
}
