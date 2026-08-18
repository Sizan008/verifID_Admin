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
public class ProductTypeOption {

  @JsonProperty("ProductTypeId")
  private String productTypeId;

  @JsonProperty("ProductTypeName")
  private String productTypeName;

  @JsonProperty("ProductTypeShortName")
  private String productTypeShortName;
}
