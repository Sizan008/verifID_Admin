package com.leads.microcube.verifidadmin.log.query;

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
public class UserActivityLogFilter {

  private Long trackingNo;
  private String userId;
  private LocalDate dateFrom;
  private LocalDate dateTo;
  private String requestChannel;
  private String branchId;
  private Integer pageNumber;
  private Integer pageSize;
}
