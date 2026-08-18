package com.leads.microcube.verifidadmin.customerprofile.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerProfileRepository
    extends JpaRepository<CustomerProfileEntity, Long>,
        JpaSpecificationExecutor<CustomerProfileEntity> {

  List<CustomerProfileEntity> findAllByReferenceNoOrderByTrackingNoAsc(Long referenceNo);

  long countByAuthStatus(String authStatus);

  long countByBranchId(String branchId);

  long countByBranchIdAndAuthStatus(String branchId, String authStatus);

  long countByEkycFlag(Integer ekycFlag);

  long countByBranchIdAndEkycFlag(String branchId, Integer ekycFlag);

  long countByUpdateDtBetween(LocalDateTime startDate, LocalDateTime endDate);

  long countByBranchIdAndUpdateDtBetween(
      String branchId, LocalDateTime startDate, LocalDateTime endDate);
}
