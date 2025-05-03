package com.example.BillingSystem.service;

import org.springframework.stereotype.Service;

import com.example.BillingSystem.model.User;
import com.example.BillingSystem.dto.SignupDto;

@Service
public interface AuthenticationService {
	User signUp(User user);
	SignupDto signIn(SignupDto signupDto);

}
