package com.leads.microcube.verifidadmin.customerprofile.query;

import lombok.Getter;

@Getter
public class CustomerDetails {

  private final Long trackingNo;
  private final boolean resolveOwner;
  private final boolean checkDebitRestriction;
  private final boolean includeAdminSettings;
  private final boolean requireMobileForPhotos;

  public CustomerDetails(Long trackingNo) {
    this(trackingNo, false, false, false, false);
  }

  public CustomerDetails(
      Long trackingNo,
      boolean resolveOwner,
      boolean checkDebitRestriction,
      boolean includeAdminSettings,
      boolean requireMobileForPhotos) {
    this.trackingNo = trackingNo;
    this.resolveOwner = resolveOwner;
    this.checkDebitRestriction = checkDebitRestriction;
    this.includeAdminSettings = includeAdminSettings;
    this.requireMobileForPhotos = requireMobileForPhotos;
  }
}
