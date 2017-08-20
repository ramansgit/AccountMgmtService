package com.account.mgmt.exception;

/**
 * provides exception handling 
 * @author ramans
 *
 */
public class MongoDatabaseException extends Exception{

	private int code;
	public MongoDatabaseException (int code, String msg) {
		super(msg);
		this.code = code;
	}
	
	public MongoDatabaseException (String msg) {
		super(msg);
	}
}
