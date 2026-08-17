package com.leads.microcube.verifidadmin.channel.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class CreateChannel {

  @NotBlank
  @Size(max = 2000)
  @JsonProperty("ChannelName")
  private String channelName;

  @Size(max = 2000)
  @JsonProperty("ChannelDesc")
  private String channelDesc;

  @NotNull
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

  @Min(0)
  @Max(255)
  @JsonProperty("BizTimeControl")
  private Short bizTimeControl;

  @Size(max = 2000)
  @JsonProperty("BizStartTime")
  private String bizStartTime;

  @Size(max = 2000)
  @JsonProperty("BizEndTime")
  private String bizEndTime;

  @Min(0)
  @Max(255)
  @JsonProperty("AlienNetAccess")
  private Short alienNetAccess;
}
