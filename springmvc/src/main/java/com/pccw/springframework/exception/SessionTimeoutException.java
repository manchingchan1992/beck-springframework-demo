package com.pccw.springframework.exception;

public class SessionTimeoutException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public SessionTimeoutException(String msg) {
		super(msg);
	}
}
