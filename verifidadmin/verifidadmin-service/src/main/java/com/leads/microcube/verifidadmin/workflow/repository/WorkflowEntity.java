package com.leads.microcube.verifidadmin.workflow.repository;

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
@Table(name = "PARAM_WORKFLOWS")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class WorkflowEntity {

  @Id
  @Column(name = "WORKFLOW_ID", nullable = false, precision = 10)
  private Integer workflowId;

  @Column(name = "WORKFLOWNAME", length = 2000)
  private String workflowName;

  @Column(name = "WORKFLOWDESC", length = 2000)
  private String workflowDesc;

  @Column(name = "ACC_OPN_FLAG", precision = 3)
  private Integer accOpnFlag;

  @Column(name = "ACC_AUTH_STEP", precision = 3)
  private Integer accAuthStep;

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
