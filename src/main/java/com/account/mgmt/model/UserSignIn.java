package com.account.mgmt.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class UserSignIn {
	@NotNull(message = "userId can't be null")
	@NotEmpty(message = "userId cann't be empty")
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserSignIn [userId=" + userId + "]";
	}
}
