package com.leads.microcube.verifidadmin.product.repository;

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
public class ChannelProductId implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer channelId;
  private Integer productCode;
}
