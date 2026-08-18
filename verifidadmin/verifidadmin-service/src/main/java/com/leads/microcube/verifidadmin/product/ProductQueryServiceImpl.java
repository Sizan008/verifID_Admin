package com.leads.microcube.verifidadmin.product;

import com.leads.microcube.verifidadmin.channel.repository.ChannelEntity;
import com.leads.microcube.verifidadmin.channel.repository.ChannelRepository;
import com.leads.microcube.verifidadmin.product.exception.ProductNotFoundException;
import com.leads.microcube.verifidadmin.product.exception.ProductValidationException;
import com.leads.microcube.verifidadmin.product.query.AvailableProductResponse;
import com.leads.microcube.verifidadmin.product.query.AvailableProducts;
import com.leads.microcube.verifidadmin.product.query.AvailableProductsResponse;
import com.leads.microcube.verifidadmin.product.query.ChannelProductResponse;
import com.leads.microcube.verifidadmin.product.query.ChannelProducts;
import com.leads.microcube.verifidadmin.product.query.ProductResponse;
import com.leads.microcube.verifidadmin.product.repository.ChannelProductEntity;
import com.leads.microcube.verifidadmin.product.repository.ChannelProductRepository;
import com.leads.microcube.verifidadmin.product.repository.ProductEntity;
import com.leads.microcube.verifidadmin.product.repository.ProductRepository;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductQueryServiceImpl implements ProductQueryService {

  private final ProductRepository productRepository;
  private final ChannelProductRepository channelProductRepository;
  private final ChannelRepository channelRepository;
  private final ProductMapper productMapper;

  @Override
  public List<ChannelProductResponse> retrieveChannelProducts(ChannelProducts query) {
    Integer channelId = requireChannelId(query == null ? null : query.getChannelId());

    try {
      List<ChannelProductEntity> channelProducts =
          channelProductRepository.findAllByChannelIdOrderByProductCodeAsc(channelId);
      if (channelProducts.isEmpty()) {
        throw new ProductNotFoundException("No Data Found.");
      }

      List<Integer> productCodes =
          channelProducts.stream().map(ChannelProductEntity::getProductCode).toList();
      Map<Integer, ProductEntity> productsByCode =
          productRepository.findAllByProductCodeIn(productCodes).stream()
              .collect(Collectors.toMap(ProductEntity::getProductCode, Function.identity()));

      return channelProducts.stream()
          .map(
              channelProduct -> {
                ProductEntity product = productsByCode.get(channelProduct.getProductCode());
                String productName = product == null ? "" : product.getProductName();
                return productMapper.toChannelProductResponse(
                    channelProduct.getProductCode(), productName);
              })
          .toList();
    } catch (ProductNotFoundException exception) {
      throw exception;
    } catch (Exception exception) {
      log.error("Unable to retrieve products for channel {}.", channelId, exception);
      throw new ProductValidationException("Unable to retrieve channel products.");
    }
  }

  @Override
  public AvailableProductsResponse retrieveAvailableProducts(AvailableProducts query) {
    Integer channelId = requireChannelId(query == null ? null : query.getChannelId());

    try {
      ChannelEntity channel =
          channelRepository
              .findById(channelId)
              .orElseThrow(() -> new ProductNotFoundException("Invalid Channel"));
      List<AvailableProductResponse> products =
          productRepository.retrieveAvailableProducts(channelId).stream()
              .map(productMapper::toAvailableProductResponse)
              .toList();
      return AvailableProductsResponse.builder()
          .channelId(channelId)
          .channelName(channel.getChannelName())
          .products(products)
          .build();
    } catch (ProductNotFoundException exception) {
      throw exception;
    } catch (Exception exception) {
      log.error("Unable to retrieve available products for channel {}.", channelId, exception);
      throw new ProductValidationException("Unable to retrieve available products.");
    }
  }

  @Override
  public List<ProductResponse> retrieveProducts() {
    try {
      List<ProductEntity> products = productRepository.findAllByOrderByProductIdAsc();
      if (products.isEmpty()) {
        throw new ProductNotFoundException("No Data Found.");
      }
      return products.stream().map(productMapper::toProductResponse).toList();
    } catch (ProductNotFoundException exception) {
      throw exception;
    } catch (Exception exception) {
      log.error("Unable to retrieve products.", exception);
      throw new ProductValidationException("Unable to retrieve products.");
    }
  }

  private Integer requireChannelId(Integer channelId) {
    if (channelId == null || channelId <= 0) {
      throw new ProductValidationException("Invalid Channel");
    }
    return channelId;
  }
}
