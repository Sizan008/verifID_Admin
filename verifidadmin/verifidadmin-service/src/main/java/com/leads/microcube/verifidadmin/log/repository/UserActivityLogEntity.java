package com.leads.microcube.verifidadmin.log.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SEC_USER_ACTIVITY_LOG")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserActivityLogEntity {

  @Id
  @Column(name = "ACTIVITY_SL_NO", nullable = false, precision = 10)
  private Integer activitySlNo;

  @Column(name = "TRACKING_NO", nullable = false, precision = 19)
  private Long trackingNo;

  @Column(name = "STEP_ID", nullable = false, precision = 3)
  private Integer stepId;

  @Column(name = "ACTION_TYPE", nullable = false, length = 1)
  private String actionType;

  @Column(name = "ACTION_PARTICULARS", nullable = false, length = 500)
  private String actionParticulars;

  @Column(name = "ACTION_DATE")
  private LocalDateTime actionDate;

  @Column(name = "ACTION_TERMINAL_IP", nullable = false, length = 100)
  private String actionTerminalIp;

  @Column(name = "USER_ID", length = 100)
  private String userId;
}
