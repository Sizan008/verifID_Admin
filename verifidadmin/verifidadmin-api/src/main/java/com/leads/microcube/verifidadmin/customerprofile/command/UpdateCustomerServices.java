package com.leads.microcube.verifidadmin.customerprofile.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerServices {

  private Long trackingNo;
  private Integer smsAlertFlag;
  private Integer emailAlertFlag;
  private Integer debitCardFlag;
  private Integer chequeBookFlag;
}
