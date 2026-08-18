package com.leads.microcube.verifidadmin.customerprofile.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerSchemeAccountRepository
    extends JpaRepository<CustomerSchemeAccountEntity, Integer> {

  Optional<CustomerSchemeAccountEntity> findFirstByTrackingNo(Long trackingNo);
}
