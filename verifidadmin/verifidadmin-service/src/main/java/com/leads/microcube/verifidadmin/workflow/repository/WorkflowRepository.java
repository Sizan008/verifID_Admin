package com.leads.microcube.verifidadmin.workflow.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkflowRepository extends JpaRepository<WorkflowEntity, Integer> {

  List<WorkflowEntity> findAllByOrderByWorkflowIdAsc();

  @Query(value = "SELECT NVL(MAX(WORKFLOW_ID), 0) FROM PARAM_WORKFLOWS", nativeQuery = true)
  Integer retrieveMaxWorkflowId();
}
