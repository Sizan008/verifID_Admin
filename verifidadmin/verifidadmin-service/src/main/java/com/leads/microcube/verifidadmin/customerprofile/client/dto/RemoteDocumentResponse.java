package com.leads.microcube.verifidadmin.customerprofile.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RemoteDocumentResponse {

  @JsonProperty("Status")
  private String status;

  @JsonProperty("Message")
  private String message;

  @JsonProperty("TrackingNo")
  private String trackingNo;

  @JsonProperty("Files")
  private List<RemoteDocumentFile> files = new ArrayList<>();

  @JsonProperty("TotalFiles")
  private Integer totalFiles;
}
