package com.leads.microcube.verifidadmin.customerprofile;

import com.leads.microcube.verifidadmin.customerprofile.query.CustomerBeneficiaryResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerGuardianResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerListItem;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerNomineeResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerPaymentResponse;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerBeneficialOwnerEntity;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerNomineeEntity;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerProfileEntity;
import com.leads.microcube.verifidadmin.customerprofile.repository.CustomerSslCommerzPaymentEntity;
import com.leads.microcube.verifidadmin.customerprofile.repository.NomineeGuardianEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class CustomerProfileMapper {

  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  public CustomerListItem toListItem(CustomerProfileEntity entity) {
    return CustomerListItem.builder()
        .fullName(entity.getFullNameEn())
        .motherNameEn(nullSafe(entity.getMotherNameEn()))
        .fatherNameEn(nullSafe(entity.getFatherNameEn()))
        .religion(entity.getReligion())
        .email(entity.getEmail())
        .mobileNo(entity.getMobileNo())
        .nidNo(entity.getNidNo())
        .dateOfBirth(formatDate(entity.getBirthdate()))
        .gender(nullSafe(entity.getGender()))
        .branch(entity.getBranchId())
        .product(entity.getProductId())
        .profession(entity.getProfession())
        .authStatus(entity.getAuthStatus())
        .trackingNo(entity.getTrackingNo())
        .trackingNoText(String.valueOf(entity.getTrackingNo()))
        .faceMatchScore(entity.getFaceMatchScoreRpa())
        .riskScore(entity.getRiskGrading())
        .makeDate(formatDateTime(entity.getMakeDt()))
        .makeBy(entity.getMakeBy())
        .authBy(entity.getAuthBy())
        .authDate(formatDateTime(entity.getAuthDt()))
        .declineReason(entity.getDeclineReason())
        .declineReasonTrimmed(trimDeclineReason(entity.getDeclineReason()))
        .trackingStatus(
            entity.getTrackingStatus() == null ? null : entity.getTrackingStatus().toString())
        .accountNo(entity.getAccountNo())
        .customerId(entity.getCustomerId())
        .permanentAddress(nullSafe(entity.getPermanentAddress()))
        .customerEkycType(
            Integer.valueOf(2).equals(entity.getEkycFlag()) ? "Regular" : "Simplified")
        .sourceOfFund(entity.getSourceOfFund())
        .build();
  }

  public CustomerNomineeResponse toNominee(CustomerNomineeEntity entity) {
    return CustomerNomineeResponse.builder()
        .trackingNo(entity.getTrackingNo())
        .nomineeNo(entity.getNomineeNo())
        .nomineeName(nullSafe(entity.getNomineeName()))
        .nomineeIdType(entity.getNomineeIdType())
        .nomineeIdNo(nullSafe(entity.getNomineeIdNo()))
        .birthdate(nullSafe(formatDate(entity.getBirthdate())))
        .gender(nullSafe(entity.getGender()))
        .religion(entity.getReligion())
        .relation(nullSafe(entity.getRelation()))
        .age(entity.getAge())
        .sharePercent(entity.getSharePercent())
        .motherNameEn(nullSafe(entity.getMotherNameEn()))
        .motherNameBn(nullSafe(entity.getMotherNameBn()))
        .fatherNameEn(nullSafe(entity.getFatherNameEn()))
        .fatherNameBn(nullSafe(entity.getFatherNameBn()))
        .presentAddressEn(nullSafe(entity.getPresentAddressEn()))
        .presentAddressBn(entity.getPresentAddressBn())
        .permanentAddress(nullSafe(entity.getPermanentAddress()))
        .country(entity.getCountry())
        .division(entity.getDivision())
        .district(entity.getDistrict())
        .subDistrict(entity.getSubDistrict())
        .thana(entity.getThana())
        .zipCode(entity.getZipCode())
        .otherInfo(entity.getOtherInfo())
        .status(entity.getStatus())
        .build();
  }

  public CustomerGuardianResponse toGuardian(NomineeGuardianEntity entity) {
    return CustomerGuardianResponse.builder()
        .trackingNo(entity.getTrackingNo())
        .guardianNo(entity.getGuardianNo())
        .nomineeNo(entity.getNomineeNo())
        .guardianName(nullSafe(entity.getGuardianName()))
        .guardianIdType(entity.getGuardianIdType())
        .guardianIdNo(nullSafe(entity.getGuardianIdNo()))
        .birthdate(nullSafe(formatDate(entity.getBirthdate())))
        .gender(nullSafe(entity.getGender()))
        .religion(entity.getReligion())
        .relation(nullSafe(entity.getRelation()))
        .age(entity.getAge())
        .sharePercent(entity.getSharePercent())
        .motherNameEn(nullSafe(entity.getMotherNameEn()))
        .motherNameBn(nullSafe(entity.getMotherNameBn()))
        .fatherNameEn(nullSafe(entity.getFatherNameEn()))
        .fatherNameBn(nullSafe(entity.getFatherNameBn()))
        .presentAddressEn(nullSafe(entity.getPresentAddressEn()))
        .presentAddressBn(entity.getPresentAddressBn())
        .permanentAddress(nullSafe(entity.getPermanentAddress()))
        .country(entity.getCountry())
        .division(entity.getDivision())
        .district(entity.getDistrict())
        .subDistrict(entity.getSubDistrict())
        .thana(entity.getThana())
        .zipCode(entity.getZipCode())
        .otherInfo(entity.getOtherInfo())
        .status(entity.getStatus())
        .build();
  }

  public CustomerBeneficiaryResponse toBeneficiary(CustomerBeneficialOwnerEntity entity) {
    return CustomerBeneficiaryResponse.builder()
        .trackingNo(entity.getTrackingNo())
        .beneficiaryNo(entity.getBenifOwnerNo())
        .beneficiaryName(nullSafe(entity.getBenifOwnerName()))
        .beneficiaryIdType(entity.getNidType())
        .beneficiaryIdNo(nullSafe(entity.getNidNo()))
        .birthdate(nullSafe(formatDate(entity.getBirthdate())))
        .gender(nullSafe(entity.getGender()))
        .religion(entity.getReligion())
        .relation(nullSafe(entity.getRelation()))
        .age(entity.getAge())
        .sharePercent(
            entity.getSharePercent() == null ? null : entity.getSharePercent().doubleValue())
        .motherNameEn(nullSafe(entity.getMotherNameEn()))
        .motherNameBn(nullSafe(entity.getMotherNameBn()))
        .fatherNameEn(nullSafe(entity.getFatherNameEn()))
        .fatherNameBn(nullSafe(entity.getFatherNameBn()))
        .presentAddressEn(nullSafe(entity.getPresentAddressEn()))
        .presentAddressBn(entity.getPresentAddressBn())
        .permanentAddress(nullSafe(entity.getPermanentAddress()))
        .country(entity.getCountry())
        .division(entity.getDivision())
        .district(entity.getDistrict())
        .subDistrict(entity.getSubDistrict())
        .thana(entity.getThana())
        .zipCode(entity.getZipCode())
        .otherInfo(entity.getOtherInfo())
        .status(entity.getStatus())
        .build();
  }

  public CustomerPaymentResponse toPayment(CustomerSslCommerzPaymentEntity entity) {
    if (entity == null) {
      return null;
    }
    return CustomerPaymentResponse.builder()
        .trackingNo(entity.getTrackingNo())
        .customerId(entity.getCustomerId())
        .branchId(entity.getBranchId())
        .accountNo(entity.getAccountNo())
        .referenceId(entity.getReferenceId())
        .makeDt(entity.getMakeDt())
        .responseUrl(entity.getResponseUrl())
        .status(entity.getStatus())
        .transactionDate(entity.getTranDate())
        .validationId(entity.getValId())
        .storeAmount(entity.getStoreAmount())
        .amount(entity.getAmount())
        .cardType(entity.getCardType())
        .cardNo(entity.getCardNo())
        .currency(entity.getCurrency())
        .bankTransactionId(entity.getBankTranId())
        .cardIssuer(entity.getCardIssuer())
        .cardBrand(entity.getCardBrand())
        .cardIssuerCountry(entity.getCardIssuerCountry())
        .cardIssuerCountryCode(entity.getCardIssuerCountryCode())
        .currencyType(entity.getCurrencyType())
        .currencyAmount(entity.getCurrencyAmount())
        .riskLevel(entity.getRiskLevel())
        .riskTitle(entity.getRiskTitle())
        .accountCreditAmount(entity.getAccountCreditAmount())
        .bankChange(entity.getBankChange())
        .cbsbatchNo(entity.getCbsbatchNo())
        .refundStatus(entity.getRefundStatus())
        .refundId(entity.getRefundId())
        .refundError(entity.getRefundError())
        .build();
  }

  private String nullSafe(String value) {
    return value == null ? "" : value;
  }

  private String trimDeclineReason(String value) {
    if (value == null || value.length() <= 20) {
      return value;
    }
    return value.substring(0, 20) + "...";
  }

  public String formatDate(LocalDate value) {
    return value == null ? null : value.format(DATE_FORMAT);
  }

  public String formatDateTime(LocalDateTime value) {
    return value == null ? null : value.toString();
  }
}
