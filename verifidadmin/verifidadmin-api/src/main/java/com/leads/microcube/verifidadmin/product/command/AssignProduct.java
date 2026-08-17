package com.leads.microcube.verifidadmin.product.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
public class AssignProduct {

  @NotNull
  @Min(1)
  @JsonProperty("ProductCode")
  private Integer productCode;

  @NotNull
  @Min(1)
  @JsonProperty("ChannelID")
  private Integer channelId;
}
