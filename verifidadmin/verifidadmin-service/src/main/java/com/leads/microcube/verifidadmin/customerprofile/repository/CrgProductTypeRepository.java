package com.leads.microcube.verifidadmin.customerprofile.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrgProductTypeRepository
    extends JpaRepository<CrgProductTypeEntity, String> {

  List<CrgProductTypeEntity> findAllByOrderByProductTypeIdAsc();
}
