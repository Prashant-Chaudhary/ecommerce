package com.saggezza.ecommerce.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.saggezza.ecommerce.context.SessionContext;
import com.saggezza.ecommerce.model.CartItem;
import com.saggezza.ecommerce.model.Product;
import com.saggezza.ecommerce.model.ShoppingCart;
import com.saggezza.ecommerce.model.User;
import com.saggezza.ecommerce.request.CartRequest;
import com.saggezza.ecommerce.service.ShoppingCartService;
import com.saggezza.ecommerce.utils.EcommerceDataBase;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Override
	public boolean addProductToCart(CartRequest cartRequest) {
		User user = SessionContext.getCurrentUser().get();
		Product product = EcommerceDataBase.getExistingProduct(cartRequest.getProductId());
		if (product != null && product.getProductQuantity() >= cartRequest.getProductQuantity()) {
			ShoppingCart cart = EcommerceDataBase.getShoppingCart(user.getEmail());
			List<CartItem> itemList = cart.getItemList();
			long existingCount = 0;
			Iterator<CartItem> iterator = itemList.iterator();
			while (iterator.hasNext()) {
				CartItem item = iterator.next();
				if (item.getProduct().getId() == cartRequest.getProductId()) {
					existingCount = item.getProductQuantity();
					iterator.remove();
					break;
				}

			}
			product.setProductQuantity(product.getProductQuantity() - cartRequest.getProductQuantity());
			EcommerceDataBase.addUpdateProduct(product);
			CartItem item = new CartItem();
			item.setProduct(product);
			item.setProductQuantity(existingCount + cartRequest.getProductQuantity());
			itemList.add(item);
			cart.setItemList(itemList);
			EcommerceDataBase.addUpdateCart(cart);
			return true;
		}
		return false;
	}

	@Override
	public ShoppingCart getShoppingCart() {
		User user = SessionContext.getCurrentUser().get();
		return EcommerceDataBase.getShoppingCart(user.getEmail());

	}

}
