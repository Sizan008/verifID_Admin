package com.leads.microcube.verifidadmin.parameterconfig.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Represents parameter configuration information returned to the client. */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParameterResponse {

  @JsonProperty("ParamName")
  private String paramName;

  @JsonProperty("ParamValue")
  private String paramValue;

  @JsonProperty("Description")
  private String description;

  @JsonProperty("PrivacyLevel")
  private String privacyLevel;
}
