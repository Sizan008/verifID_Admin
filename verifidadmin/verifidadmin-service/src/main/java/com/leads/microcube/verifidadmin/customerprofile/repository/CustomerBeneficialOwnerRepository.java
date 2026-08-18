package com.leads.microcube.verifidadmin.customerprofile.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerBeneficialOwnerRepository
    extends JpaRepository<CustomerBeneficialOwnerEntity, CustomerBeneficialOwnerId> {

  Optional<CustomerBeneficialOwnerEntity> findFirstByTrackingNoAndBenifOwnerNo(
      Long trackingNo, Integer benifOwnerNo);

  List<CustomerBeneficialOwnerEntity> findAllByTrackingNoOrderByBenifOwnerNoAsc(Long trackingNo);

  long countByTrackingNo(Long trackingNo);
}
