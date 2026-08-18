package com.leads.microcube.verifidadmin.channel.repository;

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
@Table(name = "PARAM_CHANNELS")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ChannelEntity {

  @Id
  @Column(name = "CHANNEL_ID", nullable = false, precision = 10)
  private Integer channelId;

  @Column(name = "CHANNELNAME", nullable = false, length = 2000)
  private String channelName;

  @Column(name = "CHANNELDESC", length = 2000)
  private String channelDesc;

  @Column(name = "WORKFLOW_ID", nullable = false, precision = 10)
  private Integer workflowId;

  @Column(name = "EMAIL_CONN_ID", precision = 10)
  private Integer emailConnId;

  @Column(name = "API_CONN_ID_SMS", precision = 10)
  private Integer apiConnIdSms;

  @Column(name = "API_CONN_ID_SDN", precision = 10)
  private Integer apiConnIdSdn;

  @Column(name = "API_CONN_ID_CBS", precision = 10)
  private Integer apiConnIdCbs;

  @Column(name = "API_CONN_ID_VFAPI", precision = 10)
  private Integer apiConnIdVfApi;

  @Column(name = "API_CONN_ID_VF_ML", precision = 10)
  private Integer apiConnIdVfMl;

  @Column(name = "API_CONN_ID_VF_RPA", precision = 10)
  private Integer apiConnIdVfRpa;

  @Column(name = "BIZ_TIME_CONTROL", precision = 3)
  private Short bizTimeControl;

  @Column(name = "BIZ_START_TIME", length = 2000)
  private String bizStartTime;

  @Column(name = "BIZ_END_TIME", length = 2000)
  private String bizEndTime;

  @Column(name = "ALIEN_NET_ACCESS", precision = 3)
  private Short alienNetAccess;

  @Column(name = "PRODUCT_CODE", precision = 10)
  private Integer productCode;

  @Column(name = "MAKEBY", nullable = false, length = 30)
  private String makeBy;

  @Column(name = "MAKEDT", nullable = false)
  private LocalDateTime makeDt;

  @Column(name = "CHECKBY", length = 30)
  private String checkBy;

  @Column(name = "CHECKDT")
  private LocalDateTime checkDt;

  @Column(name = "VERIFYBY", length = 30)
  private String verifyBy;

  @Column(name = "VERIFYDT")
  private LocalDateTime verifyDt;

  @Column(name = "AUTHBY", length = 30)
  private String authBy;

  @Column(name = "AUTHDT")
  private LocalDateTime authDt;

  @Column(name = "UPDATEBY", length = 30)
  private String updateBy;

  @Column(name = "UPDATEDT")
  private LocalDateTime updateDt;

  @Column(name = "AUTHSTATUS", nullable = false, length = 1)
  private String authStatus;
}
