package com.leads.microcube.verifidadmin.customerprofile.query;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class CustomerPageResponse {

  @Builder.Default
  @JsonProperty("Customers")
  private List<CustomerListItem> customers = new ArrayList<>();

  @Builder.Default
  @JsonProperty("ProductTypes")
  private List<ProductTypeOption> productTypes = new ArrayList<>();

  @JsonProperty("PageNumber")
  private int pageNumber;

  @JsonProperty("PageSize")
  private int pageSize;

  @JsonProperty("TotalCount")
  private long totalCount;

  @JsonProperty("TotalPages")
  private int totalPages;

  @JsonProperty("AuthType")
  private String authType;

  @JsonProperty("IsHeadOffice")
  private boolean headOffice;

  @JsonProperty("Model")
  private String model;
}
