package com.saggezza.ecommerce.validators;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.saggezza.ecommerce.logger.ECLogger;
import com.saggezza.ecommerce.request.TokenRequest;
import com.saggezza.ecommerce.utils.HTTPUtil;

/**
 * login rerquest Validator
 * 
 * @author Prashant
 *
 */
@Component("loginRequestValidator")
public class LoginRequestValidator implements Validator {

	/*
	 * CLASS_NAME contains name of the class to append in logger information.
	 */
	private static final Class<?> CLASS_NAME = LoginRequestValidator.class;

	@Override
	public boolean supports(Class<?> clazz) {
		return TokenRequest.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		TokenRequest tokenRequest = (TokenRequest) obj;
		if (StringUtils.isEmpty(tokenRequest.getUserName()) || StringUtils.isEmpty(tokenRequest.getPassword())) {
			ECLogger.error(CLASS_NAME, "Username or Password is missing");
			errors.reject("username", "Both Username and Password are required");
		} else if (!HTTPUtil.verifyPassword(tokenRequest.getUserName(), tokenRequest.getPassword())) {
			ECLogger.error(CLASS_NAME, "Username or OTP is Invalid");
			errors.reject("inavlid-credentials", "Username or OTP is Invalid");
		}
	}

}
