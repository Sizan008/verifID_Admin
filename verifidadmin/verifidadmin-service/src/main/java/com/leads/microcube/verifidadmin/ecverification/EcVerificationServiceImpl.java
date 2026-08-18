package com.leads.microcube.verifidadmin.ecverification;

import com.leads.microcube.verifidadmin.ecverification.client.EcVerificationGateway;
import com.leads.microcube.verifidadmin.ecverification.client.dto.EcFieldVerificationResult;
import com.leads.microcube.verifidadmin.ecverification.client.dto.EcNidVerifiedResponse;
import com.leads.microcube.verifidadmin.ecverification.client.dto.EcVerificationApiResponse;
import com.leads.microcube.verifidadmin.ecverification.command.VerifyEcNid;
import com.leads.microcube.verifidadmin.ecverification.exception.EcVerificationException;
import com.leads.microcube.verifidadmin.ecverification.query.EcVerificationResponse;
import com.leads.microcube.verifidadmin.ecverification.query.FieldVerificationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class EcVerificationServiceImpl implements EcVerificationService {

  private static final String DEFAULT_PHOTO = "~/dist/img/user_profile.png";

  private final EcVerificationGateway ecVerificationGateway;

  @Override
  public EcVerificationResponse process(VerifyEcNid command) {
    EcVerificationApiResponse apiResponse = ecVerificationGateway.verify(command);
    if (!"OK".equalsIgnoreCase(apiResponse.getStatus())) {
      throw new EcVerificationException(
          resolveMessage(apiResponse.getMessage(), "NID verification failed."));
    }

    EcNidVerifiedResponse result = apiResponse.getResult();
    if (result == null) {
      throw new EcVerificationException("NID verification returned no result.");
    }

    String photo =
        result.getSuccess() == null || result.getSuccess().getData() == null
            ? null
            : result.getSuccess().getData().getPhoto();
    return EcVerificationResponse.builder()
        .result(toResponse(result.getFieldVerificationResult()))
        .photo(StringUtils.hasText(photo) ? photo : DEFAULT_PHOTO)
        .build();
  }

  private String resolveMessage(String message, String fallback) {
    return StringUtils.hasText(message) ? message.trim() : fallback;
  }

  private FieldVerificationResponse toResponse(EcFieldVerificationResult result) {
    if (result == null) {
      return null;
    }
    return FieldVerificationResponse.builder()
        .nationalId(result.getNationalId())
        .dateOfBirth(result.getDateOfBirth())
        .name(result.getName())
        .nameEn(result.getNameEn())
        .father(result.getFather())
        .mother(result.getMother())
        .spouse(result.getSpouse())
        .presentAddressMouzaOrMoholla(result.getPresentAddressMouzaOrMoholla())
        .presentAddressWardForUnionPorishod(result.getPresentAddressWardForUnionPorishod())
        .presentAddressUpozila(result.getPresentAddressUpozila())
        .presentAddressDivision(result.getPresentAddressDivision())
        .presentAddressDistrict(result.getPresentAddressDistrict())
        .presentAddressRmo(result.getPresentAddressRmo())
        .presentAddressPostalCode(result.getPresentAddressPostalCode())
        .presentAddressRegion(result.getPresentAddressRegion())
        .presentAddressPostOffice(result.getPresentAddressPostOffice())
        .permanentAddressDivision(result.getPermanentAddressDivision())
        .permanentAddressDistrict(result.getPermanentAddressDistrict())
        .permanentAddressUpozila(result.getPermanentAddressUpozila())
        .permanentAddressRmo(result.getPermanentAddressRmo())
        .permanentAddressPostalCode(result.getPermanentAddressPostalCode())
        .permanentAddressRegion(result.getPermanentAddressRegion())
        .permanentAddressPostOffice(result.getPermanentAddressPostOffice())
        .permanentAddressMouzaOrMoholla(result.getPermanentAddressMouzaOrMoholla())
        .permanentAddressWardForUnionPorishod(
            result.getPermanentAddressWardForUnionPorishod())
        .build();
  }
}
