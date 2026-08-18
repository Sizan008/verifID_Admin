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
public class ChannelResponse {

  @JsonProperty("ChannelName")
  private String channelName;

  @JsonProperty("ChannelDesc")
  private String channelDesc;

  @JsonProperty("WorkflowId")
  private Integer workflowId;

  @JsonProperty("EmailConnId")
  private Integer emailConnId;

  @JsonProperty("ApiConnIdSms")
  private Integer apiConnIdSms;

  @JsonProperty("ApiConnIdSdn")
  private Integer apiConnIdSdn;

  @JsonProperty("ApiConnIdCbs")
  private Integer apiConnIdCbs;

  @JsonProperty("ApiConnIdVfApi")
  private Integer apiConnIdVfApi;

  @JsonProperty("ApiConnIdVfMl")
  private Integer apiConnIdVfMl;

  @JsonProperty("ApiConnIdVfRpa")
  private Integer apiConnIdVfRpa;

  @JsonProperty("BizTimeControl")
  private Short bizTimeControl;

  @JsonProperty("BizStartTime")
  private String bizStartTime;

  @JsonProperty("BizEndTime")
  private String bizEndTime;

  @JsonProperty("AlienNetAccess")
  private Short alienNetAccess;
}
