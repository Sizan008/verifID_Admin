package com.leads.microcube.verifidadmin.report.query;

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
public class MergedCustomerPhotoResponse {

  @JsonProperty("pdfData")
  private String pdfData;

  @JsonProperty("result")
  private String result;
}
