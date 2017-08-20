package com.account.mgmt.exception;
public class UnAuthorizedAccessException extends Exception {

	private int code;

	public UnAuthorizedAccessException(int code, String msg) {
		super(msg);
		this.code = code;
	}

	public UnAuthorizedAccessException(String msg) {
		super(msg);
	}
}