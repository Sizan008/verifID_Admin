package com.leads.microcube.verifidadmin.common.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class FunctionAccess implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("TargetPath")
  private String targetPath;

  @JsonProperty("AllowAddFlag")
  private Integer allowAddFlag;

  @JsonProperty("AllowEditFlag")
  private Integer allowEditFlag;

  @JsonProperty("AllowDeleteFlag")
  private Integer allowDeleteFlag;

  @JsonProperty("AllowViewFlag")
  private Integer allowViewFlag;

  @JsonProperty("AllowAuthFlag")
  private Integer allowAuthFlag;

  @JsonProperty("AllowProcessFlag")
  private Integer allowProcessFlag;

  @JsonProperty("AllowReportViewFlag")
  private Integer allowReportViewFlag;

  @JsonProperty("AllowReportPrintFlag")
  private Integer allowReportPrintFlag;

  @JsonProperty("AllowReportGenFlag")
  private Integer allowReportGenFlag;
}
