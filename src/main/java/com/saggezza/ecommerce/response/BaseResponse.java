package com.saggezza.ecommerce.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseResponse {

	@JsonProperty("status")
	private int statusCode;

	@JsonProperty("message")
	private String message;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
