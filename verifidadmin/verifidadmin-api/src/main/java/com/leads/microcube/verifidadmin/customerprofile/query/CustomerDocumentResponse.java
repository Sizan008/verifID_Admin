package com.leads.microcube.verifidadmin.customerprofile.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
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
public class CustomerDocumentResponse {

  @Builder.Default
  @JsonProperty("DocumentList")
  private List<DocumentFileResponse> documentList = new ArrayList<>();
}
