package com.saggezza.ecommerce.model;

import java.time.LocalDateTime;

public class User {

	private long id;

	private String email;

	private String first_name;

	private String last_name;

	private String token;

	private long tokenExpiryTime;

	private LocalDateTime tokenCreationTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getTokenExpiryTime() {
		return tokenExpiryTime;
	}

	public void setTokenExpiryTime(long tokenExpiryTime) {
		this.tokenExpiryTime = tokenExpiryTime;
	}

	public LocalDateTime getTokenCreationTime() {
		return tokenCreationTime;
	}

	public void setTokenCreationTime(LocalDateTime tokenCreationTime) {
		this.tokenCreationTime = tokenCreationTime;
	}

}
