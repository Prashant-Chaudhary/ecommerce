package com.saggezza.ecommerce.context;

import com.saggezza.ecommerce.model.User;

/**
 * Maintain Session Context
 * 
 * @author Prashant Chaudhary
 *
 */
public class SessionContext {

	private transient static ThreadLocal<User> currentUser = new ThreadLocal<>();

	public static ThreadLocal<User> getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(User user) {
		currentUser.set(user);
	}

}
