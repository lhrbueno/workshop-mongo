package com.home.workshopmongo.exceptions;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String msg, Throwable e) {
		super(msg, e);
	}
	
}
