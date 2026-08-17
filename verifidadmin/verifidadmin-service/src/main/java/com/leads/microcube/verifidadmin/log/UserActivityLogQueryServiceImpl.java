package com.leads.microcube.verifidadmin.log;

import com.leads.microcube.verifidadmin.log.query.UserActivityLogExportResponse;
import com.leads.microcube.verifidadmin.log.query.UserActivityLogFilter;
import com.leads.microcube.verifidadmin.log.query.UserActivityLogPageResponse;
import com.leads.microcube.verifidadmin.log.query.UserActivityLogResponse;
import com.leads.microcube.verifidadmin.log.repository.UserActivityLogEntity;
import com.leads.microcube.verifidadmin.log.repository.UserActivityLogRepository;
import jakarta.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserActivityLogQueryServiceImpl implements UserActivityLogQueryService {

//  private static final int MAXIMUM_INDEX_RESULT_SIZE = 100000;
  private static final int DEFAULT_PAGE_SIZE = 8;
  private static final Sort ACTION_DATE_DESCENDING =
      Sort.by(Sort.Direction.DESC, "actionDate");

  private final UserActivityLogRepository userActivityLogRepository;
  private final UserActivityLogMapper userActivityLogMapper;
  private final UserActivityLogExcelGenerator userActivityLogExcelGenerator;

  @Override
  public UserActivityLogPageResponse retrieveUserActivities(UserActivityLogFilter filter) {
    UserActivityLogFilter normalizedFilter = normalizeFilter(filter);
    int pageNumber = normalizedFilter.getPageNumber();
    int pageSize = normalizedFilter.getPageSize();
    PageRequest request =
        PageRequest.of(pageNumber - 1, pageSize, ACTION_DATE_DESCENDING);
    Page<UserActivityLogEntity> page =
        userActivityLogRepository.findAll(createSpecification(normalizedFilter), request);

    List<UserActivityLogResponse> items =
        page.getContent().stream().map(userActivityLogMapper::toResponse).toList();
    return UserActivityLogPageResponse.builder()
        .items(items)
        .pageIndex(pageNumber)
        .totalPages(page.getTotalPages())
        .totalCount(page.getTotalElements())
        .hasPreviousPage(page.hasPrevious())
        .hasNextPage(page.hasNext())
        .build();
  }

  @Override
  public UserActivityLogExportResponse retrieveUserActivityExcel(
      UserActivityLogFilter filter) {
    UserActivityLogFilter normalizedFilter = normalizeFilter(filter);
    List<UserActivityLogResponse> activities =
        userActivityLogRepository
            .findAll(createSpecification(normalizedFilter), ACTION_DATE_DESCENDING)
            .stream()
            .map(userActivityLogMapper::toResponse)
            .toList();
    return userActivityLogExcelGenerator.generate(activities, normalizedFilter);
  }

  private UserActivityLogFilter normalizeFilter(UserActivityLogFilter filter) {
    UserActivityLogFilter normalizedFilter =
        filter == null ? new UserActivityLogFilter() : filter;
    if (normalizedFilter.getPageNumber() == null
        || normalizedFilter.getPageNumber() < 1) {
      normalizedFilter.setPageNumber(1);
    }
    if (normalizedFilter.getPageSize() == null || normalizedFilter.getPageSize() < 1) {
      normalizedFilter.setPageSize(DEFAULT_PAGE_SIZE);
    }
    return normalizedFilter;
  }

  private Specification<UserActivityLogEntity> createSpecification(
      UserActivityLogFilter filter) {
    return (root, query, builder) -> {
      List<Predicate> predicates = new ArrayList<>();
      if (filter.getTrackingNo() != null && filter.getTrackingNo() != 0) {
        predicates.add(builder.equal(root.get("trackingNo"), filter.getTrackingNo()));
      }
      if (StringUtils.hasText(filter.getUserId())) {
        predicates.add(builder.equal(root.get("userId"), filter.getUserId()));
      }
      if (filter.getDateFrom() != null && filter.getDateTo() != null) {
        LocalDateTime dateFrom = filter.getDateFrom().atStartOfDay();
        LocalDateTime dateToExclusive = filter.getDateTo().plusDays(1).atStartOfDay();
        predicates.add(
            builder.greaterThanOrEqualTo(
                root.<LocalDateTime>get("actionDate"), dateFrom));
        predicates.add(
            builder.lessThan(root.<LocalDateTime>get("actionDate"), dateToExclusive));
      }
      return builder.and(predicates.toArray(new Predicate[0]));
    };
  }
}
