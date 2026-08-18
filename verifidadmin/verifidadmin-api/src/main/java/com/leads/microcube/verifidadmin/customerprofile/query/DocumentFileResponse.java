package com.leads.microcube.verifidadmin.customerprofile.query;

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
public class DocumentFileResponse {

  @JsonProperty("DocumentCode")
  private Integer documentCode;
  @JsonProperty("TrackingNO")
  private Long trackingNo;
  @JsonProperty("DocumentName")
  private String documentName;
  @JsonProperty("DocumentFilePath")
  private String documentFilePath;
  @JsonProperty("DocumentFile")
  private String documentFile;
}
