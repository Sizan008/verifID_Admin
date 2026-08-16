package com.leads.microcube.verifidadmin.apimanagement;

import com.leads.microcube.verifidadmin.apimanagement.query.ApiConnectionResponse;
import com.leads.microcube.verifidadmin.apimanagement.repository.ApiConnectionEntity;
import org.springframework.stereotype.Component;

@Component
public class ApiManagementMapper {

  public ApiConnectionResponse toResponse(ApiConnectionEntity entity) {
    return ApiConnectionResponse.builder()
        .apiConnId(entity.getApiConnId())
        .apiConnName(entity.getApiConnName())
        .apiConnKey(entity.getApiConnKey())
        .apiConnPort(entity.getApiConnPort())
        .apiConnUrl(entity.getApiConnUrl())
        .apiConnUser(entity.getApiConnUser())
        .apiConnPass(entity.getApiConnPass())
        .apiCredential(entity.getApiCredential())
        .build();
  }
}
