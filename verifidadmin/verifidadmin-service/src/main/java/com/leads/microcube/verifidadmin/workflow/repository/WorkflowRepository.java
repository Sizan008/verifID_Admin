package com.leads.microcube.verifidadmin.workflow.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkflowRepository extends JpaRepository<WorkflowEntity, Integer> {

  List<WorkflowEntity> findAllByOrderByWorkflowIdAsc();
}
