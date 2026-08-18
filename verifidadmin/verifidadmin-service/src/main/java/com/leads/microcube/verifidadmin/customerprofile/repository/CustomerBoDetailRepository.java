package com.leads.microcube.verifidadmin.customerprofile.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerBoDetailRepository
    extends JpaRepository<CustomerBoDetailEntity, Integer> {

  Optional<CustomerBoDetailEntity> findFirstByTrackingNo(Long trackingNo);
}
