package com.leads.microcube.verifidadmin.product.query;

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
public class AvailableProductResponse {

  @JsonProperty("ProductCode")
  private Integer productCode;

  @JsonProperty("ProductName")
  private String productName;

  @JsonProperty("ProductDesc")
  private String productDesc;
}
