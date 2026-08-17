package com.leads.microcube.verifidadmin.channel;

import com.leads.microcube.verifidadmin.channel.command.CreateChannel;
import com.leads.microcube.verifidadmin.channel.command.UpdateChannel;
import com.leads.microcube.verifidadmin.channel.exception.ChannelNotFoundException;
import com.leads.microcube.verifidadmin.channel.exception.ChannelValidationException;
import com.leads.microcube.verifidadmin.channel.repository.ChannelEntity;
import com.leads.microcube.verifidadmin.channel.repository.ChannelRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ChannelServiceImpl implements ChannelService {

  private static final String DEFAULT_USER = "admin";
  private static final String AUTHORIZED_STATUS = "1";

  private final ChannelRepository channelRepository;

  @Override
  public void process(CreateChannel command) {
    validate(command);

    try {
      Integer channelId = Math.toIntExact(channelRepository.count()) + 1;
      LocalDateTime currentTime = LocalDateTime.now();
      ChannelEntity entity =
          ChannelEntity.builder()
              .channelId(channelId)
              .channelName(command.getChannelName())
              .channelDesc(command.getChannelDesc())
              .workflowId(command.getWorkflowId())
              .emailConnId(command.getEmailConnId())
              .apiConnIdSms(command.getApiConnIdSms())
              .apiConnIdSdn(command.getApiConnIdSdn())
              .apiConnIdCbs(command.getApiConnIdCbs())
              .apiConnIdVfApi(command.getApiConnIdVfApi())
              .apiConnIdVfMl(command.getApiConnIdVfMl())
              .apiConnIdVfRpa(command.getApiConnIdVfRpa())
              .bizTimeControl(command.getBizTimeControl())
              .bizStartTime(command.getBizStartTime())
              .bizEndTime(command.getBizEndTime())
              .alienNetAccess(command.getAlienNetAccess())
              .makeBy(DEFAULT_USER)
              .makeDt(currentTime)
              .authBy(DEFAULT_USER)
              .authDt(currentTime)
              .authStatus(AUTHORIZED_STATUS)
              .build();
      channelRepository.save(entity);
    } catch (ChannelValidationException exception) {
      throw exception;
    } catch (Exception exception) {
      log.error("Unable to create channel. Name={}", command.getChannelName(), exception);
      throw new ChannelValidationException("Unable to create channel.");
    }
  }

  @Override
  public void process(UpdateChannel command) {
    validate(command);

    String channelName = command.getChannelName();
    ChannelEntity entity =
        channelRepository
            .findFirstByChannelName(channelName)
            .orElseThrow(() -> new ChannelNotFoundException(channelName));

    try {
      entity.setChannelDesc(command.getChannelDesc());
      entity.setWorkflowId(command.getWorkflowId());
      entity.setEmailConnId(command.getEmailConnId());
      entity.setApiConnIdSms(command.getApiConnIdSms());
      entity.setApiConnIdSdn(command.getApiConnIdSdn());
      entity.setApiConnIdCbs(command.getApiConnIdCbs());
      entity.setApiConnIdVfApi(command.getApiConnIdVfApi());
      entity.setApiConnIdVfMl(command.getApiConnIdVfMl());
      entity.setApiConnIdVfRpa(command.getApiConnIdVfRpa());
      entity.setBizTimeControl(command.getBizTimeControl());
      entity.setBizStartTime(command.getBizStartTime());
      entity.setBizEndTime(command.getBizEndTime());
      entity.setAlienNetAccess(command.getAlienNetAccess());
      channelRepository.save(entity);
    } catch (Exception exception) {
      log.error("Unable to update channel. Name={}", channelName, exception);
      throw new ChannelValidationException("Unable to update channel.");
    }
  }

  private void validate(CreateChannel command) {
    if (command == null || !StringUtils.hasText(command.getChannelName())) {
      throw new ChannelValidationException("Channel name is required.");
    }
    if (command.getWorkflowId() == null) {
      throw new ChannelValidationException("Workflow ID is required.");
    }
  }

  private void validate(UpdateChannel command) {
    if (command == null || !StringUtils.hasText(command.getChannelName())) {
      throw new ChannelValidationException("Channel name is required.");
    }
    if (command.getWorkflowId() == null) {
      throw new ChannelValidationException("Workflow ID is required.");
    }
  }
}
