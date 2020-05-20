package com.saggezza.ecommerce.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Token Response Data
 * 
 * @author Prashant CHaudhary
 *
 */
public class TokenResponseData {

	@JsonProperty("access_token")
	private String accessToken;

	@JsonProperty("token_expiry_time")
	private long tokenExpiryTime;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public long getTokenExpiryTime() {
		return tokenExpiryTime;
	}

	public void setTokenExpiryTime(long tokenExpiryTime) {
		this.tokenExpiryTime = tokenExpiryTime;
	}

}
