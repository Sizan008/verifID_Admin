package com.leads.microcube.verifidadmin.customerprofile.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.leads.microcube.verifidadmin.customerprofile.CustomerProfileSettings;
import com.leads.microcube.verifidadmin.customerprofile.client.dto.RemoteStatusResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerPhotosResponse;
import java.net.URI;
import java.util.Iterator;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Slf4j
public class RestCustomerProfilePhotoGateway implements CustomerProfilePhotoGateway {

  private final RestClient restClient;
  private final CustomerProfileSettings settings;

  public RestCustomerProfilePhotoGateway(
      RestClient.Builder restClientBuilder,
      CustomerProfileSettings settings) {
    this.restClient = restClientBuilder.build();
    this.settings = settings;
  }

  @Override
  public CustomerPhotosResponse retrieveCustomerPhotos(Long trackingNo) {
    if (settings.isImageProcessInternal()) {
      return CustomerPhotosResponse.builder()
          .fromNid(CustomerProfileImageFallback.apply(
              retrieveInternal(trackingNo, "GETCUSTNIDSERVERPHOTO")))
          .nidFront(CustomerProfileImageFallback.apply(retrieveInternal(trackingNo, "GETNIDFRONT")))
          .nidBack(CustomerProfileImageFallback.apply(retrieveInternal(trackingNo, "GETNIDBACK")))
          .fromUploaded(CustomerProfileImageFallback.apply(
              retrieveInternal(trackingNo, "GETCUSTPHOTO")))
          .fromSignature(CustomerProfileImageFallback.apply(
              retrieveInternal(trackingNo, "GETSIGNATURE")))
          .build();
    }

    JsonNode customerPhotos = retrieveMlResult("api/getcustphotos/", trackingNo);
    return CustomerPhotosResponse.builder()
        .fromPorichoy(CustomerProfileImageFallback.apply(
            readText(customerPhotos, "from_nid_server")))
        .fromNid(CustomerProfileImageFallback.apply(readText(customerPhotos, "from_nid")))
        .nidFront(CustomerProfileImageFallback.apply(
            readText(retrieveMlResult("api/getnidfront/", trackingNo), "nid_front")))
        .nidBack(CustomerProfileImageFallback.apply(
            readText(retrieveMlResult("api/getnidback/", trackingNo), "nid_back")))
        .fromUploaded(CustomerProfileImageFallback.apply(
            readText(retrieveMlResult("api/getcustphoto/", trackingNo), "from_uploaded")))
        .fromSignature(CustomerProfileImageFallback.apply(
            readText(retrieveMlResult("api/getsignature/", trackingNo), "signature")))
        .build();
  }

  @Override
  public String retrieveNomineePhoto(Long trackingNo, Integer nomineeNo) {
    if (settings.isImageProcessInternal()) {
      return CustomerProfileImageFallback.apply(
          retrieveInternal(trackingNo, "GETNOMINEEPHOTO_" + nomineeNo));
    }
    return CustomerProfileImageFallback.apply(
        readText(
            retrieveMlResult("api/getnomineephoto/", trackingNo),
            "t_nominee_photo_" + nomineeNo));
  }

  @Override
  public String retrieveNomineeNidFront(Long trackingNo, Integer nomineeNo) {
    if (settings.isImageProcessInternal()) {
      return CustomerProfileImageFallback.apply(
          retrieveInternal(trackingNo, "GETNOMINEE_NIDFRONT_" + nomineeNo));
    }
    return CustomerProfileImageFallback.apply(
        readText(
            retrieveMlResult("api/getnomineenidfront/", trackingNo),
            "nid_front_t_nominee_" + nomineeNo));
  }

  @Override
  public String retrieveNomineeNidBack(Long trackingNo, Integer nomineeNo) {
    if (settings.isImageProcessInternal()) {
      return CustomerProfileImageFallback.apply(
          retrieveInternal(trackingNo, "GETNOMINEE_NIDBACK_" + nomineeNo));
    }
    return CustomerProfileImageFallback.apply(
        readText(
            retrieveMlResult("api/getnomineenidback/", trackingNo),
            "nid_back_t_nominee_" + nomineeNo));
  }

  @Override
  public String retrieveBeneficiaryPhoto(Long trackingNo, Integer beneficiaryNo) {
    if (settings.isImageProcessInternal()) {
      return CustomerProfileImageFallback.apply(
          retrieveInternal(trackingNo, "GETBENIFPHOTO_" + beneficiaryNo));
    }
    return CustomerProfileImageFallback.apply(
        readText(
            retrieveMlResult("api/getbenifownerphoto/", trackingNo),
            "t_benifowner_photo_" + beneficiaryNo));
  }

