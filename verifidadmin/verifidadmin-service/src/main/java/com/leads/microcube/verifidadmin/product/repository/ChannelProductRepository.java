package com.leads.microcube.verifidadmin.product.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelProductRepository
    extends JpaRepository<ChannelProductEntity, ChannelProductId> {

  List<ChannelProductEntity> findAllByChannelIdOrderByProductCodeAsc(Integer channelId);

  boolean existsByChannelIdAndProductCode(Integer channelId, Integer productCode);
}
