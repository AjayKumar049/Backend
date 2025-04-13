package com.example.BillingSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BillingSystemExceptionHandler {

    @ExceptionHandler(BillingSystemNotFoundException.class)
    public ResponseEntity<Object> handleBillingSystemNotFoundException(
            BillingSystemNotFoundException billingSystemNotFoundException) {
        
        BillingSystemException billingSystemException = new BillingSystemException(
                billingSystemNotFoundException.getMessage(),
                billingSystemNotFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(billingSystemException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BillingSystemAlreadyExist.class)
    public ResponseEntity<Object> handleBillingSystemAlreadyExistException(
            BillingSystemAlreadyExist billingSystemAlreadyExistException) {
        
        BillingSystemException billingSystemException = new BillingSystemException(
                billingSystemAlreadyExistException.getMessage(),
                billingSystemAlreadyExistException.getCause(),
                HttpStatus.BAD_REQUEST
        );

        return new ResponseEntity<>(billingSystemException, HttpStatus.BAD_REQUEST);
    }
}
