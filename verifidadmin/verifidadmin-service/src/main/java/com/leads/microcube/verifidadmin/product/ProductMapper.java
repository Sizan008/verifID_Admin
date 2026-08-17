package com.leads.microcube.verifidadmin.product;

import com.leads.microcube.verifidadmin.product.query.AvailableProductResponse;
import com.leads.microcube.verifidadmin.product.query.ChannelProductResponse;
import com.leads.microcube.verifidadmin.product.query.ProductResponse;
import com.leads.microcube.verifidadmin.product.repository.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

  public ChannelProductResponse toChannelProductResponse(
      Integer productCode, String productName) {
    return ChannelProductResponse.builder()
        .productCode(productCode)
        .productName(productName)
        .build();
  }

  public AvailableProductResponse toAvailableProductResponse(ProductEntity entity) {
    return AvailableProductResponse.builder()
        .productCode(entity.getProductCode())
        .productName(entity.getProductName())
        .productDesc(entity.getProductDesc())
        .build();
  }

  public ProductResponse toProductResponse(ProductEntity entity) {
    return ProductResponse.builder()
        .productCode(entity.getProductCode())
        .productId(entity.getProductId())
        .productName(entity.getProductName())
        .productDesc(entity.getProductDesc())
        .build();
  }
}
