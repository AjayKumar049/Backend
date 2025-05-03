package com.example.billing.dto;

import java.sql.Timestamp;

public class LoginDto {
	private String email;
    private String password;
    private Timestamp logintime;
    
    public LoginDto() {
    	
   }

	public LoginDto(String email, String password, Timestamp logintime) {
		super();
		this.email = email;
		this.password = password;
		this.logintime = logintime;
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
		this.password = password;
	}

	public Timestamp getLogintime() {
		return logintime;
	}

	public void setLogintime(Timestamp logintime) {
		this.logintime = logintime;
	}
    
    
    
    
    

}