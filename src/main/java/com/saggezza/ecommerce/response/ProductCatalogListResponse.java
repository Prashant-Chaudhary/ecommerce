package com.saggezza.ecommerce.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saggezza.ecommerce.model.Product;

public class ProductCatalogListResponse extends BaseResponse {

	@JsonProperty("data")
	private List<Product> productList;

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

}
