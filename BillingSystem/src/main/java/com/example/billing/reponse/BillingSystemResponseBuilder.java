package com.example.billing.reponse;

import java.util.HashMap;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BillingSystemResponseBuilder {

    // Private constructor to prevent instantiation
    private BillingSystemResponseBuilder() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    public static ResponseEntity<Object> responseBuilder(
            String message, HttpStatus httpStatus, Object responseObject
    ) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("httpStatus", httpStatus);
        response.put("data", responseObject);

        return new ResponseEntity<>(response, httpStatus);
    }
}
