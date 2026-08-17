package com.leads.microcube.verifidadmin.workflow.repository;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkflowStepRepository extends JpaRepository<WorkflowStepEntity, Integer> {

  List<WorkflowStepEntity> findAllByOrderByStepIdAsc();

  List<WorkflowStepEntity> findAllByStepIdIn(Collection<Integer> stepIds);
}
