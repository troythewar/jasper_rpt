package com.test.js.jsper;

public class User {
	private String userName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private String email;
	@Override
	public String toString() {
		return "User [userName=" + userName + ", email=" + email + "]";
	}
	
	


}