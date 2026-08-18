package com.leads.microcube.verifidadmin.customerprofile.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAdditionalInfoDetailRepository
    extends JpaRepository<CustomerAdditionalInfoDetailEntity, CustomerAdditionalInfoDetailId> {

  Optional<CustomerAdditionalInfoDetailEntity> findFirstByTrackingNoAndPropertyName(
      Long trackingNo, String propertyName);
}
