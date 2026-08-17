package com.leads.microcube.verifidadmin.workflow.repository;

import jakarta.persistence.LockModeType;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkflowSequenceRepository
    extends JpaRepository<WorkflowSequenceEntity, Integer> {

  List<WorkflowSequenceEntity> findAllByWorkflowIdOrderByStepSequenceNumberAsc(
      Integer workflowId);

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query(
      """
      select workflowSequence
      from WorkflowSequenceEntity workflowSequence
      where workflowSequence.workflowSequenceId in :sequenceIds
      """)
  List<WorkflowSequenceEntity> retrieveAllForUpdate(
      @Param("sequenceIds") Collection<Integer> sequenceIds);
}
