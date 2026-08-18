package com.leads.microcube.verifidadmin.customerprofile.repository;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDocumentId implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long trackingNo;
  private Integer documentCode;
}
