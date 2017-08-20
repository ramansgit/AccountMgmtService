package com.account.mgmt.controller;

import javax.ws.rs.core.Response;

import com.account.mgmt.model.UserSignIn;
import com.account.mgmt.model.UserSignup;

/**
 * AccountMgmt service api
 * 
 * @author ramans
 *
 */
public abstract class AccountMgmtApiService {

	/**
	 * signup request from users
	 * @param data
	 * @return
	 * @throws NotFoundException
	 */
	public abstract Response signup(UserSignup data);

	/**
	 * get user details request from link
	 * @param 
	 * @return
	 * @throws NotFoundException
	 */
	public abstract Response getUserDetails(String jwt);

	
	/**
	 * signin request from users.
	 * @param 
	 * @return
	 * @throws NotFoundException
	 */
	public abstract Response login(UserSignIn signIn);

	
	
	

}
