package com.leads.microcube.verifidadmin.log.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UserActivityLogRepository
    extends JpaRepository<UserActivityLogEntity, Integer>,
        JpaSpecificationExecutor<UserActivityLogEntity> {

  @Query(
      "select coalesce(max(activity.activitySlNo), 0) "
          + "from UserActivityLogEntity activity")
  Integer retrieveMaximumActivitySerialNumber();
}
