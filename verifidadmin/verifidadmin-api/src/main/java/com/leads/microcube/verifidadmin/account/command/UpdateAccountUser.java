package com.leads.microcube.verifidadmin.account.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class UpdateAccountUser {

  @NotBlank
  @Size(max = 200)
  @JsonProperty("UserId")
  private String userId;

  @Size(max = 200)
  @JsonProperty("UserName")
  private String userName;

  @NotBlank
  @Size(max = 8)
  @JsonProperty("UserPassword")
  private String userPassword;

  @NotBlank
  @Size(max = 200)
  @JsonProperty("BranchId")
  private String branchId;

  @NotBlank
  @Size(max = 200)
  @JsonProperty("UserRole")
  private String userRole;
}
