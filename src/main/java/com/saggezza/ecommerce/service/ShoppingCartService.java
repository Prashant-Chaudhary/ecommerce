package com.saggezza.ecommerce.service;

import com.saggezza.ecommerce.model.ShoppingCart;
import com.saggezza.ecommerce.request.CartRequest;

public interface ShoppingCartService {

	boolean addProductToCart(CartRequest cartRequest);

	ShoppingCart getShoppingCart();
	
}
