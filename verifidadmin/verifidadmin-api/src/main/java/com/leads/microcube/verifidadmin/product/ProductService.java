package com.leads.microcube.verifidadmin.product;

import com.leads.microcube.verifidadmin.product.command.AssignProduct;
import com.leads.microcube.verifidadmin.product.command.CreateProduct;
import com.leads.microcube.verifidadmin.product.command.DeleteProduct;

public interface ProductService {

  void process(AssignProduct command);

  void process(CreateProduct command);

  void process(DeleteProduct command);
}
