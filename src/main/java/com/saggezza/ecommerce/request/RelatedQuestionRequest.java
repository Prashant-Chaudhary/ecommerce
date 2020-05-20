package com.saggezza.ecommerce.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saggezza.ecommerce.model.Product;

public class RelatedQuestionRequest {

	@JsonProperty("data")
	List<Product> list;

	public List<Product> getList() {
		return list;
	}

	public void setList(List<Product> list) {
		this.list = list;
	}

}
