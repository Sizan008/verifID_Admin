package com.leads.microcube.verifidadmin.apimanagement;

import com.leads.microcube.verifidadmin.apimanagement.command.CreateApiConnection;
import com.leads.microcube.verifidadmin.apimanagement.command.UpdateApiConnection;
import com.leads.microcube.verifidadmin.apimanagement.exception.ApiManagementNotFoundException;
import com.leads.microcube.verifidadmin.apimanagement.exception.ApiManagementValidationException;
import com.leads.microcube.verifidadmin.apimanagement.repository.ApiConnectionEntity;
import com.leads.microcube.verifidadmin.apimanagement.repository.ApiConnectionRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ApiManagementServiceImpl implements ApiManagementService {

  private static final String DEFAULT_USER = "admin";
  private static final String AUTHORIZED_STATUS = "A";

  private final ApiConnectionRepository apiConnectionRepository;

  @Override
  public void process(CreateApiConnection command) {
    if (command == null) {
      throw new ApiManagementValidationException("API connection request is required.");
    }

    try {
      Integer apiConnId = apiConnectionRepository.findMaximumApiConnectionId() + 1;
      ApiConnectionEntity entity =
          ApiConnectionEntity.builder()
              .apiConnId(apiConnId)
              .apiConnName(command.getApiConnName())
              .apiConnKey(command.getApiConnKey())
              .apiConnPort(command.getApiConnPort())
              .apiConnUrl(command.getApiConnUrl())
              .apiConnUser(command.getApiConnUser())
              .apiConnPass(command.getApiConnPass())
              .apiCredential(command.getApiCredential())
              .makeBy(DEFAULT_USER)
              .makeDt(LocalDateTime.now())
              .authStatus(AUTHORIZED_STATUS)
              .build();
      apiConnectionRepository.save(entity);
    } catch (ApiManagementValidationException exception) {
      throw exception;
    } catch (Exception exception) {
      log.error("Unable to create API connection.", exception);
      throw new ApiManagementValidationException("Unable to create API connection.");
    }
  }

  @Override
  public void process(UpdateApiConnection command) {
    validate(command);

    Integer apiConnId = command.getApiConnId();
    ApiConnectionEntity entity =
        apiConnectionRepository
            .findById(apiConnId)
            .orElseThrow(() -> new ApiManagementNotFoundException(apiConnId));

    try {
      entity.setApiConnName(command.getApiConnName());
      entity.setApiConnKey(command.getApiConnKey());
      entity.setApiConnPort(command.getApiConnPort());
      entity.setApiConnUrl(command.getApiConnUrl());
      entity.setApiConnUser(command.getApiConnUser());
      entity.setApiConnPass(command.getApiConnPass());
      entity.setApiCredential(command.getApiCredential());
      apiConnectionRepository.save(entity);
    } catch (Exception exception) {
      log.error("Unable to update API connection. ID={}", apiConnId, exception);
      throw new ApiManagementValidationException("Unable to update API connection.");
    }
  }

  private void validate(UpdateApiConnection command) {
    if (command == null || command.getApiConnId() == null || command.getApiConnId() <= 0) {
      throw new ApiManagementValidationException("API connection ID is required.");
    }
  }
}
