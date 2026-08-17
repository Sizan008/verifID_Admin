package com.leads.microcube.verifidadmin.log.command;

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
public class RecordCurrentUserActivity {

  private Long trackingNo;
  private Integer stepId;
  private String actionType;
  private String actionParticulars;
  private String requestChannel;
}
