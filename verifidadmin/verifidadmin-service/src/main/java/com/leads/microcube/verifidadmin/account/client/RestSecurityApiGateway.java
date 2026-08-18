package com.leads.microcube.verifidadmin.account.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.leads.microcube.verifidadmin.account.AccountSettings;
import com.leads.microcube.verifidadmin.account.client.dto.SecurityApiRequest;
import com.leads.microcube.verifidadmin.account.client.dto.SecurityApiResponse;
import com.leads.microcube.verifidadmin.account.command.LoginAccount;
import com.leads.microcube.verifidadmin.account.command.LoginContext;
import com.leads.microcube.verifidadmin.account.exception.AccountValidationException;
import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
@Slf4j
public class RestSecurityApiGateway implements SecurityApiGateway {

  private static final int SESSION_TIMEOUT_MINUTES = 20;

  private final RestClient restClient;
  private final ObjectMapper objectMapper;
  private final AccountSettings accountSettings;

  public RestSecurityApiGateway(
      RestClient.Builder restClientBuilder,
      ObjectMapper objectMapper,
      AccountSettings accountSettings) {
    this.restClient = restClientBuilder.build();
    this.objectMapper = objectMapper;
    this.accountSettings = accountSettings;
  }

  @Override
  public SecurityApiResponse login(LoginAccount command, LoginContext context) {
    ObjectNode businessData = objectMapper.createObjectNode();
    businessData.put("UserId", command.getUserName().trim());
    businessData.put("Password", command.getPassword());
    if ("1".equals(accountSettings.getSpark())) {
      businessData.put("Application", accountSettings.getApplicationId());
      businessData.putNull("RequestCliedIP");
    } else {
      businessData.put("FunctionGroup", accountSettings.getApplicationId());
      businessData.put("RequestCliedIP", context.getClientIp());
    }

    SecurityApiRequest request =
        SecurityApiRequest.builder()
            .requestClientIp(context.getClientIp())
            .requestApplicationIp(context.getServerIp())
            .businessData(writeBusinessData(businessData))
            .sessionId(context.getSessionId())
            .sessionTimeout(SESSION_TIMEOUT_MINUTES)
            .build();
    return post(accountSettings.getLoginUrl(), request, "login");
  }

  @Override
  public SecurityApiResponse logout(String userId) {
    ObjectNode businessData = objectMapper.createObjectNode();
    businessData.put("UserId", userId);
    SecurityApiRequest request =
        SecurityApiRequest.builder().businessData(writeBusinessData(businessData)).build();
    return post(accountSettings.getLogoutApiUrl(), request, "logout");
  }

  private SecurityApiResponse post(String url, SecurityApiRequest request, String operation) {
    try {
      log.debug("Calling Security API operation {} at {}.", operation, url);
      SecurityApiResponse response =
          restClient
              .post()
              .uri(URI.create(url))
              .contentType(MediaType.APPLICATION_JSON)
              .body(request)
              .retrieve()
              .body(SecurityApiResponse.class);
      if (response == null) {
        throw new AccountValidationException("Security API returned an empty response.");
      }
      log.debug(
          "Security API operation {} completed with response status {}.",
          operation,
          response.isResponseStatus());
      return response;
    } catch (RestClientException | IllegalArgumentException exception) {
      throw new AccountValidationException("Security API " + operation + " call failed.", exception);
    }
  }

  private String writeBusinessData(ObjectNode businessData) {
    try {
      return objectMapper.writeValueAsString(businessData);
    } catch (JsonProcessingException exception) {
      throw new AccountValidationException("Security API request could not be created.", exception);
    }
  }
}
