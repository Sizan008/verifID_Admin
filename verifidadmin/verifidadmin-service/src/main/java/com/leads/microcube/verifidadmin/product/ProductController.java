package com.leads.microcube.verifidadmin.product;

import com.leads.microcube.verifidadmin.channel.ChannelQueryService;
import com.leads.microcube.verifidadmin.channel.query.ChannelSummaryResponse;
import com.leads.microcube.verifidadmin.common.response.ApiResponse;
import com.leads.microcube.verifidadmin.common.security.PermissionType;
import com.leads.microcube.verifidadmin.common.security.RequirePermission;
import com.leads.microcube.verifidadmin.log.UserActivityLogService;
import com.leads.microcube.verifidadmin.log.command.RecordCurrentUserActivity;
import com.leads.microcube.verifidadmin.product.command.AssignProduct;
import com.leads.microcube.verifidadmin.product.command.CreateProduct;
import com.leads.microcube.verifidadmin.product.command.DeleteProduct;
import com.leads.microcube.verifidadmin.product.query.AvailableProducts;
import com.leads.microcube.verifidadmin.product.query.AvailableProductsResponse;
import com.leads.microcube.verifidadmin.product.query.ChannelProductResponse;
import com.leads.microcube.verifidadmin.product.query.ChannelProducts;
import com.leads.microcube.verifidadmin.product.query.ProductResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Product")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;
  private final ProductQueryService productQueryService;
  private final ChannelQueryService channelQueryService;
  private final UserActivityLogService userActivityLogService;

  @GetMapping("/Index")
  @RequirePermission(targetPath = "Product/Index")
  public ResponseEntity<ApiResponse<List<ChannelSummaryResponse>>> retrieveChannels() {
    List<ChannelSummaryResponse> channels = channelQueryService.retrieveChannels();
    return ResponseEntity.ok(ApiResponse.success(channels));
  }

  @GetMapping("/ProductListOfChannel")
  @RequirePermission(targetPath = "Product/Index")
  public ResponseEntity<ApiResponse<List<ChannelProductResponse>>> retrieveChannelProducts(
      @RequestParam("id") Integer id) {
    List<ChannelProductResponse> products =
        productQueryService.retrieveChannelProducts(new ChannelProducts(id));
    return ResponseEntity.ok(ApiResponse.success(products));
  }

  @GetMapping("/AddNewProductToChannel")
  @RequirePermission(targetPath = "Product/Index", value = PermissionType.ADD)
  public ResponseEntity<ApiResponse<AvailableProductsResponse>> retrieveAvailableProducts(
      @RequestParam("id") Integer id) {
    AvailableProductsResponse response =
        productQueryService.retrieveAvailableProducts(new AvailableProducts(id));
    return ResponseEntity.ok(ApiResponse.success(response));
  }

  @PostMapping("/AddProduct")
  @RequirePermission(targetPath = "Product/Index", value = PermissionType.ADD)
  public ResponseEntity<ApiResponse<Void>> assignProduct(
      @Valid @RequestBody AssignProduct command) {
    productService.process(command);
    userActivityLogService.process(
        RecordCurrentUserActivity.builder()
            .trackingNo(0L)
            .stepId(0)
            .actionType("AddProduct")
            .actionParticulars("is Assigning New Product")
            .requestChannel("")
            .build());
    return ResponseEntity.ok(ApiResponse.success("Successfully new product assigned.", null));
  }

  @GetMapping("/ListOfProducts")
  @RequirePermission(targetPath = "Product/Index")
  public ResponseEntity<ApiResponse<List<ProductResponse>>> retrieveProducts() {
    List<ProductResponse> products = productQueryService.retrieveProducts();
    return ResponseEntity.ok(ApiResponse.success(products));
  }

  @GetMapping("/Create")
  @RequirePermission(targetPath = "Product/Index", value = PermissionType.ADD)
  public ResponseEntity<ApiResponse<CreateProduct>> retrieveCreateForm() {
    return ResponseEntity.ok(ApiResponse.success(new CreateProduct()));
  }

  @PostMapping("/Create")
  @RequirePermission(targetPath = "Product/Index", value = PermissionType.ADD)
  public ResponseEntity<ApiResponse<Void>> registerProduct(
      @Valid @RequestBody CreateProduct command) {
    productService.process(command);
    userActivityLogService.process(
        RecordCurrentUserActivity.builder()
            .trackingNo(0L)
            .stepId(0)
            .actionType("Create")
            .actionParticulars("is Creating New Product")
            .requestChannel("")
            .build());
    return ResponseEntity.ok(ApiResponse.success("Successfully new product created.", null));
  }

  @PostMapping("/DeleteFromProductList")
  @RequirePermission(targetPath = "Product/Index", value = PermissionType.DELETE)
  public ResponseEntity<ApiResponse<Void>> deleteProduct(
      @RequestParam("id") Integer id) {
    productService.process(new DeleteProduct(id));
    return ResponseEntity.ok(ApiResponse.success("Successfully product deleted.", null));
  }
}
