package com.leads.microcube.verifidadmin.home.query;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class DashboardResponse {

  @JsonProperty("total_customers")
  private Long totalCustomers;

  @JsonProperty("total_authorized_customers")
  private Long totalAuthorizedCustomers;

  @JsonProperty("total_unauthorized_customers")
  private Long totalUnauthorizedCustomers;

  @JsonProperty("total_verified_customers")
  private Long totalVerifiedCustomers;

  @JsonProperty("regular_ekyc_count")
  private Long regularEkycCount;

  @JsonProperty("simplified_ekyc_count")
  private Long simplifiedEkycCount;

  @JsonProperty("current_month_count")
  private Long currentMonthCount;

  @JsonProperty("licence_error")
  private String licenceError;
}
