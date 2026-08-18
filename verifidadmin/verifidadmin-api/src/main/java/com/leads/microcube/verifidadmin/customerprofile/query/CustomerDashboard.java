package com.leads.microcube.verifidadmin.customerprofile.query;

import java.time.LocalDateTime;
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
public class CustomerDashboard {

  private String branchId;
  private boolean headOffice;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
}
