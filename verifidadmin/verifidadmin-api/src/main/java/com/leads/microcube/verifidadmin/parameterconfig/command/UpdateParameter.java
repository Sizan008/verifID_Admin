package com.leads.microcube.verifidadmin.parameterconfig.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Contains values used to update a parameter configuration. */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateParameter {

  @JsonIgnore
  private String currentParamName;

  @NotBlank
  @Size(max = 450)
  @JsonProperty("ParamName")
  private String paramName;

  @NotNull
  @Size(max = 2000)
  @JsonProperty("ParamValue")
  private String paramValue;

  @Size(max = 2000)
  @JsonProperty("Description")
  private String description;

  @Size(max = 2000)
  @JsonProperty("PrivacyLevel")
  private String privacyLevel;
}
