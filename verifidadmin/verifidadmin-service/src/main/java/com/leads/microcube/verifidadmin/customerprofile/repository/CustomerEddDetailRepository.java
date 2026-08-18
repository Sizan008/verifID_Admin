package com.leads.microcube.verifidadmin.customerprofile.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerEddDetailRepository
    extends JpaRepository<CustomerEddDetailEntity, CustomerEddDetailId> {

  List<CustomerEddDetailEntity> findAllByTrackingNoOrderByCrgEddIdAsc(Long trackingNo);

  long countByTrackingNo(Long trackingNo);
}
