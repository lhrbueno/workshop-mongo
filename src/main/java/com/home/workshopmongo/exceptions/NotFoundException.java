package com.home.workshopmongo.exceptions;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotFoundException(String msg, Throwable e) {
		super(msg, e);
	}
	
	public NotFoundException(String msg) {
		super(msg);
	}
	
}
