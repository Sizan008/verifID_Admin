package com.leads.microcube.verifidadmin.ecverification.query;

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
public class EcVerificationResponse {

  @JsonProperty("result")
  private FieldVerificationResponse result;

  @JsonProperty("photo")
  private String photo;
}
