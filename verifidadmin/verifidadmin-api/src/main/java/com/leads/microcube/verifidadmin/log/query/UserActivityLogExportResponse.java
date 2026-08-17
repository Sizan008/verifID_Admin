package com.leads.microcube.verifidadmin.log.query;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserActivityLogExportResponse {

  @JsonProperty("pdfData")
  private String pdfData;

  @JsonProperty("docname")
  private String documentName;

  @JsonProperty("result")
  private String result;
}
