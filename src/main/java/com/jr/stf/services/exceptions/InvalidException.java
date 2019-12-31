package com.jr.stf.services.exceptions;

public class InvalidException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public InvalidException(String msg) {
		super(msg);
	}
	public InvalidException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
