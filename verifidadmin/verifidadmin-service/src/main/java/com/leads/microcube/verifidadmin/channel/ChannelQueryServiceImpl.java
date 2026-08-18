package com.leads.microcube.verifidadmin.channel;

import com.leads.microcube.verifidadmin.channel.exception.ChannelNotFoundException;
import com.leads.microcube.verifidadmin.channel.exception.ChannelValidationException;
import com.leads.microcube.verifidadmin.channel.query.ChannelDetails;
import com.leads.microcube.verifidadmin.channel.query.ChannelResponse;
import com.leads.microcube.verifidadmin.channel.query.ChannelSummaryResponse;
import com.leads.microcube.verifidadmin.channel.repository.ChannelEntity;
import com.leads.microcube.verifidadmin.channel.repository.ChannelRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChannelQueryServiceImpl implements ChannelQueryService {

  private final ChannelRepository channelRepository;
  private final ChannelMapper channelMapper;

  @Override
  public List<ChannelSummaryResponse> retrieveChannels() {
    try {
      return channelRepository.findAllByOrderByChannelIdAsc().stream()
          .map(channelMapper::toSummaryResponse)
          .toList();
    } catch (Exception exception) {
      log.error("Unable to retrieve channels.", exception);
      throw new ChannelValidationException("Unable to retrieve channels.");
    }
  }

  @Override
  public ChannelResponse retrieveChannel(ChannelDetails query) {
    if (query == null || query.getChannelId() == null || query.getChannelId() <= 0) {
      throw new ChannelValidationException("Channel ID is required.");
    }

    Integer channelId = query.getChannelId();
    ChannelEntity entity =
        channelRepository
            .findById(channelId)
            .orElseThrow(() -> new ChannelNotFoundException(channelId));
    return channelMapper.toResponse(entity);
  }
}
