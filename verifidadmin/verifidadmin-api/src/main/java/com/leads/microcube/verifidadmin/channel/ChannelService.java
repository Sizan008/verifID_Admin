package com.leads.microcube.verifidadmin.channel;

import com.leads.microcube.verifidadmin.channel.command.CreateChannel;
import com.leads.microcube.verifidadmin.channel.command.UpdateChannel;

public interface ChannelService {

  void process(CreateChannel command);

  void process(UpdateChannel command);
}
