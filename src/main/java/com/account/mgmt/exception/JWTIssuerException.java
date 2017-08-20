package com.account.mgmt.exception;
public class JWTIssuerException extends Exception {

	private int code;

	public JWTIssuerException(int code, String msg) {
		super(msg);
		this.code = code;
	}

	public JWTIssuerException(String msg) {
		super(msg);
	}
}