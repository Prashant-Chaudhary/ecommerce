package com.saggezza.ecommerce.response;

/**
 * Token Response
 * 
 * @author Prashant Chaudhary
 *
 */
public class TokenResponse extends BaseResponse {

	private TokenResponseData data;

	public TokenResponseData getData() {
		return data;
	}

	public void setData(TokenResponseData data) {
		this.data = data;
	}

}
