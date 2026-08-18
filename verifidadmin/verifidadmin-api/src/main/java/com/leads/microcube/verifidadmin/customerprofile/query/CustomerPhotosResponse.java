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
public class CustomerPhotosResponse {

  @JsonProperty("NidFront")
  private String nidFront;

  @JsonProperty("NidBack")
  private String nidBack;

  @JsonProperty("FromNid")
  private String fromNid;

  @JsonProperty("FromPorichoy")
  private String fromPorichoy;

  @JsonProperty("FromUploaded")
  private String fromUploaded;

  @JsonProperty("FromSignature")
  private String fromSignature;
}
