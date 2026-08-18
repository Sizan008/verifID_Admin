package com.leads.microcube.verifidadmin.account.repository;

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
@Table(name = "PARAM_BANK_USER_ROLES")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BankUserRoleEntity {

  @Column(name = "USER_ROLE_ID", nullable = false, precision = 10)
  private Integer userRoleId;

  @Id
  @Column(name = "USER_ID", nullable = false, length = 200)
  private String userId;

  @Column(name = "USER_NM", length = 200)
  private String userName;

  @Column(name = "USER_PASSWORD", length = 8)
  private String userPassword;

  @Column(name = "BRANCH_ID", length = 200)
  private String branchId;

  @Column(name = "USER_ROLE", length = 200)
  private String userRole;

  @Column(name = "LAST_ACTION", length = 3)
  private String lastAction;

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
