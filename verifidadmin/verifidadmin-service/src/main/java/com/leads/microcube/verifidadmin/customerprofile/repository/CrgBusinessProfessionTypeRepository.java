package com.leads.microcube.verifidadmin.customerprofile.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrgBusinessProfessionTypeRepository
    extends JpaRepository<CrgBusinessProfessionTypeEntity, Integer> {

  List<CrgBusinessProfessionTypeEntity> findAllByBizProfFlagOrderByBizProfTypeIdAsc(
      Integer bizProfFlag);
}
