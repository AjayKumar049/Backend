package com.example.billing.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.dao.DataAccessException;



import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.billing.dto.SignupDto;
import com.example.billing.exception.BillingSystemAlreadyExist;
import com.example.billing.exception.BillingSystemInternalException;
import com.example.billing.exception.BillingSystemNotFoundException;
import com.example.billing.model.Users;
import com.example.billing.repository.AuthenticationRepository;

@Service
public class AuthenticationImpl implements AuthenticationService{
	
	private final AuthenticationRepository authenticationRepository;
	private final PasswordEncoder passwordEncoder;

	public AuthenticationImpl(AuthenticationRepository authenticationRepository, PasswordEncoder passwordEncoder) {
	    this.authenticationRepository = authenticationRepository;
	    this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public User signUp(Users user) {
	    try {
	        // Validate password first
	        if (!isValidPassword(user.getPassword())) {
	            throw new BillingSystemInternalException(
	                "Password must contain at least 1 uppercase letter, 1 lowercase letter, 1 digit, 1 special character, and be at least 8 characters long"
	            );
	        }

	        // Check if the email already exists
	        if (authenticationRepository.existsByEmail(user.getEmail())) {
	            throw new BillingSystemAlreadyExist("Email already exists");
	        }

	        // Encode the password before saving
	        String encodedPassword = passwordEncoder.encode(user.getPassword());
	        user.setPassword(encodedPassword);

	        // Save the user details to the database
	        int save = authenticationRepository.save(user);
	        if (save == 0) {
	            throw new BillingSystemInternalException("Failed to signup due to internal DB error");
	        }

	        return user;

	    } catch (DataAccessException e) {
	        throw new BillingSystemInternalException("Database error while signup: " + e.getMessage());
	    }
	}

	private boolean isValidPassword(String password) {
	    if (password == null || password.length() < 8) {
	        return false;
	    }

	    boolean hasUppercase = password.matches(".*[A-Z].*");
	    boolean hasLowercase = password.matches(".*[a-z].*");
	    boolean hasDigit = password.matches(".*\\d.*");

	    String specialChars = "!@#$%^&*()-+=<>?";
	    Set<Character> seenSpecials = new HashSet<>();
	    boolean hasSpecial = false;

	    for (char c : password.toCharArray()) {
	        if (specialChars.indexOf(c) >= 0) {
	            if (seenSpecials.contains(c)) {
	                return false; // Duplicate special character
	            }
	            seenSpecials.add(c);
	            hasSpecial = true;
	        }
	    }

	    return hasUppercase && hasLowercase && hasDigit && hasSpecial;
	}


    @Override
    public SignupDto signIn(SignupDto signupDto) {
        try {
            // Fetch the user by email
            SignupDto existingUser = authenticationRepository.findByEmailAndPassword(signupDto.getEmail(), signupDto.getPassword());

            // Check if user exists
            if (existingUser == null) {
                throw new BillingSystemNotFoundException("User not found with the provided email.");
            }

            // Verify the password using passwordEncoder.matches() to compare hashed passwords
            if (!passwordEncoder.matches(signupDto.getPassword(), existingUser.getPassword())) {
                throw new BillingSystemNotFoundException("Invalid password.");
            }

            // Successful signin, return the found user without password
            existingUser.setPassword(null); // Never return password in DTO
            return existingUser;

        } catch (DataAccessException e) {
            // Handle database error
            throw new BillingSystemInternalException("Database error while signing in: " + e.getMessage());
        } catch (BillingSystemNotFoundException e) {
            // Handle user not found or invalid password
            throw e;  // Re-throw the exception
        } catch (Exception e) {
            // Handle any other unexpected errors
            throw new BillingSystemInternalException("Unexpected error: " + e.getMessage());
        }
    }

   


}

	
	
	
