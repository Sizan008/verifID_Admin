package com.leads.microcube.verifidadmin.customerprofile.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerSslCommerzPaymentRepository
    extends JpaRepository<CustomerSslCommerzPaymentEntity, CustomerSslCommerzPaymentId> {

  List<CustomerSslCommerzPaymentEntity> findAllByTrackingNoOrderByMakeDtDesc(Long trackingNo);

  default Optional<CustomerSslCommerzPaymentEntity> retrieveLatest(Long trackingNo) {
    return findAllByTrackingNoOrderByMakeDtDesc(trackingNo).stream().findFirst();
  }
}
