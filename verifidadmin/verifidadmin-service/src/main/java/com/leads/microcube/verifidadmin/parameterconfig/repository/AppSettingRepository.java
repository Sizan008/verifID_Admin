package com.leads.microcube.verifidadmin.parameterconfig.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Provides persistence operations for application settings. */
@Repository
public interface AppSettingRepository extends JpaRepository<AppSettingEntity, String> {

  List<AppSettingEntity> findAllByOrderByKeyAsc();
}
