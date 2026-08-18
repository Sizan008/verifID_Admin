package com.leads.microcube.verifidadmin.customerprofile.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerLoanDetailRepository
    extends JpaRepository<CustomerLoanDetailEntity, Integer> {

  Optional<CustomerLoanDetailEntity> findFirstByTrackingNo(Long trackingNo);
}
