package com.leads.microcube.verifidadmin.customerprofile.client;

import com.leads.microcube.verifidadmin.customerprofile.CustomerProfileSettings;
import com.leads.microcube.verifidadmin.customerprofile.client.dto.RemoteDocumentResponse;
import com.leads.microcube.verifidadmin.customerprofile.client.dto.RemoteStatusResponse;
import com.leads.microcube.verifidadmin.customerprofile.exception.CustomerProfileValidationException;
import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Slf4j
public class RestCustomerProfileReportGateway implements CustomerProfileReportGateway {

  private final RestClient restClient;
  private final CustomerProfileSettings settings;

  public RestCustomerProfileReportGateway(
      RestClient.Builder restClientBuilder,
      CustomerProfileSettings settings) {
    this.restClient = restClientBuilder.build();
    this.settings = settings;
  }

  @Override
  public RemoteStatusResponse retrieveReport(Long trackingNo) {
    try {
      URI uri =
          UriComponentsBuilder.fromUriString(settings.getReportBaseUrl())
              .path("api/Home/GenerateUserReport")
              .queryParam("trackingNo", trackingNo)
              .build(true)
              .toUri();
      return restClient.get().uri(uri).retrieve().body(RemoteStatusResponse.class);
    } catch (RestClientException | IllegalArgumentException exception) {
      log.error("Unable to retrieve EKYC report. TrackingNo={}", trackingNo, exception);
      throw new CustomerProfileValidationException("Unable to generate customer report.",
          exception);
    }
  }

  @Override
  public RemoteDocumentResponse retrieveDocuments(Long trackingNo) {
    try {
      URI uri =
          UriComponentsBuilder.fromUriString(settings.getApiBaseUrl())
              .path("api/Document/GetByTrackingNo")
              .queryParam("trackingNo", trackingNo)
              .build(true)
              .toUri();
      return restClient.get().uri(uri).retrieve().body(RemoteDocumentResponse.class);
    } catch (RestClientException | IllegalArgumentException exception) {
      log.warn("Unable to retrieve additional documents. TrackingNo={}", trackingNo, exception);
      return null;
    }
  }
}
