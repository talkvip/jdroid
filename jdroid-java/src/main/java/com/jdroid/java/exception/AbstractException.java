package com.jdroid.java.exception;

import java.util.Map;
import com.jdroid.java.collections.Maps;

public abstract class AbstractException extends RuntimeException {
	
	private Map<String, Object> parameters = Maps.newHashMap();
	
	/**
	 * Constructor
	 */
	public AbstractException() {
		super();
	}
	
	/**
	 * @param message
	 * @param cause
	 */
	public AbstractException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * @param message
	 */
	public AbstractException(String message) {
		super(message);
	}
	
	/**
	 * @param cause
	 */
	public AbstractException(Throwable cause) {
		super(cause);
	}
	
	public Map<String, Object> getParameters() {
		return parameters;
	}
	
	public Boolean hasParameter(String key) {
		return parameters.containsKey(key);
	}
	
	@SuppressWarnings("unchecked")
	public <E> E getParameter(String key) {
		return (E)parameters.get(key);
	}
	
	public void addParameter(String key, Object value) {
		parameters.put(key, value);
	}
}