  @Override
  public String retrieveBeneficiaryNidFront(Long trackingNo, Integer beneficiaryNo) {
    if (settings.isImageProcessInternal()) {
      return CustomerProfileImageFallback.apply(
          retrieveInternal(trackingNo, "GETBENIF_NIDFRONT_" + beneficiaryNo));
    }
    return CustomerProfileImageFallback.apply(
        readText(
            retrieveMlResult("api/getbenifnidfront/", trackingNo),
            "nid_front_t_benif_" + beneficiaryNo));
  }

  @Override
  public String retrieveBeneficiaryNidBack(Long trackingNo, Integer beneficiaryNo) {
    if (settings.isImageProcessInternal()) {
      return CustomerProfileImageFallback.apply(
          retrieveInternal(trackingNo, "GETBENIF_NIDBACK_" + beneficiaryNo));
    }
    return CustomerProfileImageFallback.apply(
        readText(
            retrieveMlResult("api/getbenifnidback/", trackingNo),
            "nid_back_t_benif_" + beneficiaryNo));
  }

  @Override
  public String retrieveGuardianPhoto(Long trackingNo, Integer nomineeNo) {
    if (settings.isImageProcessInternal()) {
      return CustomerProfileImageFallback.apply(
          retrieveInternal(trackingNo, "GETGUARDIANPHOTO_" + nomineeNo + "_1"));
    }
    return CustomerProfileImageFallback.apply(
        readText(
            retrieveMlResult("api/getguardianphoto/", trackingNo),
            "t_nominee_guardian_photo_" + nomineeNo + "_1"));
  }

  @Override
  public String retrieveGuardianNidFront(Long trackingNo, Integer nomineeNo) {
    if (settings.isImageProcessInternal()) {
      return CustomerProfileImageFallback.apply(
          retrieveInternal(trackingNo, "GETGUARDIAN_NIDFRONT_" + nomineeNo + "_1"));
    }
    return CustomerProfileImageFallback.apply(
        readText(
            retrieveMlResult("api/getguardiannidfront/", trackingNo),
            "nid_front_t_nominee_guardian_" + nomineeNo + "_1"));
  }

  @Override
  public String retrieveGuardianNidBack(Long trackingNo, Integer nomineeNo) {
    if (settings.isImageProcessInternal()) {
      return CustomerProfileImageFallback.apply(
          retrieveInternal(trackingNo, "GETGUARDIAN_NIDBACK_" + nomineeNo + "_1"));
    }
    return CustomerProfileImageFallback.apply(
        readText(
            retrieveMlResult("api/getguardiannidback/", trackingNo),
            "nid_back_t_nominee_guardian_" + nomineeNo + "_1"));
  }

  private JsonNode retrieveMlResult(String endpointPrefix, Long trackingNo) {
    try {
      URI uri = URI.create(settings.getMlBaseUrl() + endpointPrefix + trackingNo);
      RemoteStatusResponse response =
          restClient.get().uri(uri).retrieve().body(RemoteStatusResponse.class);
      return response == null ? null : response.getResult();
    } catch (RestClientException | IllegalArgumentException exception) {
      log.warn("Unable to retrieve ML photo. Endpoint={}, TrackingNo={}", endpointPrefix,
          trackingNo, exception);
      return null;
    }
  }

  private String retrieveInternal(Long trackingNo, String photoNames) {
    try {
      URI uri =
          UriComponentsBuilder.fromUriString(settings.getApiBaseUrl())
              .path("api/Document/GetCustPhotos")
              .queryParam("trackingNo", trackingNo)
              .queryParam("photoNames", photoNames)
              .build(true)
              .toUri();
      RemoteStatusResponse response =
          restClient.get().uri(uri).retrieve().body(RemoteStatusResponse.class);
      if (response == null || !"SUCCESS".equalsIgnoreCase(response.getStatus())) {
        return null;
      }
      JsonNode result = response.getResult();
      if (result == null || result.isNull()) {
        return null;
      }
      return result.isTextual() ? result.asText() : result.toString();
    } catch (RestClientException | IllegalArgumentException exception) {
      log.warn("Unable to retrieve internal photo. TrackingNo={}, PhotoNames={}", trackingNo,
          photoNames, exception);
      return null;
    }
  }

  private String readText(JsonNode node, String fieldName) {
    JsonNode value = findField(node, fieldName);
    if (value == null || value.isNull()) {
      return null;
    }
    String text = value.isTextual() ? value.asText() : value.toString();
    return StringUtils.hasText(text) ? text : null;
  }

  private JsonNode findField(JsonNode node, String fieldName) {
    if (node == null || node.isNull()) {
      return null;
    }
    if (node.isObject()) {
      JsonNode direct = node.get(fieldName);
      if (direct != null) {
        return direct;
      }
      Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
      while (fields.hasNext()) {
        JsonNode found = findField(fields.next().getValue(), fieldName);
        if (found != null) {
          return found;
        }
      }
    } else if (node.isArray()) {
      for (JsonNode item : node) {
        JsonNode found = findField(item, fieldName);
        if (found != null) {
          return found;
        }
      }
    }
    return null;
  }
}
