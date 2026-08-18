package com.leads.microcube.verifidadmin.customerprofile.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NomineeGuardianRepository
    extends JpaRepository<NomineeGuardianEntity, NomineeGuardianId> {

  Optional<NomineeGuardianEntity> findFirstByTrackingNoAndNomineeNo(
      Long trackingNo, Integer nomineeNo);

  List<NomineeGuardianEntity> findAllByTrackingNoOrderByNomineeNoAscGuardianNoAsc(
      Long trackingNo);

  long countByTrackingNo(Long trackingNo);
}
