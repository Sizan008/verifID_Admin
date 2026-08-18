package com.leads.microcube.verifidadmin.customerprofile.query;

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
public class CustomerDashboardMetrics {

  private Long totalCustomers;
  private Long totalAuthorizedCustomers;
  private Long totalUnauthorizedCustomers;
  private Long totalVerifiedCustomers;
  private Long regularEkycCount;
  private Long simplifiedEkycCount;
  private Long currentMonthCount;
}
