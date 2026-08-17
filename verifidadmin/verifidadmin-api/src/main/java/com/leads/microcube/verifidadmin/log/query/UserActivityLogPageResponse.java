package com.leads.microcube.verifidadmin.log.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserActivityLogPageResponse {

  @JsonProperty("Items")
  private List<UserActivityLogResponse> items;

  @JsonProperty("PageIndex")
  private Integer pageIndex;

  @JsonProperty("TotalPages")
  private Integer totalPages;

  @JsonProperty("TotalCount")
  private Long totalCount;

  @JsonProperty("HasPreviousPage")
  private Boolean hasPreviousPage;

  @JsonProperty("HasNextPage")
  private Boolean hasNextPage;
}
