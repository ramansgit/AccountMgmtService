package com.account.mgmt.exception;
public class EmailException extends Exception {

	private int code;

	public EmailException(int code, String msg) {
		super(msg);
		this.code = code;
	}

	public EmailException(String msg) {
		super(msg);
	}
}