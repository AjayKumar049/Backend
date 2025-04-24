package com.example.BillingSystem.dto;

public class ForgotPasswordDto {
	
		private String email;
    
		public ForgotPasswordDto() {}

		public ForgotPasswordDto(String email) {
		super();
		this.email = email;
		}

		public String getEmail() {
		return email;
		}

		public void setEmail(String email) {
		this.email = email;
		}

}
