package com.leads.microcube.verifidadmin.customerprofile.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDocumentRepository
    extends JpaRepository<CustomerDocumentEntity, CustomerDocumentId> {

  List<CustomerDocumentEntity> findAllByTrackingNoOrderByDocumentCodeAsc(Long trackingNo);

  long countByTrackingNo(Long trackingNo);
}
