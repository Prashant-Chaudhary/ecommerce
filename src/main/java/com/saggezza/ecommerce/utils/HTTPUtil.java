package com.saggezza.ecommerce.utils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.util.StringUtils;

import com.saggezza.ecommerce.context.SessionContext;
import com.saggezza.ecommerce.logger.ECLogger;
import com.saggezza.ecommerce.model.User;
import com.saggezza.ecommerce.response.TokenResponseData;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HTTPUtil {

	// one instance, reuse
	private final static OkHttpClient httpClient = new OkHttpClient();

	/*
	 * CLASS_NAME contains name of the class to append in logger information.
	 */
	private static final Class<?> CLASS_NAME = HTTPUtil.class;

	public static void main(String[] args) throws Exception {

		HTTPUtil obj = new HTTPUtil();

		System.out.println("Testing 1 - Send Http GET request");
		// obj.sendGet();

		System.out.println("Testing 2 - Send Http POST request");
		obj.verifyPassword("eve.holt@reqres.in", "12345");

	}

	private void sendGet() throws Exception {

		Request request = new Request.Builder().url("https://www.google.com/search?q=mkyong")
				.addHeader("custom-key", "mkyong") // add request headers
				.addHeader("User-Agent", "OkHttp Bot").build();

		try (Response response = httpClient.newCall(request).execute()) {

			if (!response.isSuccessful())
				throw new IOException("Unexpected code " + response);

			// Get response body
			System.out.println(response.body().string());
		}

	}

	private static String sendPost(String userName, String password) throws Exception {
		String result = "";
		// form parameters
		RequestBody formBody = new FormBody.Builder().add("email", userName).add("password", password).build();

		Request request = new Request.Builder().url("https://reqres.in/api/login")
				.addHeader("User-Agent", "Ecommerce App").post(formBody).build();

		try (Response response = httpClient.newCall(request).execute()) {

			if (!response.isSuccessful())
				throw new IOException("Unexpected code " + response);
			result = response.body().string();
			// Get response body
			System.out.println(result);

		}
		return result;
	}

	public static String getToken(String userName, String password) throws Exception {
		String res = sendPost(userName, password);
		String token = "";
		if (!StringUtils.isEmpty(res)) {
			Object obj = new JSONParser().parse(res);
			// typecasting obj to JSONObject
			JSONObject jo = (JSONObject) obj;
			// getting firstName and lastName
			token = (String) jo.get("token");
			System.out.println(token);
		}
		return token;
	}

	public static boolean verifyPassword(String userName, String password) {
		String res;
		try {
			res = sendPost(userName, password);
			String error = "";
			if (!StringUtils.isEmpty(res)) {
				Object obj = new JSONParser().parse(res);
				// typecasting obj to JSONObject
				JSONObject jo = (JSONObject) obj;
				// getting firstName and lastName
				error = (String) jo.get("error");
				if (error == null) {
					return true;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public static TokenResponseData generateUserToken(String username, String password) {
		ECLogger.debug(CLASS_NAME, "Generating token for user with username:" + username);
		TokenResponseData responseData = new TokenResponseData();
		String token = "";
		try {
			token = getToken(username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!StringUtils.isEmpty(token)) {
			User user = new User();
			user.setEmail(username);
			user.setToken(token);
			user.setTokenExpiryTime(LocalDateTime.now().plusDays(2).toInstant(ZoneOffset.UTC).toEpochMilli());
			//SessionContext.setCurrentUser(user);
			EcommerceDataBase.addUpdateUser(user);
			EcommerceDataBase.createCart(user);
			responseData.setAccessToken(token);
			responseData.setTokenExpiryTime(user.getTokenExpiryTime());
			return responseData;

		} else {
			ECLogger.error(CLASS_NAME, "No user found with username:" + username);
		}
		return null;
	}
}
