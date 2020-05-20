package com.saggezza.ecommerce.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductRequest {

	@JsonProperty("id")
	long id;

	@JsonProperty("product_name")
	String productName;

	@JsonProperty("product_quantity")
	long productQuantity;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public long getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(long productQuantity) {
		this.productQuantity = productQuantity;
	}

}
