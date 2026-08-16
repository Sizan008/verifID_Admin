package com.leads.microcube.verifidadmin.common.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentUser implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("UserId")
  private String userId;

  @JsonProperty("LoginId")
  private String loginId;

  @JsonProperty("UserNm")
  private String userName;

  @JsonProperty("HomeBranchId")
  private String homeBranchId;

  @JsonProperty("HomeBranchName")
  private String homeBranchName;

  @JsonProperty("SessionId")
  private String sessionId;

  @Builder.Default
  @JsonProperty("UserFunctionAccess")
  private List<FunctionAccess> userFunctionAccess = new ArrayList<>();
}
