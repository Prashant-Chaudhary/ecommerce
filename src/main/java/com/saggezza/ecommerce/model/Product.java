package com.saggezza.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {

	private long id;

	private String productName;

	private long productQuantity;

	/**
	 * @param id
	 * @param productName
	 * @param productQuantity
	 */
	public Product(long id, String productName, long productQuantity) {
		super();
		this.id = id;
		this.productName = productName;
		this.productQuantity = productQuantity;
	}

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
