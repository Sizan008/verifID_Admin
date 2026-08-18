package com.leads.microcube.verifidadmin.product.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PARAM_PRODUCT_TYPES")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProductTypeEntity {

  @Id
  @Column(name = "PRODUCT_TYPE_ID", nullable = false, length = 5)
  private String productTypeId;

  @Column(name = "PRODUCT_TYPE_NM", nullable = false, length = 200)
  private String productTypeName;

  @Column(name = "PRODUCT_TYPE_SH_NM", nullable = false, length = 10)
  private String productTypeShortName;

  @Column(name = "MAKEBY", nullable = false, length = 30)
  private String makeBy;

  @Column(name = "MAKEDT", nullable = false)
  private LocalDateTime makeDt;

  @Column(name = "CHECKBY", length = 30)
  private String checkBy;

  @Column(name = "CHECKDT")
  private LocalDateTime checkDt;

  @Column(name = "VERIFYBY", length = 30)
  private String verifyBy;

  @Column(name = "VERIFYDT")
  private LocalDateTime verifyDt;

  @Column(name = "AUTHBY", length = 30)
  private String authBy;

  @Column(name = "AUTHDT")
  private LocalDateTime authDt;

  @Column(name = "UPDATEBY", length = 30)
  private String updateBy;

  @Column(name = "UPDATEDT")
  private LocalDateTime updateDt;

  @Column(name = "AUTHSTATUS", length = 1)
  private String authStatus;
}
