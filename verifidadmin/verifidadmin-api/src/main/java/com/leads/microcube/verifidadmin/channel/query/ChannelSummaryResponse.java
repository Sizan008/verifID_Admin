package com.leads.microcube.verifidadmin.channel.query;

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
public class ChannelSummaryResponse {

  @JsonProperty("ChannelId")
  private Integer channelId;

  @JsonProperty("ChannelName")
  private String channelName;
}
