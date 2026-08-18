package com.leads.microcube.verifidadmin.account.query;

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
public class CurrentAccountUserResponse {

  @JsonProperty("UserName")
  private String userName;

  @JsonProperty("UserBranch")
  private String userBranch;

  @JsonProperty("UserBranchName")
  private String userBranchName;
}
