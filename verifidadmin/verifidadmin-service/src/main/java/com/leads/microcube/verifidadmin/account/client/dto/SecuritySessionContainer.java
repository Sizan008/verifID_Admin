package com.leads.microcube.verifidadmin.account.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.leads.microcube.verifidadmin.common.security.CurrentUser;
import java.io.Serializable;
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
public class SecuritySessionContainer implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("InstituteId")
  private String instituteId;

  @JsonProperty("InstituteName")
  private String instituteName;

  @JsonProperty("InstituteType")
  private String instituteType;

  @JsonProperty("InstituteServiceType")
  private String instituteServiceType;

  @JsonProperty("InstituteOperationMode")
  private String instituteOperationMode;

  @JsonProperty("InstituteHeadOffice")
  private String instituteHeadOffice;

  @JsonProperty("LocalCurrency")
  private String localCurrency;

  @JsonProperty("CountryId")
  private String countryId;

  @JsonProperty("CountryNm")
  private String countryName;

  @JsonProperty("FunctionId")
  private String functionId;

  @JsonProperty("FastPath")
  private String fastPath;

  @JsonProperty("ServerDate")
  private String serverDate;

  @JsonProperty("UserAnyBrOperationFlag")
  private String userAnyBranchOperationFlag;

  @JsonProperty("LoginUser")
  private CurrentUser loginUser;

  @JsonProperty("ApiAccessToken")
  private String apiAccessToken;

  @JsonProperty("AuthMode")
  private String authMode;
}
