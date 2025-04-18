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
import com.example.BillingSystem.model.Customer;
import com.example.BillingSystem.reponse.BillingSystemResponseBuilder;
import com.example.BillingSystem.service.CustomerService;
import jakarta.validation.Valid;
@RestController
@RequestMapping("/Customers")
public class CustomerController {
	
    private final CustomerService customerService;
    private final RequestValidationUtil validationUtil;

    public CustomerController(CustomerService customerService, RequestValidationUtil validationUtil) {
        this.customerService = customerService;
        this.validationUtil = validationUtil;
    }
    
    @PostMapping("/add")
    public ResponseEntity<Object> addItem(@Valid @RequestBody Customer customer, BindingResult result) {
        try {
            ResponseEntity<Object> validationResponse = validationUtil.validateRequest(result);
            if (validationResponse != null) {
                return validationResponse;
            }

            Customer addedCustomer = customerService.addCustomer(customer);
            return BillingSystemResponseBuilder.responseBuilder(
                    "Customer added successfully",
                    HttpStatus.CREATED,
                    addedCustomer
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
