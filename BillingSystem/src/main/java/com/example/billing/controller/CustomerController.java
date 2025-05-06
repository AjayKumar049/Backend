package com.example.billing.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.billing.exception.BillingSystemAlreadyExist;
import com.example.billing.exception.BillingSystemInternalException;
import com.example.billing.exception.BillingSystemNotFoundException;
import com.example.billing.model.Customer;
import com.example.billing.reponse.BillingSystemResponseBuilder;
import com.example.billing.service.CustomerService;
import com.example.billing.utility.RequestValidationUtil;
import static com.example.billing.constant.BillingSystemConstants.*;


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
                    BillingSystemConstants.INTERNAL_ERROR + ": " + ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        } catch (Exception ex) {
            return BillingSystemResponseBuilder.responseBuilder(
                    BillingSystemConstants.UNEXPECTED_ERROR + ": " + ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
    }
    
    @GetMapping("/all")
    public ResponseEntity<Object> getAllCustomers() {
        try {
            return BillingSystemResponseBuilder.responseBuilder(
                    "Customers fetched successfully",
                    HttpStatus.OK,
                    customerService.getAllCustomers()
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
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateItem(@RequestBody Customer customer) {
        try {
            Customer updatedCustomer = customerService.UpdateCustomer(customer);
            return BillingSystemResponseBuilder.responseBuilder(
                    "Customer updated successfully",
                    HttpStatus.OK,
                    updatedCustomer
            );
        } catch (BillingSystemNotFoundException ex) {
            return BillingSystemResponseBuilder.responseBuilder(
                    ex.getMessage(),
                    HttpStatus.NOT_FOUND,
                    null); }
        catch (BillingSystemInternalException ex) {
            return BillingSystemResponseBuilder.responseBuilder(
                    "Internal server error: " + ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null); }
            
            
        
        catch (Exception ex) {
        	return BillingSystemResponseBuilder.responseBuilder(
        		    "We’re currently experiencing a technical issue while connecting to the system. Please try again later.",
        		    HttpStatus.INTERNAL_SERVER_ERROR,
        		    null
        		);

        }
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> DeleteCustomer(@PathVariable int id, @RequestBody @Valid Customer customer) {
        try {
            customer.setCustomerId(id);
            customerService.DeleteCustomer(customer);
            return BillingSystemResponseBuilder.responseBuilder(
                    "Customer Deleted successfully",
                    HttpStatus.OK,
                    customer // returning the deleted Customer
            );
        } catch (BillingSystemNotFoundException ex) {
            return BillingSystemResponseBuilder.responseBuilder(
                    ex.getMessage(),
                    HttpStatus.NOT_FOUND,
                    null
            );
        }         catch (Exception ex) {
            return BillingSystemResponseBuilder.responseBuilder(
                    "Unexpected error occurred: " + ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
    }
    
    @PostMapping("/search")
    public ResponseEntity<Object> searchCustomerByName(@RequestBody Customer customer) {
        try {
            Customer foundCustomer = customerService.searchCustomerByName(customer);
            return BillingSystemResponseBuilder.responseBuilder(
                    "Customer found successfully",
                    HttpStatus.OK,
                    foundCustomer
            );
        } catch (BillingSystemNotFoundException ex) {
            return BillingSystemResponseBuilder.responseBuilder(
                    ex.getMessage(),
                    HttpStatus.NOT_FOUND,
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
