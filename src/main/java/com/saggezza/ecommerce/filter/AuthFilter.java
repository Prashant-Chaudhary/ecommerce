package com.saggezza.ecommerce.filter;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

import com.saggezza.ecommerce.context.SessionContext;
import com.saggezza.ecommerce.logger.ECLogger;
import com.saggezza.ecommerce.model.User;
import com.saggezza.ecommerce.utils.EcommerceConstants;
import com.saggezza.ecommerce.utils.EcommerceDataBase;
import com.saggezza.ecommerce.utils.HTTPUtil;

/**
 * This is a web filter that authenticates the web apis
 *
 * @author Prashant Chaudhary
 *
 */
@WebFilter(urlPatterns = { "/api/*" })
public class AuthFilter implements Filter {

	/*
	 * CLASS_NAME contains name of the class to append in logger information.
	 */
	private static final Class<?> CLASS_NAME = AuthFilter.class;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if (!req.getRequestURI().contains("auth")) {
			HttpServletResponse res = (HttpServletResponse) response;
			String authHeader = req.getHeader(EcommerceConstants.AUTHORIZATION);
			String token = getTokenFromHeader(authHeader);
			if (StringUtils.isEmpty(token)) {
				ECLogger.error(CLASS_NAME, "Unauthorized Request empty token");
				res.sendError(HttpServletResponse.SC_UNAUTHORIZED, EcommerceConstants.ERROR_UNAUTHORIZED);
			} else {
				if (EcommerceDataBase.getExistingUser(token) == null) {
					ECLogger.error(CLASS_NAME, "Unauthorized Request token invalid");
					res.sendError(HttpServletResponse.SC_UNAUTHORIZED, EcommerceConstants.TOKEN_INVALID);
				} else {
					ECLogger.info(CLASS_NAME, "Request Authorized");
					User user = EcommerceDataBase.getExistingUser(token);
					user.setTokenCreationTime(LocalDateTime.now());
					SessionContext.setCurrentUser(user);
					chain.doFilter(request, response);
				}
			}
		} else {
			chain.doFilter(request, response);
		}

	}

	/**
	 * This method extracts token from header
	 *
	 * @param authHeader
	 * @return token
	 */
	private String getTokenFromHeader(String authHeader) {
		String token = null;
		if (!StringUtils.isEmpty(authHeader) && authHeader.startsWith(EcommerceConstants.AUTHORIZATION_HEADER)) {
			String[] arr = authHeader.split(EcommerceConstants.SPACE);
			token = arr.length > 1 ? arr[1] : null;
		}
		return token;
	}

}
