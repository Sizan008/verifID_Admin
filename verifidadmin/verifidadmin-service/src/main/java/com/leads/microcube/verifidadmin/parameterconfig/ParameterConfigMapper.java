package com.leads.microcube.verifidadmin.parameterconfig;

import com.leads.microcube.verifidadmin.parameterconfig.query.ParameterResponse;
import com.leads.microcube.verifidadmin.parameterconfig.repository.AppSettingEntity;
import org.springframework.stereotype.Component;

/** Maps parameter configuration persistence models to API models. */
@Component
public class ParameterConfigMapper {

  private static final int LIST_VALUE_MAX_LENGTH = 30;

  public ParameterResponse toResponse(AppSettingEntity entity, boolean truncateValue) {
    String value = entity.getValue();
    if (truncateValue && value != null && value.length() > LIST_VALUE_MAX_LENGTH) {
      value = value.substring(0, LIST_VALUE_MAX_LENGTH);
    }

    return ParameterResponse.builder()
        .paramName(entity.getKey())
        .paramValue(value)
        .description(entity.getDescription())
        .privacyLevel(entity.getPrivacyLevel())
        .build();
  }
}
