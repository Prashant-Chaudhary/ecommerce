package com.saggezza.ecommerce.utils;

import org.springframework.stereotype.Component;

import com.saggezza.ecommerce.response.BaseResponse;

/**
 * Handles utility functions
 * 
 * @author Prashant Chaudhary
 *
 */
@Component
public class ECUtils {

	/*
	 * CLASS_NAME contains name of the class to append in logger information.
	 */
	private static final Class<?> CLASS_NAME = ECUtils.class;

	/**
	 * This method sets the response status
	 * 
	 * @param response
	 * @param status
	 * @param message
	 */
	public static void setResponseStatus(BaseResponse response, int status, String message) {
		response.setMessage(message);
		response.setStatusCode(status);
	}

}
