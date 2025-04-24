package com.example.BillingSystem.model;

public class User {
	private String userName;
	private String email;
	private String password;
	
	public User() {}

	public User(String userName, String email, String password) {
		super();
		this.userName = userName;
		this.email = email;
		password = password;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		password = password;
	}
	
}
