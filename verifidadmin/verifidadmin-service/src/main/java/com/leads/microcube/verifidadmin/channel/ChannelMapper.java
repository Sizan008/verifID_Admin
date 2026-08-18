package com.leads.microcube.verifidadmin.channel;

import com.leads.microcube.verifidadmin.channel.query.ChannelResponse;
import com.leads.microcube.verifidadmin.channel.query.ChannelSummaryResponse;
import com.leads.microcube.verifidadmin.channel.repository.ChannelEntity;
import org.springframework.stereotype.Component;

@Component
public class ChannelMapper {

  public ChannelSummaryResponse toSummaryResponse(ChannelEntity entity) {
    return ChannelSummaryResponse.builder()
        .channelId(entity.getChannelId())
        .channelName(entity.getChannelName())
        .build();
  }

  public ChannelResponse toResponse(ChannelEntity entity) {
    return ChannelResponse.builder()
        .channelName(entity.getChannelName())
        .channelDesc(entity.getChannelDesc())
        .workflowId(entity.getWorkflowId())
        .emailConnId(entity.getEmailConnId())
        .apiConnIdSms(entity.getApiConnIdSms())
        .apiConnIdSdn(entity.getApiConnIdSdn())
        .apiConnIdCbs(entity.getApiConnIdCbs())
        .apiConnIdVfApi(entity.getApiConnIdVfApi())
        .apiConnIdVfMl(entity.getApiConnIdVfMl())
        .apiConnIdVfRpa(entity.getApiConnIdVfRpa())
        .bizTimeControl(entity.getBizTimeControl())
        .bizStartTime(entity.getBizStartTime())
        .bizEndTime(entity.getBizEndTime())
        .alienNetAccess(entity.getAlienNetAccess())
        .build();
  }
}
