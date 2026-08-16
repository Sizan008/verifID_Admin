package com.leads.microcube.verifidadmin.parameterconfig;

import com.leads.microcube.verifidadmin.parameterconfig.command.CreateParameter;
import com.leads.microcube.verifidadmin.parameterconfig.command.UpdateParameter;
import com.leads.microcube.verifidadmin.parameterconfig.exception.ParameterConfigNotFoundException;
import com.leads.microcube.verifidadmin.parameterconfig.exception.ParameterConfigValidationException;
import com.leads.microcube.verifidadmin.parameterconfig.repository.AppSettingEntity;
import com.leads.microcube.verifidadmin.parameterconfig.repository.AppSettingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/** Processes parameter configuration write commands. */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ParameterConfigServiceImpl implements ParameterConfigService {

  private final AppSettingRepository appSettingRepository;

  @Override
  public void process(CreateParameter command) {
    validate(command);

    String key = command.getParamName();
    if (appSettingRepository.existsById(key)) {
      throw new ParameterConfigValidationException("Parameter already exists: " + key);
    }

    try {
      AppSettingEntity entity =
          AppSettingEntity.builder()
              .key(key)
              .value(command.getParamValue())
              .description(command.getDescription())
              .privacyLevel(command.getPrivacyLevel())
              .build();
      appSettingRepository.save(entity);
    } catch (ParameterConfigValidationException exception) {
      throw exception;
    } catch (Exception exception) {
      log.error("Unable to create parameter configuration. Key={}", key, exception);
      throw new ParameterConfigValidationException(
          "Unable to create parameter configuration.");
    }
  }

  @Override
  public void process(UpdateParameter command) {
    validate(command);

    String currentKey = command.getCurrentParamName();
    String updatedKey = command.getParamName();
    AppSettingEntity entity =
        appSettingRepository
            .findById(currentKey)
            .orElseThrow(() -> new ParameterConfigNotFoundException(currentKey));

    try {
      if (!currentKey.equals(updatedKey)) {
        throw new ParameterConfigValidationException(
                "Parameter name cannot be changed.");
      }

      entity.setValue(command.getParamValue());
      entity.setDescription(command.getDescription());
      entity.setPrivacyLevel(command.getPrivacyLevel());

      appSettingRepository.save(entity);
    } catch (ParameterConfigValidationException exception) {
      throw exception;
    } catch (Exception exception) {
      log.error("Unable to update parameter configuration. Key={}", currentKey, exception);
      throw new ParameterConfigValidationException(
          "Unable to update parameter configuration.");
    }
  }

  private void validate(CreateParameter command) {
    if (command == null || !StringUtils.hasText(command.getParamName())) {
      throw new ParameterConfigValidationException("Parameter name is required.");
    }
    if (command.getParamValue() == null) {
      throw new ParameterConfigValidationException("Parameter value is required.");
    }
  }

  private void validate(UpdateParameter command) {
    if (command == null || !StringUtils.hasText(command.getCurrentParamName())) {
      throw new ParameterConfigValidationException("Current parameter name is required.");
    }
    if (!StringUtils.hasText(command.getParamName())) {
      throw new ParameterConfigValidationException("Parameter name is required.");
    }
    if (command.getParamValue() == null) {
      throw new ParameterConfigValidationException("Parameter value is required.");
    }
  }
}
