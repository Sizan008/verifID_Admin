package com.leads.microcube.verifidadmin.product.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
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
public class CreateProduct {

  @Builder.Default
  @NotNull
  @JsonProperty("ApplicationId")
  private Integer applicationId = 3;

  @NotBlank
  @Size(max = 20)
  @JsonProperty("ProductId")
  private String productId;

  @NotBlank
  @Size(max = 20)
  @JsonProperty("ServiceTypeId")
  private String serviceTypeId;

  @NotBlank
  @Size(max = 100)
  @JsonProperty("ProductName")
  private String productName;

  @Size(max = 2000)
  @JsonProperty("ProductDesc")
  private String productDesc;

  @Builder.Default
  @NotBlank
  @Size(max = 5)
  @JsonProperty("ProductType")
  private String productType = "00001";

  @Builder.Default
  @NotNull
  @JsonProperty("AmountMax")
  private BigDecimal amountMax = BigDecimal.ZERO;

  @Builder.Default
  @NotNull
  @JsonProperty("AmountMin")
  private BigDecimal amountMin = BigDecimal.ZERO;

  @Size(max = 1)
  @JsonProperty("Gender")
  private String gender;

  @Size(max = 100)
  @JsonProperty("Profession")
  private String profession;

  @Builder.Default
  @NotNull
  @JsonProperty("AgeMax")
  private Integer ageMax = 0;

  @Builder.Default
  @NotNull
  @JsonProperty("AgeMin")
  private Integer ageMin = 0;
}
