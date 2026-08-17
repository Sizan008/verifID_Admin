package com.leads.microcube.verifidadmin.workflow;

import com.leads.microcube.verifidadmin.workflow.exception.WorkflowValidationException;
import com.leads.microcube.verifidadmin.workflow.query.WorkflowSequenceResponse;
import com.leads.microcube.verifidadmin.workflow.query.WorkflowSequences;
import com.leads.microcube.verifidadmin.workflow.query.WorkflowSummaryResponse;
import com.leads.microcube.verifidadmin.workflow.repository.WorkflowEntity;
import com.leads.microcube.verifidadmin.workflow.repository.WorkflowRepository;
import com.leads.microcube.verifidadmin.workflow.repository.WorkflowSequenceEntity;
import com.leads.microcube.verifidadmin.workflow.repository.WorkflowSequenceRepository;
import com.leads.microcube.verifidadmin.workflow.repository.WorkflowStepEntity;
import com.leads.microcube.verifidadmin.workflow.repository.WorkflowStepRepository;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WorkflowQueryServiceImpl implements WorkflowQueryService {

  private final WorkflowRepository workflowRepository;
  private final WorkflowSequenceRepository workflowSequenceRepository;
  private final WorkflowStepRepository workflowStepRepository;
  private final WorkflowMapper workflowMapper;

  @Override
  public List<WorkflowSummaryResponse> retrieveWorkflows() {
    try {
      return workflowRepository.findAllByOrderByWorkflowIdAsc().stream()
          .map(workflowMapper::toSummaryResponse)
          .toList();
    } catch (Exception exception) {
      log.error("Unable to retrieve workflows.", exception);
      throw new WorkflowValidationException("Unable to retrieve workflows.");
    }
  }

  @Override
  public List<WorkflowSequenceResponse> retrieveWorkflowSequences(WorkflowSequences query) {
    Integer workflowId = requireWorkflowId(query == null ? null : query.getWorkflowId());

    try {
      List<WorkflowSequenceEntity> sequences =
          workflowSequenceRepository.findAllByWorkflowIdOrderByStepSequenceNumberAsc(workflowId);
      List<Integer> stepIds = sequences.stream().map(WorkflowSequenceEntity::getStepId).toList();
      Map<Integer, WorkflowStepEntity> stepsById =
          workflowStepRepository.findAllByStepIdIn(stepIds).stream()
              .collect(Collectors.toMap(WorkflowStepEntity::getStepId, Function.identity()));

      return sequences.stream()
          .map(
              sequence -> {
                WorkflowStepEntity step = stepsById.get(sequence.getStepId());
                String stepName = step == null ? null : step.getStepDescription();
                return workflowMapper.toSequenceResponse(sequence, stepName);
              })
          .toList();
    } catch (Exception exception) {
      log.error("Unable to retrieve sequences for workflow {}.", workflowId, exception);
      throw new WorkflowValidationException("Unable to retrieve workflow sequences.");
    }
  }

  private Integer requireWorkflowId(Integer workflowId) {
    if (workflowId == null || workflowId <= 0) {
      throw new WorkflowValidationException("Invalid Workflow");
    }
    return workflowId;
  }
}
