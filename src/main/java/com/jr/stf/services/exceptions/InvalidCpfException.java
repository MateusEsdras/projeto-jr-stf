package com.jr.stf.services.exceptions;

public class InvalidCpfException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public InvalidCpfException(String msg) {
		super(msg);
	}
	public InvalidCpfException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
