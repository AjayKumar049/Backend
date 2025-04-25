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
             // Save the user details to the database
            int save = authenticationRepository.save(user);
            if (save == 0) {
                throw new BillingSystemInternalException("Failed to signup to internal DB error");
            }

            return user;

        } catch (DataAccessException e) {
            throw new BillingSystemInternalException("Database error while signup: " + e.getMessage());
        }

	//Signin
	@Override
	public User Signin(User user) {
	    try {
	        // Try to find the user with the given email and password
	        User existingUser = authenticationRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());

	        if (existingUser == null) {
	            // If no user found, throw BillingSystemNotFoundException with a custom message
	            throw new BillingSystemNotFoundException("User not found with the provided email and password.");
	        }

	        // Successful signin, return the found user
	        return existingUser;

	    } catch (DataAccessException e) {
	        // If there's any DB error, wrap and throw custom exception
	        throw new BillingSystemInternalException("Database error while signing in: " + e.getMessage());
	    }
	}
}


	
	


