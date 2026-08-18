package com.leads.microcube.verifidadmin.customerprofile.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerNomineeRepository
    extends JpaRepository<CustomerNomineeEntity, CustomerNomineeId> {

  Optional<CustomerNomineeEntity> findFirstByTrackingNoAndNomineeNo(
      Long trackingNo, Integer nomineeNo);

  List<CustomerNomineeEntity> findAllByTrackingNoOrderByNomineeNoAsc(Long trackingNo);

  long countByTrackingNo(Long trackingNo);
}
