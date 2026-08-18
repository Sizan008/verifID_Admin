package com.leads.microcube.verifidadmin.log;

import com.leads.microcube.verifidadmin.log.query.UserActivityLogResponse;
import com.leads.microcube.verifidadmin.log.repository.UserActivityLogEntity;
import org.springframework.stereotype.Component;

@Component
public class UserActivityLogMapper {

  private static final int LIST_PARTICULARS_LENGTH = 30;

  public UserActivityLogResponse toResponse(UserActivityLogEntity entity) {
    return UserActivityLogResponse.builder()
        .userId(entity.getUserId())
        .activitySlNo(entity.getActivitySlNo())
        .trackingNo(entity.getTrackingNo())
        .stepId(entity.getStepId())
        .actionType(entity.getActionType())
        .actionParticulars(truncateParticulars(entity.getActionParticulars()))
        .actionDate(entity.getActionDate())
        .actionTerminalIp(entity.getActionTerminalIp())
        .build();
  }

  private String truncateParticulars(String particulars) {
    if (particulars == null || particulars.length() <= LIST_PARTICULARS_LENGTH) {
      return particulars;
    }
    return particulars.substring(0, LIST_PARTICULARS_LENGTH);
  }
}
