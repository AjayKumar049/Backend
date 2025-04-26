package com.example.BillingSystem.service;

import org.springframework.stereotype.Service;

import com.example.BillingSystem.model.User;

@Service
public interface AuthenticationService {
	User SignUp(User user);
	LoginDto Signin(LoginDto loginDto);
	

}
