package com.example.billing.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.billing.dto.SignupDto;
import com.example.billing.exception.BillingSystemAlreadyExist;
import com.example.billing.exception.BillingSystemInternalException;
import com.example.billing.exception.BillingSystemNotFoundException;
import com.example.billing.model.User;
import com.example.billing.reponse.BillingSystemResponseBuilder;
import com.example.billing.service.AuthenticationService;
import com.example.billing.utility.RequestValidationUtil;

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
	    public ResponseEntity<Object> addUser(@Valid @RequestBody User user, BindingResult result) {
	        try {
	            ResponseEntity<Object> validationResponse = validationUtil.validateRequest(result);
	            if (validationResponse != null) {
	                return validationResponse;
	            }

	            User addedUser = authenticationService.signUp(user);
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
	    
	  //Signin
		// POST Method
		    @PostMapping("/Signin")
		    public ResponseEntity<Object> signIn(@Valid @RequestBody SignupDto signupDto, BindingResult result) {
		        try {
		            ResponseEntity<Object> validationResponse = validationUtil.validateRequest(result);
		            if (validationResponse != null) {
		                return validationResponse;
		            }

		            SignupDto signIn = authenticationService.signIn(signupDto);
		            return BillingSystemResponseBuilder.responseBuilder(
		                    "Signin Successful",
		                    HttpStatus.OK,
		                    signIn
		            );
		        } catch (BillingSystemNotFoundException ex) {
		            return BillingSystemResponseBuilder.responseBuilder(
		                    ex.getMessage(),
		                    HttpStatus.UNAUTHORIZED,
		                    null
		            );}
		            catch (BillingSystemAlreadyExist ex) {
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

		    

		    

	
	    
	   