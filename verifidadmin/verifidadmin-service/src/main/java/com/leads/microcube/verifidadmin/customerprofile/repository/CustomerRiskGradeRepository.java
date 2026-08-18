package com.leads.microcube.verifidadmin.customerprofile.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRiskGradeRepository
    extends JpaRepository<CustomerRiskGradeEntity, Long> {

  Optional<CustomerRiskGradeEntity> findFirstByTrackingNo(Long trackingNo);
}
