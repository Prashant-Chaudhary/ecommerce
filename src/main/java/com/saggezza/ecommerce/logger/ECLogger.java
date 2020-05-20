package com.saggezza.ecommerce.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Ecommerce Logger Class.
 * 
 * @author Prashant Chaudhary
 */
@Component
public class ECLogger {

	public static final Logger logger = LoggerFactory.getLogger(ECLogger.class);

	/**
	 * To log the information in the logger.
	 * 
	 * @param clazz, The class name
	 * @param log,   The log info
	 */
	public static void info(Class<?> clazz, String log) {
		logger.info("{} - {}", clazz, log);
	}

	/**
	 * To log the debug information in the logger.
	 * 
	 * @param clazz, The class name
	 * @param log,   The log info
	 */
	public static void debug(Class<?> clazz, String log) {
		logger.debug("{} - {}", clazz, log);
	}

	/**
	 * To log the error information in the logger.
	 * 
	 * @param clazz, The class name
	 * @param log,   The log info
	 */
	public static void error(Class<?> clazz, String log) {
		logger.error("{} - {}", clazz, log);
	}

	/**
	 * To log the error information in the logger.
	 * 
	 * @param clazz, The class name
	 * @param log,   The log info
	 * @param e,     Exception object
	 */
	public static void error(Class<?> clazz, String log, Exception e) {
		logger.error("{} - {} - {}", clazz, log, e);
	}

}
