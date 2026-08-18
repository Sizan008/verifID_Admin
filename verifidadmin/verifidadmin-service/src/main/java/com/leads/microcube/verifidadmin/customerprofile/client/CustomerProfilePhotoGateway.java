package com.leads.microcube.verifidadmin.customerprofile.client;

import com.leads.microcube.verifidadmin.customerprofile.query.CustomerPhotosResponse;

public interface CustomerProfilePhotoGateway {

  CustomerPhotosResponse retrieveCustomerPhotos(Long trackingNo);

  String retrieveNomineePhoto(Long trackingNo, Integer nomineeNo);

  String retrieveNomineeNidFront(Long trackingNo, Integer nomineeNo);

  String retrieveNomineeNidBack(Long trackingNo, Integer nomineeNo);

  String retrieveBeneficiaryPhoto(Long trackingNo, Integer beneficiaryNo);

  String retrieveBeneficiaryNidFront(Long trackingNo, Integer beneficiaryNo);

  String retrieveBeneficiaryNidBack(Long trackingNo, Integer beneficiaryNo);

  String retrieveGuardianPhoto(Long trackingNo, Integer nomineeNo);

  String retrieveGuardianNidFront(Long trackingNo, Integer nomineeNo);

  String retrieveGuardianNidBack(Long trackingNo, Integer nomineeNo);
}
