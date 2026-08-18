package com.leads.microcube.verifidadmin.customerprofile.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.LinkedHashMap;
import java.util.Map;
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
public class CustomerProductResponse {

  @JsonProperty("TrackingNo")
  private Long trackingNo;
  @JsonProperty("ProductTypeId")
  private String productTypeId;
  @JsonProperty("ProductTypeName")
  private String productTypeName;
  @JsonProperty("ProductName")
  private String productName;
  @Builder.Default
  @JsonProperty("Details")
  private Map<String, Object> details = new LinkedHashMap<>();
}
