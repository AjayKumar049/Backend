package com.example.BillingSystem.exception;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.example.BillingSystem.reponse.ApiResponse;

@ControllerAdvice
public class BillingSystemExceptionHandler {

    // Handle custom exceptions like Item not found
    @ExceptionHandler(BillingSystemNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFound(BillingSystemNotFoundException ex) {
        ApiResponse<Object> response = new ApiResponse<>(null, ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Handle custom exceptions like Item already exists
    @ExceptionHandler(BillingSystemAlreadyExist.class)
    public ResponseEntity<ApiResponse<Object>> handleAlreadyExist(BillingSystemAlreadyExist ex) {
        ApiResponse<Object> response = new ApiResponse<>(null, ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Handle invalid endpoint requests (e.g., 404)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleInvalidEndpoint(NoHandlerFoundException ex) {
        ApiResponse<Object> response = new ApiResponse<>(null, "Invalid endpoint: " + ex.getRequestURL(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    
}
