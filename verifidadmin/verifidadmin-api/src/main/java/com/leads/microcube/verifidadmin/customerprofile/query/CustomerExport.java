package com.leads.microcube.verifidadmin.customerprofile.query;

import java.time.LocalDate;
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
public class CustomerExport {

  private String authType;
  private String mobileNo;
  private Long trackingNo;
  private String nidNo;
  private String fullName;
  private String customerId;
  private LocalDate dateFrom;
  private LocalDate dateTo;
  private String accountNoFrom;
  private String accountNoTo;
  private String selectedBranchId;
  private String productTypeId;
  private String requestChannel;
  private String year;
  private boolean onlyAccounts;
}
