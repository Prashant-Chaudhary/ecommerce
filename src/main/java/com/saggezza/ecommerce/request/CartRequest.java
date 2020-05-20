package com.saggezza.ecommerce.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CartRequest {

	@JsonProperty("product_id")
	long productId;

	@JsonProperty("product_quantity")
	long productQuantity;

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(long productQuantity) {
		this.productQuantity = productQuantity;
	}

	
}
