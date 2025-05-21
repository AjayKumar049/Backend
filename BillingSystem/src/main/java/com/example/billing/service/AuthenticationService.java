package com.example.billing.service;

import org.springframework.stereotype.Service;

import com.example.billing.dto.SignupDto;
import com.example.billing.model.Users;

@Service
public interface AuthenticationService {
	Users signUp(Users user);
	SignupDto signIn(SignupDto signupDto);

	
	

}
