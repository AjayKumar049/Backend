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
	//Signup
       @Override
	public User SignUp(User user) {
	    try {
	        // Validate password first
	        if (!isValidPassword(user.getPassword())) {
	            throw new BillingSystemInternalException("Password must contain at least 1 uppercase letter, 1 lowercase letter, 1 digit, 1 special character, and be at least 8 characters long");
	        }

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
	}
    
   //Add this private method here
    private boolean isValidPassword(String password) {
        if (password.length() < 8) {
            return false;
        }
        boolean hasUppercase = password.matches(".*[A-Z].*");
        boolean hasLowercase = password.matches(".*[a-z].*");
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*()-+=<>?].*");

        return hasUppercase && hasLowercase && hasDigit && hasSpecial;
    }


	//Signin
	@Override
	public LoginDto Signin(LoginDto loginDto) {
	    try {
	        // Try to find the user with the given email and password
	        LoginDto existingLogindto = authenticationRepository.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());

	        if (existingLogindto == null) {
	            // If no user found, throw BillingSystemNotFoundException with a custom message
	            throw new BillingSystemNotFoundException("User not found with the provided email and password.");
	        }

	        // Successful signin, return the found user
	        return existingLogindto;

	    } catch (DataAccessException e) {
	        // If there's any DB error, wrap and throw custom exception
	        throw new BillingSystemInternalException("Database error while signing in: " + e.getMessage());
	    }
	}

	
	
}


	
	


