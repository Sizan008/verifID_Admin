package com.leads.microcube.verifidadmin.apimanagement.command;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class CreateApiConnection {

  @Size(max = 2000)
  @JsonProperty("ApiConnName")
  private String apiConnName;

  @Size(max = 2000)
  @JsonProperty("ApiConnKey")
  private String apiConnKey;

  @Size(max = 2000)
  @JsonProperty("ApiConnPort")
  private String apiConnPort;

  @Size(max = 2000)
  @JsonProperty("ApiConnUrl")
  private String apiConnUrl;

  @Size(max = 2000)
  @JsonProperty("ApiConnUser")
  private String apiConnUser;

  @Size(max = 2000)
  @JsonProperty("ApiConnPass")
  private String apiConnPass;

  @Size(max = 2000)
  @JsonProperty("ApiCredential")
  private String apiCredential;
}
