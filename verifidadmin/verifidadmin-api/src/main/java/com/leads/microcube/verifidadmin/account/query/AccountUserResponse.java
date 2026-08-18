package com.leads.microcube.verifidadmin.account.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountUserResponse {

  @JsonProperty("UserRoleId")
  private Integer userRoleId;

  @JsonProperty("UserId")
  private String userId;

  @JsonProperty("UserName")
  private String userName;

  @JsonProperty("UserPassword")
  private String userPassword;

  @JsonProperty("BranchId")
  private String branchId;

  @JsonProperty("UserRole")
  private String userRole;

  @JsonProperty("LastAction")
  private String lastAction;

  @JsonProperty("MakeBy")
  private String makeBy;

  @JsonProperty("MakeDt")
  private LocalDateTime makeDt;

  @JsonProperty("CheckBy")
  private String checkBy;

  @JsonProperty("CheckDt")
  private LocalDateTime checkDt;

  @JsonProperty("VerifyBy")
  private String verifyBy;

  @JsonProperty("VerifyDt")
  private LocalDateTime verifyDt;

  @JsonProperty("AuthBy")
  private String authBy;

  @JsonProperty("AuthDt")
  private LocalDateTime authDt;

  @JsonProperty("UpdateBy")
  private String updateBy;

  @JsonProperty("UpdateDt")
  private LocalDateTime updateDt;

  @JsonProperty("AuthStatus")
  private String authStatus;
}
