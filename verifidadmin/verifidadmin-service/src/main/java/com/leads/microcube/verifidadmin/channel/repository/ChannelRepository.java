package com.leads.microcube.verifidadmin.channel.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends JpaRepository<ChannelEntity, Integer> {

  List<ChannelEntity> findAllByOrderByChannelIdAsc();

  Optional<ChannelEntity> findFirstByChannelName(String channelName);
}
