package com.saggezza.ecommerce.service;

import java.util.List;

import com.saggezza.ecommerce.model.Product;
import com.saggezza.ecommerce.request.ProductRequest;
import com.saggezza.ecommerce.response.ProductCatalogListResponse;

public interface ProductCatalogService {

	long addProduct(ProductRequest productRequest);

	List<Product> getProductCatalog();
	
}
