package com.itexchange.demo.jpa.exception;

public class ObjectNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String message) {
		super(message);
	}
	
	public ObjectNotFoundException(Throwable cause) {
		super(cause);
	}

	public ObjectNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
