package com.leads.microcube.verifidadmin.apimanagement.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiConnectionRepository extends JpaRepository<ApiConnectionEntity, Integer> {

  List<ApiConnectionEntity> findAllByOrderByApiConnIdAsc();

  @Query("select coalesce(max(connection.apiConnId), 0) from ApiConnectionEntity connection")
  Integer findMaximumApiConnectionId();
}
