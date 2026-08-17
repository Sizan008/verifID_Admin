package com.leads.microcube.verifidadmin.channel;

import com.leads.microcube.verifidadmin.channel.query.ChannelDetails;
import com.leads.microcube.verifidadmin.channel.query.ChannelResponse;
import com.leads.microcube.verifidadmin.channel.query.ChannelSummaryResponse;
import java.util.List;

public interface ChannelQueryService {

  List<ChannelSummaryResponse> retrieveChannels();

  ChannelResponse retrieveChannel(ChannelDetails query);
}
