package com.leads.microcube.verifidadmin.product;

import com.leads.microcube.verifidadmin.channel.repository.ChannelRepository;
import com.leads.microcube.verifidadmin.common.security.CurrentUser;
import com.leads.microcube.verifidadmin.common.security.CurrentUserProvider;
import com.leads.microcube.verifidadmin.common.security.UnauthenticatedException;
import com.leads.microcube.verifidadmin.product.command.AssignProduct;
import com.leads.microcube.verifidadmin.product.command.CreateProduct;
import com.leads.microcube.verifidadmin.product.command.DeleteProduct;
import com.leads.microcube.verifidadmin.product.exception.ProductNotFoundException;
import com.leads.microcube.verifidadmin.product.exception.ProductValidationException;
import com.leads.microcube.verifidadmin.product.repository.ChannelProductEntity;
import com.leads.microcube.verifidadmin.product.repository.ChannelProductRepository;
import com.leads.microcube.verifidadmin.product.repository.ProductEntity;
import com.leads.microcube.verifidadmin.product.repository.ProductRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

  private static final String DEFAULT_USER = "admin";
  private static final String AUTHORIZED_STATUS = "A";

  private final ProductRepository productRepository;
  private final ChannelProductRepository channelProductRepository;
  private final ChannelRepository channelRepository;
  private final CurrentUserProvider currentUserProvider;

  @Override
  public void process(AssignProduct command) {
    validate(command);
    Integer channelId = command.getChannelId();
    Integer productCode = command.getProductCode();

    if (!channelRepository.existsById(channelId)) {
      throw new ProductNotFoundException("Invalid Channel");
    }
    if (!productRepository.existsById(productCode)) {
      throw new ProductNotFoundException("Invalid Product ID");
    }
    if (channelProductRepository.existsByChannelIdAndProductCode(channelId, productCode)) {
      throw new ProductValidationException("Product is already assigned to the channel.");
    }

    try {
      ChannelProductEntity entity =
          ChannelProductEntity.builder()
              .channelId(channelId)
              .productCode(productCode)
              .authStatus(AUTHORIZED_STATUS)
              .makeBy(resolveUserId())
              .makeDt(LocalDateTime.now())
              .build();
      channelProductRepository.save(entity);
    } catch (Exception exception) {
      log.error(
          "Unable to assign product {} to channel {}.", productCode, channelId, exception);
      throw new ProductValidationException("Failed to assign new product.");
    }
  }

  @Override
  public void process(CreateProduct command) {
    validate(command);

    try {
      Integer productCode = productRepository.retrieveMaximumProductCode().orElse(0) + 1;
      ProductEntity entity =
          ProductEntity.builder()
              .productCode(productCode)
              .applicationId(command.getApplicationId())
              .productId(command.getProductId())
              .serviceTypeId(command.getServiceTypeId())
              .productName(command.getProductName())
              .productDesc(command.getProductDesc())
              .productType(command.getProductType())
              .amountMax(command.getAmountMax())
              .amountMin(command.getAmountMin())
              .gender(command.getGender())
              .professionId(command.getProfession())
              .ageMax(command.getAgeMax())
              .ageMin(command.getAgeMin())
              .authStatus(AUTHORIZED_STATUS)
              .makeBy(resolveUserId())
              .makeDt(LocalDateTime.now())
              .build();
      productRepository.save(entity);
    } catch (Exception exception) {
      log.error("Unable to create product. ProductId={}", command.getProductId(), exception);
      throw new ProductValidationException("Failed to create new product.");
    }
  }

  @Override
  public void process(DeleteProduct command) {
    if (command == null || command.getProductCode() == null || command.getProductCode() <= 0) {
      throw new ProductValidationException("Invalid Product ID");
    }

    Integer productCode = command.getProductCode();
    if (!productRepository.existsById(productCode)) {
      throw new ProductNotFoundException("Invalid Product ID");
    }

    try {
      productRepository.deleteById(productCode);
      productRepository.flush();
    } catch (Exception exception) {
      log.error("Unable to delete product {}.", productCode, exception);
      throw new ProductValidationException("Failed to delete product.");
    }
  }

  private void validate(AssignProduct command) {
    if (command == null
        || command.getChannelId() == null
        || command.getChannelId() <= 0
        || command.getProductCode() == null
        || command.getProductCode() <= 0) {
      throw new ProductValidationException("Failed to assign new product.");
    }
  }

  private void validate(CreateProduct command) {
    if (command == null
        || command.getApplicationId() == null
        || !StringUtils.hasText(command.getProductId())
        || !StringUtils.hasText(command.getServiceTypeId())
        || !StringUtils.hasText(command.getProductName())
        || !StringUtils.hasText(command.getProductType())
        || command.getAmountMax() == null
        || command.getAmountMin() == null
        || command.getAgeMax() == null
        || command.getAgeMin() == null) {
      throw new ProductValidationException("Failed to create new product.");
    }
  }

  private String resolveUserId() {
    try {
      CurrentUser currentUser = currentUserProvider.getCurrentUser();
      if (currentUser != null && StringUtils.hasText(currentUser.getUserId())) {
        return currentUser.getUserId().trim();
      }
    } catch (UnauthenticatedException exception) {
      log.debug("No authenticated user is available for product maintenance.", exception);
    }
    return DEFAULT_USER;
  }
}
