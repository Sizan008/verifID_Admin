package com.leads.microcube.verifidadmin.product;

import com.leads.microcube.verifidadmin.product.query.AvailableProducts;
import com.leads.microcube.verifidadmin.product.query.AvailableProductsResponse;
import com.leads.microcube.verifidadmin.product.query.ChannelProductResponse;
import com.leads.microcube.verifidadmin.product.query.ChannelProducts;
import com.leads.microcube.verifidadmin.product.query.ProductResponse;
import java.util.List;

public interface ProductQueryService {

  List<ChannelProductResponse> retrieveChannelProducts(ChannelProducts query);

  AvailableProductsResponse retrieveAvailableProducts(AvailableProducts query);

  List<ProductResponse> retrieveProducts();
}
