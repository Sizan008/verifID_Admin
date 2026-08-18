package com.leads.microcube.verifidadmin.apimanagement;

import com.leads.microcube.verifidadmin.apimanagement.exception.ApiManagementNotFoundException;
import com.leads.microcube.verifidadmin.apimanagement.exception.ApiManagementValidationException;
import com.leads.microcube.verifidadmin.apimanagement.query.ApiConnectionDetails;
import com.leads.microcube.verifidadmin.apimanagement.query.ApiConnectionResponse;
import com.leads.microcube.verifidadmin.apimanagement.repository.ApiConnectionEntity;
import com.leads.microcube.verifidadmin.apimanagement.repository.ApiConnectionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApiManagementQueryServiceImpl implements ApiManagementQueryService {

  private final ApiConnectionRepository apiConnectionRepository;
  private final ApiManagementMapper apiManagementMapper;

  @Override
  public List<ApiConnectionResponse> retrieveApiConnections() {
    try {
      return apiConnectionRepository.findAllByOrderByApiConnIdAsc().stream()
          .map(apiManagementMapper::toResponse)
          .toList();
    } catch (Exception exception) {
      log.error("Unable to retrieve API connections.", exception);
      throw new ApiManagementValidationException("Unable to retrieve API connections.");
    }
  }

  @Override
  public ApiConnectionResponse retrieveApiConnection(ApiConnectionDetails query) {
    if (query == null || query.getApiConnId() == null || query.getApiConnId() <= 0) {
      throw new ApiManagementValidationException("API connection ID is required.");
    }

    Integer apiConnId = query.getApiConnId();
    ApiConnectionEntity entity =
        apiConnectionRepository
            .findById(apiConnId)
            .orElseThrow(() -> new ApiManagementNotFoundException(apiConnId));
    return apiManagementMapper.toResponse(entity);
  }
}
