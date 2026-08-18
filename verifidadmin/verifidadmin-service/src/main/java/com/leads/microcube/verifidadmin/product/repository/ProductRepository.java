package com.leads.microcube.verifidadmin.product.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

  List<ProductEntity> findAllByOrderByProductIdAsc();

  List<ProductEntity> findAllByProductCodeIn(Collection<Integer> productCodes);

  List<ProductEntity> findAllByProductType(String productType);

  Optional<ProductEntity> findFirstByProductId(String productId);

  @Query("select max(product.productCode) from ProductEntity product")
  Optional<Integer> retrieveMaximumProductCode();

  @Query(
      """
      select product
      from ProductEntity product
      where not exists (
        select channelProduct.productCode
        from ChannelProductEntity channelProduct
        where channelProduct.channelId = :channelId
          and channelProduct.productCode = product.productCode
      )
      order by product.productId
      """)
  List<ProductEntity> retrieveAvailableProducts(@Param("channelId") Integer channelId);
}
