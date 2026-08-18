package com.leads.microcube.verifidadmin.log.query;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class UserActivityLogResponse {

  @JsonProperty("UserId")
  private String userId;

  @JsonProperty("ActivitySlNo")
  private Integer activitySlNo;

  @JsonProperty("TrackingNo")
  private Long trackingNo;

  @JsonProperty("StepId")
  private Integer stepId;

  @JsonProperty("ActionType")
  private String actionType;

  @JsonProperty("ActionParticulars")
  private String actionParticulars;

  @JsonProperty("ActionDate")
  private LocalDateTime actionDate;

  @JsonProperty("ActionTerminalIp")
  private String actionTerminalIp;
}
