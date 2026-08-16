package com.leads.microcube.verifidadmin.parameterconfig;

import com.leads.microcube.verifidadmin.parameterconfig.exception.ParameterConfigNotFoundException;
import com.leads.microcube.verifidadmin.parameterconfig.exception.ParameterConfigValidationException;
import com.leads.microcube.verifidadmin.parameterconfig.query.ParameterDetails;
import com.leads.microcube.verifidadmin.parameterconfig.query.ParameterResponse;
import com.leads.microcube.verifidadmin.parameterconfig.repository.AppSettingEntity;
import com.leads.microcube.verifidadmin.parameterconfig.repository.AppSettingRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/** Retrieves parameter configuration information. */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ParameterConfigQueryServiceImpl implements ParameterConfigQueryService {

  private final AppSettingRepository appSettingRepository;
  private final ParameterConfigMapper parameterConfigMapper;

  @Override
  public List<ParameterResponse> retrieveParameters() {
    try {
      return appSettingRepository.findAllByOrderByKeyAsc().stream()
          .map(entity -> parameterConfigMapper.toResponse(entity, true))
          .toList();
    } catch (Exception exception) {
      log.error("Unable to retrieve parameter configurations.", exception);
      throw new ParameterConfigValidationException(
          "Unable to retrieve parameter configurations.");
    }
  }

  @Override
  public ParameterResponse retrieveParameter(ParameterDetails query) {
    if (query == null || !StringUtils.hasText(query.getParamName())) {
      throw new ParameterConfigValidationException("Parameter name is required.");
    }

    String key = query.getParamName();
    AppSettingEntity entity =
        appSettingRepository
            .findById(key)
            .orElseThrow(() -> new ParameterConfigNotFoundException(key));
    return parameterConfigMapper.toResponse(entity, false);
  }
}
