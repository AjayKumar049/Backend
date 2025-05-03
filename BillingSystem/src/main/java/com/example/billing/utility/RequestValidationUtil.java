package com.example.billing.utility;
import org.springframework.validation.BindingResult;

import com.example.billing.reponse.BillingSystemResponseBuilder;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@Component
public class RequestValidationUtil {

    // Method to handle validation errors and return a ResponseEntity with the error messages
    public ResponseEntity<Object> validateRequest(BindingResult result) {
        if (result.hasErrors()) {
            // Collecting all error messages
            List<String> errorMessages = new ArrayList<>();
            result.getFieldErrors().forEach(error ->
                errorMessages.add(error.getField() + ": " + error.getDefaultMessage())
            );
            // Returning a response with a BAD_REQUEST status and all error messages
            return BillingSystemResponseBuilder.responseBuilder(
                    String.join("; ", errorMessages),
                    HttpStatus.BAD_REQUEST,
                    null
            );
        }
        return null; // No errors, so continue with processing
    }
}
