package com.saggezza.ecommerce.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saggezza.ecommerce.model.ShoppingCart;

public class ShoppingCartResponse extends BaseResponse {

	@JsonProperty("data")
	private ShoppingCart cart;

	public ShoppingCart getCart() {
		return cart;
	}

	public void setCart(ShoppingCart cart) {
		this.cart = cart;
	}

}
