package com.example.BillingSystem.service;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.BillingSystem.exception.BillingSystemAlreadyExist;
import com.example.BillingSystem.exception.BillingSystemInternalException;
import com.example.BillingSystem.model.User;
import com.example.BillingSystem.repository.AuthenticationRepository;

@Service
public class AuthenticationImpl implements AuthenticationService{
	
	private final AuthenticationRepository authenticationRepository;

	public AuthenticationImpl(AuthenticationRepository authenticationRepository) {
		super();
		this.authenticationRepository = authenticationRepository;
	}
	
	@Override
    public User SignUp(User user) {
        try {
            // Check if the email already exists
            if (authenticationRepository.existsByEmail(user.getEmail())) {
                throw new BillingSystemAlreadyExist("Email already exists");
            }
             // Save the customer to the database
            int save = authenticationRepository.save(user);
            if (save == 0) {
                throw new BillingSystemInternalException("Failed to signup to internal DB error");
            }

            return user;

        } catch (DataAccessException e) {
            throw new BillingSystemInternalException("Database error while signup: " + e.getMessage());
        }

	}}
