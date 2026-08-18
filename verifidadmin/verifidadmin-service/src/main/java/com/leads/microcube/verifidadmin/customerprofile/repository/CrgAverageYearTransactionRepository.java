package com.leads.microcube.verifidadmin.customerprofile.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrgAverageYearTransactionRepository
    extends JpaRepository<CrgAverageYearTransactionEntity, Integer> {

  List<CrgAverageYearTransactionEntity> findAllByOrderByAvgYearTransIdAsc();
}
