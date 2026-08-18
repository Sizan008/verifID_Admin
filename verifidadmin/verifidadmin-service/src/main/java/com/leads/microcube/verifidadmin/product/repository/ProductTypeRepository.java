package com.leads.microcube.verifidadmin.product.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductTypeEntity, String> {

  List<ProductTypeEntity> findAllByOrderByProductTypeIdAsc();
}
