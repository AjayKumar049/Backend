package com.example.billing.service;

import org.springframework.stereotype.Service;

import com.example.billing.dto.SignupDto;
import com.example.billing.model.User;

@Service
public interface AuthenticationService {
	User signUp(User user);
	SignupDto signIn(SignupDto signupDto);
	
	
	

}
