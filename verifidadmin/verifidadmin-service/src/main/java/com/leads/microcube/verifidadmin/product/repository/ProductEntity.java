package com.leads.microcube.verifidadmin.product.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PARAM_PRODUCTS")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProductEntity {

  @Id
  @Column(name = "PRODUCT_CODE", nullable = false, precision = 20)
  private Integer productCode;

  @Column(name = "APPLICATION_ID", nullable = false, precision = 3)
  private Integer applicationId;

  @Column(name = "PRODUCT_ID", nullable = false, length = 20)
  private String productId;

  @Column(name = "SERVICE_TYPE_ID", length = 20)
  private String serviceTypeId;

  @Column(name = "PRODUCTNAME", nullable = false, length = 100)
  private String productName;

  @Column(name = "PRODUCTDESC", length = 2000)
  private String productDesc;

  @Column(name = "PRODUCT_TYPE", length = 5)
  private String productType;

  @Column(name = "AGE_MIN", nullable = false, precision = 3)
  private Integer ageMin;

  @Column(name = "AGE_MAX", nullable = false, precision = 3)
  private Integer ageMax;

  @Column(name = "GENDER", length = 1)
  private String gender;

  @Column(name = "PROFESSION_ID", length = 5)
  private String professionId;

  @Column(name = "AMOUNT_MAX", nullable = false, precision = 17, scale = 2)
  private BigDecimal amountMax;

  @Column(name = "AMOUNT_MIN", nullable = false, precision = 17, scale = 2)
  private BigDecimal amountMin;

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

  @Column(name = "PRODUCT_GROUP_ID", length = 5)
  private String productGroupId;
}
