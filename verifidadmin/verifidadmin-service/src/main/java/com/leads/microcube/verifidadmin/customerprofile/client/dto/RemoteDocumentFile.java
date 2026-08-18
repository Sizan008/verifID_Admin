package com.leads.microcube.verifidadmin.customerprofile.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RemoteDocumentFile {

  @JsonProperty("FileName")
  private String fileName;

  @JsonProperty("Extension")
  private String extension;

  @JsonProperty("Base64")
  private String base64;

  @JsonProperty("FileSizeBytes")
  private Long fileSizeBytes;
}
