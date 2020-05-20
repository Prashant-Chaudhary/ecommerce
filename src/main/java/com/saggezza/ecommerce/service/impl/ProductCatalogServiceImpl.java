package com.saggezza.ecommerce.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.saggezza.ecommerce.model.Product;
import com.saggezza.ecommerce.request.ProductRequest;
import com.saggezza.ecommerce.service.ProductCatalogService;
import com.saggezza.ecommerce.utils.EcommerceDataBase;

@Service
public class ProductCatalogServiceImpl implements ProductCatalogService {

	@Override
	public long addProduct(ProductRequest productRequest) {
		if (productRequest != null) {
			if (productRequest.getId() == 0) {
				Product product = new Product(System.currentTimeMillis(), productRequest.getProductName(),
						productRequest.getProductQuantity());
				EcommerceDataBase.addUpdateProduct(product);
			} else {
				Product existingProduct = EcommerceDataBase.getExistingProduct(productRequest.getId());
				if (existingProduct != null) {
					existingProduct.setProductName(productRequest.getProductName());
					existingProduct.setProductQuantity(
							existingProduct.getProductQuantity() + productRequest.getProductQuantity());
					EcommerceDataBase.addUpdateProduct(existingProduct);
				}
			}

		}
		return 0;
	}

	@Override
	public List<Product> getProductCatalog() {
		Map<Long, Product> productList = EcommerceDataBase.productList;
		List<Product> productCatalogList = new ArrayList<Product>();
		for (Map.Entry<Long, Product> product : productList.entrySet()) {
			productCatalogList.add(product.getValue());
		}

		return productCatalogList;
	}

}
