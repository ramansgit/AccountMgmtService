package com.account.mgmt.model;

public class SignupResp {
	private String userId;
	
	public SignupResp(){
		
	}
	public SignupResp(String userId){
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
