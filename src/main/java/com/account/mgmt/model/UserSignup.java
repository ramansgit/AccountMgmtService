package com.account.mgmt.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.mongojack.Id;

public class UserSignup {
	@Id
	private String userId;
	@NotNull(message = "username can't be null")
	@NotEmpty(message = "username cann't be empty")
	@Length(min = 4, max = 16, message = "username should be between 4 to 16 characters")
	private String name;
	@NotNull(message = "email can't be null")
	@NotEmpty(message = "email cann't be empty")
	private String email;
	@NotNull(message = "pincode can't be null")
	@NotEmpty(message = "pincode cann't be empty")
	private String pincode;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserSignupPojo [userId=" + userId + ", name=" + name + ", email=" + email + ", pincode=" + pincode
				+ "]";
	}

}
