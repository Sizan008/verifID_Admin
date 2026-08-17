package com.leads.microcube.verifidadmin.log;

import com.leads.microcube.verifidadmin.common.security.CurrentUser;
import com.leads.microcube.verifidadmin.common.security.CurrentUserProvider;
import com.leads.microcube.verifidadmin.common.security.UnauthenticatedException;
import com.leads.microcube.verifidadmin.log.command.RecordCurrentUserActivity;
import com.leads.microcube.verifidadmin.log.command.RecordUserActivity;
import com.leads.microcube.verifidadmin.log.repository.UserActivityLogEntity;
import com.leads.microcube.verifidadmin.log.repository.UserActivityLogRepository;
import java.time.LocalDateTime;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserActivityLogServiceImpl implements UserActivityLogService {

  private static final String DEFAULT_USER = "admin";
  private static final String DEFAULT_REQUEST_CHANNEL = "---";
  private static final int MAXIMUM_PARTICULARS_LENGTH = 500;

  private final UserActivityLogRepository userActivityLogRepository;
  private final CurrentUserProvider currentUserProvider;
  private final PlatformTransactionManager transactionManager;

  @Override
  public boolean process(RecordUserActivity command) {
    if (!isValid(command)) {
      log.warn("User activity log request is incomplete.");
      return false;
    }

    String userId = resolveUserId(command.getUserId());
    try {
      TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
      transactionTemplate.setPropagationBehavior(
          TransactionDefinition.PROPAGATION_REQUIRES_NEW);
      transactionTemplate.executeWithoutResult(
          status -> userActivityLogRepository.saveAndFlush(createEntity(command, userId)));
      return true;
    } catch (RuntimeException exception) {
      log.error(
          "Unable to record user activity. UserId={}, ActionType={}",
          userId,
          command.getActionType(),
          exception);
      return false;
    }
  }

  private UserActivityLogEntity createEntity(
      RecordUserActivity command,
      String userId) {
    Integer activitySerialNumber =
        userActivityLogRepository.retrieveMaximumActivitySerialNumber() + 1;
    return UserActivityLogEntity.builder()
        .activitySlNo(activitySerialNumber)
        .userId(userId)
        .trackingNo(command.getTrackingNo() == null ? 0L : command.getTrackingNo())
        .stepId(command.getStepId() == null ? 0 : command.getStepId())
        .actionType(normalizeActionType(command.getActionType()))
        .actionParticulars(command.getActionParticulars())
        .actionDate(LocalDateTime.now())
        .actionTerminalIp(normalizeRequestChannel(command.getRequestChannel()))
        .build();
  }

  @Override
  public boolean process(RecordCurrentUserActivity command) {
    if (command == null) {
      log.warn("Current user activity log request is required.");
      return false;
    }

    String userId = resolveUserId(null);
    String particulars =
        userId + " " + normalizeParticulars(command.getActionParticulars());
    return process(
        RecordUserActivity.builder()
            .userId(userId)
            .trackingNo(command.getTrackingNo())
            .stepId(command.getStepId())
            .actionType(command.getActionType())
            .actionParticulars(particulars)
            .requestChannel(command.getRequestChannel())
            .build());
  }

  private boolean isValid(RecordUserActivity command) {
    return command != null
        && StringUtils.hasText(command.getActionType())
        && StringUtils.hasText(command.getActionParticulars())
        && command.getActionParticulars().length() <= MAXIMUM_PARTICULARS_LENGTH;
  }

  private String resolveUserId(String requestedUserId) {
    if (StringUtils.hasText(requestedUserId)) {
      return requestedUserId.trim();
    }

    try {
      CurrentUser currentUser = currentUserProvider.getCurrentUser();
      if (currentUser != null && StringUtils.hasText(currentUser.getUserId())) {
        return currentUser.getUserId().trim();
      }
    } catch (UnauthenticatedException exception) {
      log.debug("No authenticated user is available for activity logging.", exception);
    }
    return DEFAULT_USER;
  }

  private String normalizeActionType(String actionType) {
    return actionType.trim().substring(0, 1).toUpperCase(Locale.ROOT);
  }

  private String normalizeParticulars(String particulars) {
    return StringUtils.hasText(particulars) ? particulars.trim() : "performed an action";
  }

  private String normalizeRequestChannel(String requestChannel) {
    return StringUtils.hasText(requestChannel)
        ? requestChannel.trim()
        : DEFAULT_REQUEST_CHANNEL;
  }
}
