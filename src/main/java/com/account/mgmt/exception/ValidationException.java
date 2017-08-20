package com.account.mgmt.exception;

/**
 * provides exception handling
 * 
 * @author ramans
 *
 */
public class ValidationException extends Exception {

	private int code;

	public ValidationException(int code, String msg) {
		super(msg);
		this.code = code;
	}

	public ValidationException(String msg) {
		super(msg);
	}
}
