package com.leads.microcube.verifidadmin.ecverification.client;

import com.leads.microcube.verifidadmin.ecverification.EcVerificationSettings;
import com.leads.microcube.verifidadmin.ecverification.client.dto.EcAddress;
import com.leads.microcube.verifidadmin.ecverification.client.dto.EcIdentify;
import com.leads.microcube.verifidadmin.ecverification.client.dto.EcNidVerificationRequest;
import com.leads.microcube.verifidadmin.ecverification.client.dto.EcVerificationApiResponse;
import com.leads.microcube.verifidadmin.ecverification.client.dto.EcVerify;
import com.leads.microcube.verifidadmin.ecverification.command.EcVerificationAddress;
import com.leads.microcube.verifidadmin.ecverification.command.VerifyEcNid;
import com.leads.microcube.verifidadmin.ecverification.exception.EcVerificationException;
import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
@Slf4j
public class RestEcVerificationGateway implements EcVerificationGateway {

  private final RestClient restClient;
  private final EcVerificationSettings settings;

  public RestEcVerificationGateway(
      RestClient.Builder restClientBuilder,
      EcVerificationSettings settings) {
    this.restClient = restClientBuilder.build();
    this.settings = settings;
  }

  @Override
  public EcVerificationApiResponse verify(VerifyEcNid command) {
    try {
      String url = settings.getApiUrl();
      log.debug("Calling EC verification API at {}.", url);
      EcVerificationApiResponse response =
          restClient
              .post()
              .uri(URI.create(url))
              .contentType(MediaType.APPLICATION_JSON)
              .body(createRequest(command))
              .retrieve()
              .body(EcVerificationApiResponse.class);
      if (response == null) {
        throw new EcVerificationException(
            "Something went wrong. Unable to connect to the remote server.");
      }
      return response;
    } catch (RestClientException | IllegalArgumentException exception) {
      throw new EcVerificationException("EC verification API call failed.", exception);
    }
  }

  private EcNidVerificationRequest createRequest(VerifyEcNid command) {
    String nid = command.getNidOrVoterNoOrFormNoOrVoterId().trim();
    EcVerificationAddress address = command.getPermanentAddress();
    return EcNidVerificationRequest.builder()
        .identify(
            EcIdentify.builder()
                .nid10Digit(nid.length() == 10 ? nid : null)
                .nid17Digit(nid.length() == 17 ? nid : null)
                .build())
        .verify(
            EcVerify.builder()
                .dateOfBirth(command.getDateOfBirth().toString())
                .name(command.getName())
                .nameEn(command.getNameEn())
                .father(command.getFather())
                .mother(command.getMother())
                .spouse(command.getSpouse())
                .permanentAddress(
                    EcAddress.builder()
                        .division(address.getDivision())
                        .district(address.getDistrict())
                        .upozila(address.getUpozila())
                        .postOffice(address.getPostOffice())
                        .postalCode(address.getPostalCode())
                        .build())
                .build())
        .build();
  }
}
