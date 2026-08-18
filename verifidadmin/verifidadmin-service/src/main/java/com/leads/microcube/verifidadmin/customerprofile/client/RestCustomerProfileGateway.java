package com.leads.microcube.verifidadmin.customerprofile.client;

import com.leads.microcube.verifidadmin.customerprofile.CustomerProfileSettings;
import com.leads.microcube.verifidadmin.customerprofile.client.dto.RemoteStatusResponse;
import com.leads.microcube.verifidadmin.customerprofile.exception.CustomerProfileValidationException;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerActionResponse;
import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Slf4j
public class RestCustomerProfileGateway implements CustomerProfileGateway {

  private final RestClient restClient;
  private final CustomerProfileSettings settings;

  public RestCustomerProfileGateway(
      RestClient.Builder restClientBuilder,
      CustomerProfileSettings settings) {
    this.restClient = restClientBuilder.build();
    this.settings = settings;
  }

  @Override
  public CustomerActionResponse enableCashTransaction(Long trackingNo) {
    return retrieve("api/SSLCommerz/CashTransactionEnable", trackingNo);
  }

  @Override
  public CustomerActionResponse openAccount(Long trackingNo) {
    return retrieve("api/CustOnBoarding/OpenAccount", trackingNo);
  }

  @Override
  public CustomerActionResponse withdrawDebitRestriction(Long trackingNo) {
    if (trackingNo == null || trackingNo <= 0) {
      throw new CustomerProfileValidationException("Invalid tracking number.");
    }
    URI uri = buildUri("api/CustOnBoarding/WithdrawDrRestriction", trackingNo);
    try {
      restClient.get().uri(uri).retrieve().toBodilessEntity();
      return CustomerActionResponse.builder().status("OK").build();
    } catch (RestClientResponseException exception) {
      log.error("Withdraw debit restriction failed. TrackingNo={}", trackingNo, exception);
      return CustomerActionResponse.builder().status("FAILED").build();
    } catch (RestClientException | IllegalArgumentException exception) {
      log.error("Withdraw debit restriction call failed. TrackingNo={}", trackingNo, exception);
      throw new CustomerProfileValidationException(
          "Customer profile remote API call failed.", exception);
    }
  }

  @Override
  public CustomerActionResponse requireDebitRestrictionWithdrawal(Long trackingNo) {
    return retrieve("api/CustOnBoarding/WithdrawDrRestriction", trackingNo);
  }

  @Override
  public CustomerActionResponse checkDebitRestriction(Long trackingNo) {
    if (settings.isOtherCbs()) {
      return CustomerActionResponse.builder().status("OK").result("").build();
    }
    return retrieve("api/CustOnBoarding/CheckDebitRestriction", trackingNo);
  }

  @Override
  public CustomerActionResponse checkDebitRestrictionDirect(Long trackingNo) {
    return retrieve("api/CustOnBoarding/CheckDebitRestriction", trackingNo);
  }

  private URI buildUri(String path, Long trackingNo) {
    return UriComponentsBuilder.fromUriString(settings.getApiBaseUrl())
        .path(path)
        .queryParam("trackingNo", trackingNo)
        .build(true)
        .toUri();
  }

  private CustomerActionResponse retrieve(String path, Long trackingNo) {
    if (trackingNo == null || trackingNo <= 0) {
      throw new CustomerProfileValidationException("Invalid tracking number.");
    }
    try {
      URI uri = buildUri(path, trackingNo);
      RemoteStatusResponse response =
          restClient.get().uri(uri).retrieve().body(RemoteStatusResponse.class);
      if (response == null) {
        throw new CustomerProfileValidationException("Remote service returned no response.");
      }
      return CustomerActionResponse.builder()
          .message(response.getMessage())
          .status(response.getStatus())
          .result(response.getResult() == null || response.getResult().isNull()
              ? null
              : response.getResult().isTextual()
                  ? response.getResult().asText()
                  : response.getResult())
          .build();
    } catch (RestClientException | IllegalArgumentException exception) {
      log.error("Customer profile remote call failed. Path={}, TrackingNo={}", path, trackingNo,
          exception);
      throw new CustomerProfileValidationException("Customer profile remote API call failed.",
          exception);
    }
  }
}
