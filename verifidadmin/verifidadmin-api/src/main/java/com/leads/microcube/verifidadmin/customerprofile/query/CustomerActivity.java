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
public class CustomerActivity {

  private Long trackingNo;
  private String userId;
  private LocalDate dateFrom;
  private LocalDate dateTo;
  private String requestChannel;
  private String branchId;
  private int pageNumber;
  private int pageSize;
}
