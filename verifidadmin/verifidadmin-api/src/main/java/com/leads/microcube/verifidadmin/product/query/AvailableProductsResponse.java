package com.leads.microcube.verifidadmin.product.query;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class AvailableProductsResponse {

  @JsonProperty("ChannelId")
  private Integer channelId;

  @JsonProperty("ChannelName")
  private String channelName;

  @JsonProperty("Products")
  private List<AvailableProductResponse> products;
}
