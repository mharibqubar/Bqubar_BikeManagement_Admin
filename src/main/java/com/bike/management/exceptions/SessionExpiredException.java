package com.bike.management.exceptions;

public class SessionExpiredException extends RuntimeException{

	/**
	 * This class is session expired while access the application
	 * Author @Harikrishna
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SessionExpiredException(String msg) {
		super(msg);
	}
}
