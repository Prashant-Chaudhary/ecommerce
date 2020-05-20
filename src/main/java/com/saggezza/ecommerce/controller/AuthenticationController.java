package com.saggezza.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saggezza.ecommerce.logger.ECLogger;
import com.saggezza.ecommerce.request.TokenRequest;
import com.saggezza.ecommerce.response.TokenResponse;
import com.saggezza.ecommerce.utils.ECUtils;
import com.saggezza.ecommerce.utils.HTTPUtil;

import io.swagger.annotations.Api;

/**
 * Authentication Controller handles authentication module
 * 
 * @author Prashant Chaudhary
 *
 */
@RestController
@RequestMapping("/api/v1/auth")
@Api(value = "Authentication Resource")
public class AuthenticationController {

	@Autowired
	@Qualifier("loginRequestValidator")
	protected Validator loginRequestValidator;

	/*
	 * CLASS_NAME contains name of the class to append in logger information.
	 */
	private static final Class<?> CLASS_NAME = AuthenticationController.class;

	@PostMapping("/login")
	public TokenResponse login(@RequestBody TokenRequest tokenRequest, BindingResult result) {
		ECLogger.debug(CLASS_NAME, "Authenticating User by Password: " + tokenRequest.getUserName());
		TokenResponse response = new TokenResponse();
		loginRequestValidator.validate(tokenRequest, result);
		if (result.hasErrors()) {
			ECLogger.debug(CLASS_NAME, "Password Verification Failed");
			ECUtils.setResponseStatus(response, 0, result.getAllErrors().get(0).getDefaultMessage());
		} else {
			ECLogger.debug(CLASS_NAME, "Authentication Successful");
			response.setData(HTTPUtil.generateUserToken(tokenRequest.getUserName() , tokenRequest.getPassword()));
			ECUtils.setResponseStatus(response, 1, "Success");
		}
		return response;
	}

}
