package com.saggezza.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saggezza.ecommerce.model.ShoppingCart;
import com.saggezza.ecommerce.request.CartRequest;
import com.saggezza.ecommerce.response.BaseResponse;
import com.saggezza.ecommerce.response.ProductCatalogListResponse;
import com.saggezza.ecommerce.response.ShoppingCartResponse;
import com.saggezza.ecommerce.service.ShoppingCartService;
import com.saggezza.ecommerce.utils.ECUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@RequestMapping("/api/v1/cart")
@Api(value = "Saggezza Shopping Cart Resource")
public class ShoppingCartController {
	
	@Autowired
	protected ShoppingCartService shoppingCartService;
	@PostMapping("/add")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "Authorization Token", required = true, dataType = "String", paramType = "header") })
	public BaseResponse addProduct(@RequestBody CartRequest cartRequest) {
		BaseResponse response = new BaseResponse();
		try {
			shoppingCartService.addProductToCart(cartRequest);
			ECUtils.setResponseStatus(response, 1, "Product Added / Updated in Cart Successfully");
		} catch (Exception ex) {
			ECUtils.setResponseStatus(response, 0, "Error Occured in Adding/Updating Product in Cart");
		}

		return response;
	}

	@GetMapping
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Authorization Token", required = true, dataType = "String", paramType = "header") })
	public ShoppingCartResponse getShoppingCart() {
		ShoppingCartResponse response = new ShoppingCartResponse();
		
		try {
			ShoppingCart cart = shoppingCartService.getShoppingCart();
			response.setCart(cart);
			ECUtils.setResponseStatus(response, 1, "Cart ");
		} catch (Exception ex) {
			ECUtils.setResponseStatus(response, 0, "Error Occured in Fetching Cart");
		}

		return response;
	}

	
}
