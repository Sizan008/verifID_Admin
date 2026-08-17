package com.leads.microcube.verifidadmin.workflow;

import com.leads.microcube.verifidadmin.workflow.command.CreateWorkflow;
import com.leads.microcube.verifidadmin.workflow.command.SwapWorkflowSequence;
import com.leads.microcube.verifidadmin.workflow.exception.WorkflowNotFoundException;
import com.leads.microcube.verifidadmin.workflow.exception.WorkflowValidationException;
import com.leads.microcube.verifidadmin.workflow.repository.WorkflowEntity;
import com.leads.microcube.verifidadmin.workflow.repository.WorkflowRepository;
import com.leads.microcube.verifidadmin.workflow.repository.WorkflowSequenceEntity;
import com.leads.microcube.verifidadmin.workflow.repository.WorkflowSequenceRepository;
import com.leads.microcube.verifidadmin.workflow.repository.WorkflowStepEntity;
import com.leads.microcube.verifidadmin.workflow.repository.WorkflowStepRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class WorkflowServiceImpl implements WorkflowService {

  private static final String DEFAULT_USER = "admin";
  private static final String AUTHORIZED_STATUS = "1";

  private final WorkflowRepository workflowRepository;
  private final WorkflowSequenceRepository workflowSequenceRepository;
  private final WorkflowStepRepository workflowStepRepository;

  @Override
  public void process(CreateWorkflow command) {
    if (command == null) {
      throw new WorkflowValidationException("Failed to create new workflow.");
    }

    try {
      Integer workflowId = Math.toIntExact(workflowRepository.count()) + 1;
      LocalDateTime currentTime = LocalDateTime.now();
      WorkflowEntity workflow =
          WorkflowEntity.builder()
              .workflowId(workflowId)
              .workflowName(command.getWorkflowName())
              .workflowDesc(command.getWorkflowDesc())
              .accOpnFlag(command.getAccOpnFlag())
              .accAuthStep(command.getAccAuthStep())
              .makeBy(DEFAULT_USER)
              .makeDt(currentTime)
              .authBy(DEFAULT_USER)
              .authDt(currentTime)
              .authStatus(AUTHORIZED_STATUS)
              .build();
      workflowRepository.save(workflow);
      assignSteps(workflowId, currentTime);
    } catch (WorkflowValidationException exception) {
      throw exception;
    } catch (Exception exception) {
      log.error("Unable to create workflow. Name={}", command.getWorkflowName(), exception);
      throw new WorkflowValidationException("Failed to create new workflow.");
    }
  }

  @Override
  public void process(SwapWorkflowSequence command) {
    validate(command);
    Integer currentId = command.getCurrentWorkflowSequenceId();
    Integer nextId = command.getNextWorkflowSequenceId();

    try {
      List<WorkflowSequenceEntity> sequences =
          workflowSequenceRepository.retrieveAllForUpdate(List.of(currentId, nextId));
      if (sequences.size() != 2) {
        throw new WorkflowNotFoundException("Invalid workflow sequence.");
      }

      WorkflowSequenceEntity current = retrieveSequence(sequences, currentId);
      WorkflowSequenceEntity next = retrieveSequence(sequences, nextId);
      if (!current.getWorkflowId().equals(next.getWorkflowId())) {
        throw new WorkflowValidationException(
            "Workflow sequences must belong to the same workflow.");
      }

      Integer currentSequenceNumber = current.getStepSequenceNumber();
      current.setStepSequenceNumber(next.getStepSequenceNumber());
      next.setStepSequenceNumber(currentSequenceNumber);
      workflowSequenceRepository.saveAll(sequences);
      workflowSequenceRepository.flush();
    } catch (WorkflowNotFoundException | WorkflowValidationException exception) {
      throw exception;
    } catch (Exception exception) {
      log.error(
          "Unable to swap workflow sequences {} and {}.", currentId, nextId, exception);
      throw new WorkflowValidationException("Failed to reorder workflow steps.");
    }
  }

  private void assignSteps(Integer workflowId, LocalDateTime currentTime) {
    List<WorkflowStepEntity> steps = workflowStepRepository.findAllByOrderByStepIdAsc();
    for (int index = 0; index < steps.size(); index++) {
      WorkflowStepEntity step = steps.get(index);
      WorkflowSequenceEntity sequence =
          WorkflowSequenceEntity.builder()
              .workflowId(workflowId)
              .stepSequenceNumber(index + 1)
              .stepId(step.getStepId())
              .makeBy(DEFAULT_USER)
              .makeDt(currentTime)
              .authStatus(AUTHORIZED_STATUS)
              .build();
      workflowSequenceRepository.save(sequence);
    }
  }

  private void validate(SwapWorkflowSequence command) {
    if (command == null
        || command.getCurrentWorkflowSequenceId() == null
        || command.getCurrentWorkflowSequenceId() <= 0
        || command.getNextWorkflowSequenceId() == null
        || command.getNextWorkflowSequenceId() <= 0
        || command.getCurrentWorkflowSequenceId().equals(command.getNextWorkflowSequenceId())) {
      throw new WorkflowValidationException("Invalid workflow sequence.");
    }
  }

  private WorkflowSequenceEntity retrieveSequence(
      List<WorkflowSequenceEntity> sequences, Integer sequenceId) {
    return sequences.stream()
        .filter(sequence -> sequenceId.equals(sequence.getWorkflowSequenceId()))
        .findFirst()
        .orElseThrow(() -> new WorkflowNotFoundException("Invalid workflow sequence."));
  }
}
