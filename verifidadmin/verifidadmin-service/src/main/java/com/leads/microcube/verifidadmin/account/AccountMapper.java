package com.leads.microcube.verifidadmin.account;

import com.leads.microcube.verifidadmin.account.query.AccountUserResponse;
import com.leads.microcube.verifidadmin.account.repository.BankUserRoleEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

  public AccountUserResponse toResponse(BankUserRoleEntity entity) {
    return AccountUserResponse.builder()
        .userRoleId(entity.getUserRoleId())
        .userId(trim(entity.getUserId()))
        .userName(entity.getUserName())
        .userPassword(trim(entity.getUserPassword()))
        .branchId(trim(entity.getBranchId()))
        .userRole(upper(entity.getUserRole()))
        .lastAction(trim(entity.getLastAction()))
        .makeBy(entity.getMakeBy())
        .makeDt(entity.getMakeDt())
        .checkBy(entity.getCheckBy())
        .checkDt(entity.getCheckDt())
        .verifyBy(entity.getVerifyBy())
        .verifyDt(entity.getVerifyDt())
        .authBy(entity.getAuthBy())
        .authDt(entity.getAuthDt())
        .updateBy(entity.getUpdateBy())
        .updateDt(entity.getUpdateDt())
        .authStatus(trim(entity.getAuthStatus()))
        .build();
  }

  private String trim(String value) {
    return value == null ? null : value.trim();
  }

  private String upper(String value) {
    String normalized = trim(value);
    return normalized == null ? null : normalized.toUpperCase();
  }
}
