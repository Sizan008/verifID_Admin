package com.leads.microcube.verifidadmin.customerprofile.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.leads.microcube.verifidadmin.log.query.UserActivityLogResponse;
import java.util.ArrayList;
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
public class CustomerActivityPageResponse {

  @Builder.Default
  @JsonProperty("Logs")
  private List<UserActivityLogResponse> logs = new ArrayList<>();

  @JsonProperty("PageNumber") private int pageNumber;
  @JsonProperty("PageSize") private int pageSize;
  @JsonProperty("TotalCount") private long totalCount;
  @JsonProperty("TotalPages") private int totalPages;
}
