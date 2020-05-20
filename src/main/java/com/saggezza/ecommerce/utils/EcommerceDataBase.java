package com.saggezza.ecommerce.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.saggezza.ecommerce.model.CartItem;
import com.saggezza.ecommerce.model.Product;
import com.saggezza.ecommerce.model.ShoppingCart;
import com.saggezza.ecommerce.model.User;

public class EcommerceDataBase {

	public static Map<Long, Product> productList = new HashMap<Long, Product>();

	public static Map<String, User> userList = new HashMap<String, User>();

	public static Map<String, ShoppingCart> cartList = new HashMap<String, ShoppingCart>();

	public static Product getExistingProduct(long id) {
		return productList.get(id);
	}

	public static void addUpdateProduct(Product product) {
		productList.put(product.getId(), product);
	}

	public static User getExistingUser(String token) {
		return userList.get(token);
	}

	public static void addUpdateUser(User user) {
		userList.put(user.getToken(), user);
	}

	public static ShoppingCart getShoppingCart(String id) {
		return cartList.get(id);
	}

	public static void addUpdateCart(ShoppingCart cart) {
		cartList.put(cart.getUserMail(), cart);
	}

	public static void createCart(User user) {
		ShoppingCart cart = new ShoppingCart();
		cart.setCartId(System.currentTimeMillis());
		cart.setUserMail(user.getEmail());
		List<CartItem> itemList = new ArrayList<CartItem>();
		cart.setItemList(itemList);
		cartList.put(user.getEmail(), cart);
	}

}
