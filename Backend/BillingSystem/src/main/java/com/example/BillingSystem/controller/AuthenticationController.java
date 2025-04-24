package com.example.BillingSystem.controller;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BillingSystem.Utility.RequestValidationUtil;
import com.example.BillingSystem.exception.BillingSystemAlreadyExist;
import com.example.BillingSystem.exception.BillingSystemInternalException;
import com.example.BillingSystem.model.User;
import com.example.BillingSystem.reponse.BillingSystemResponseBuilder;
import com.example.BillingSystem.service.AuthenticationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	 private final AuthenticationService authenticationService;
	 private final RequestValidationUtil validationUtil;

	 public AuthenticationController(AuthenticationService authenticationService, RequestValidationUtil validationUtil) {
	        this.authenticationService = authenticationService;
	        this.validationUtil = validationUtil;
	 }
	 
	// POST Method
	    @PostMapping("/SignUp")
	    public ResponseEntity<Object> addItem(@Valid @RequestBody User user, BindingResult result) {
	        try {
	            ResponseEntity<Object> validationResponse = validationUtil.validateRequest(result);
	            if (validationResponse != null) {
	                return validationResponse;
	            }

	            User addedUser = authenticationService.SignUp(user);
	            return BillingSystemResponseBuilder.responseBuilder(
	                    "Account Created Succesfully",
	                    HttpStatus.CREATED,
	                    addedUser
	            );
	        } catch (BillingSystemAlreadyExist ex) {
	            return BillingSystemResponseBuilder.responseBuilder(
	                    ex.getMessage(),
	                    HttpStatus.BAD_REQUEST,
	                    null
	            );
	        } catch (BillingSystemInternalException ex) {
	            return BillingSystemResponseBuilder.responseBuilder(
	                    "Internal server error: " + ex.getMessage(),
	                    HttpStatus.INTERNAL_SERVER_ERROR,
	                    null
	            );
	        } catch (Exception ex) {
	            return BillingSystemResponseBuilder.responseBuilder(
	                    "Unexpected error occurred: " + ex.getMessage(),
	                    HttpStatus.INTERNAL_SERVER_ERROR,
	                    null
	            );
	        }
	    }

	    

}
