package com.leads.microcube.verifidadmin.channel.exception;

public class ChannelNotFoundException extends RuntimeException {

  public ChannelNotFoundException(Integer channelId) {
    super("Invalid Channel ID: " + channelId);
  }

  public ChannelNotFoundException(String channelName) {
    super("Invalid Channel Name: " + channelName);
  }
}
