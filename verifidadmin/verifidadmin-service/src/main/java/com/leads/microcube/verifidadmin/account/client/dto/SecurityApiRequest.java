package com.leads.microcube.verifidadmin.account.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class SecurityApiRequest {

  @JsonProperty("RequestId")
  private String requestId;

  @JsonProperty("RequestCliedIP")
  private String requestClientIp;

  @JsonProperty("RequestCliedAgent")
  private String requestClientAgent;

  @JsonProperty("RequestAppIP")
  private String requestApplicationIp;

  @JsonProperty("RequestAppBaseUrl")
  private String requestApplicationBaseUrl;

  @JsonProperty("BusinessData")
  private String businessData;

  @JsonProperty("FunctionId")
  private String functionId;

  @JsonProperty("BranchId")
  private String branchId;

  @JsonProperty("UserId")
  private String userId;

  @JsonProperty("InstitueId")
  private String instituteId;

  @JsonProperty("SessionId")
  private String sessionId;

  @JsonProperty("RequestDateTime")
  private String requestDateTime;

  @JsonProperty("SessionTimeout")
  private Integer sessionTimeout;
}
