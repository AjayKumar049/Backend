package com.example.billing.controller;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.billing.constant.BillingSystemConstants;
import com.example.billing.exception.BillingSystemAlreadyExist;
import com.example.billing.exception.BillingSystemInternalException;
import com.example.billing.exception.BillingSystemNotFoundException;
import com.example.billing.model.Customer;
import com.example.billing.reponse.BillingSystemResponseBuilder;
import com.example.billing.service.CustomerService;
import com.example.billing.utility.RequestValidationUtil;
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
	public ResponseEntity<Object> addCustomer(@Valid @RequestBody Customer customer, BindingResult result) {
		ResponseEntity<Object> validationResponse = validationUtil.validateRequest(result);
		if (validationResponse != null) {
			return validationResponse;
		}

		try {
			Customer addedCustomer = customerService.addCustomer(customer);
			return BillingSystemResponseBuilder.responseBuilder("Customer added successfully", HttpStatus.CREATED,
					addedCustomer);
		} catch (BillingSystemAlreadyExist ex) {
			return BillingSystemResponseBuilder.responseBuilder(ex.getMessage(), HttpStatus.BAD_REQUEST, null);
		} catch (BillingSystemInternalException ex) {
			return BillingSystemResponseBuilder.responseBuilder(
					BillingSystemConstants.INTERNAL_ERROR + ": " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
					null);
		} catch (Exception ex) {
			return BillingSystemResponseBuilder.responseBuilder(
					BillingSystemConstants.UNEXPECTED_ERROR + ": " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
					null);
		}
	}

	@GetMapping("/all")
	public ResponseEntity<Object> getAllCustomers() {
		try {
			return BillingSystemResponseBuilder.responseBuilder("Customers fetched successfully", HttpStatus.OK,
					customerService.getAllCustomers());
		} catch (BillingSystemInternalException ex) {
			return BillingSystemResponseBuilder.responseBuilder(
					BillingSystemConstants.INTERNAL_ERROR + ": " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
					null);
		} catch (Exception ex) {
			return BillingSystemResponseBuilder.responseBuilder(
					BillingSystemConstants.UNEXPECTED_ERROR + ": " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
					null);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateCustomer(@PathVariable("id") Integer id, @Valid @RequestBody Customer customer,
			BindingResult result) {
		ResponseEntity<Object> validationResponse = validationUtil.validateRequest(result);
		if (validationResponse != null) {
			return validationResponse;
		}

		try {
			customer.setCustomerId(id);
			Customer updatedCustomer = customerService.updateCustomer(customer);
			return BillingSystemResponseBuilder.responseBuilder("Customer updated successfully", HttpStatus.OK,
					updatedCustomer);
		} catch (BillingSystemNotFoundException ex) {
			return BillingSystemResponseBuilder.responseBuilder(ex.getMessage(), HttpStatus.NOT_FOUND, null);
		} catch (BillingSystemInternalException ex) {
			return BillingSystemResponseBuilder.responseBuilder(
					BillingSystemConstants.INTERNAL_ERROR + ": " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
					null);
		} catch (Exception ex) {
			return BillingSystemResponseBuilder.responseBuilder(BillingSystemConstants.TECHNICAL_ISSUE_MESSAGE,
					HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteCustomer(@PathVariable Integer id, @RequestBody @Valid Customer customer) {
		try {
			customer.setCustomerId(id);
			customerService.deleteCustomer(customer);
			return BillingSystemResponseBuilder.responseBuilder("Customer Deleted successfully", HttpStatus.OK, customer // returning
																															// the
																															// deleted
																															// Customer
			);
		} catch (BillingSystemNotFoundException ex) {
			return BillingSystemResponseBuilder.responseBuilder(ex.getMessage(), HttpStatus.NOT_FOUND, null);
		} catch (BillingSystemInternalException ex) {
			return BillingSystemResponseBuilder.responseBuilder(
					BillingSystemConstants.INTERNAL_ERROR + ": " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
					null);
		} catch (Exception ex) {
			return BillingSystemResponseBuilder.responseBuilder(BillingSystemConstants.TECHNICAL_ISSUE_MESSAGE,
					HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	@PostMapping("/search")
	public ResponseEntity<Object> searchCustomerByName(@RequestBody Customer customer) {
		try {
			List<Customer> foundCustomers = customerService.searchCustomerByName(customer);

			return BillingSystemResponseBuilder.responseBuilder("Customers found successfully", HttpStatus.OK,
					foundCustomers // return list here
			);
		} catch (BillingSystemNotFoundException ex) {
			return BillingSystemResponseBuilder.responseBuilder(ex.getMessage(), HttpStatus.NOT_FOUND, null);
		} catch (BillingSystemInternalException ex) {
			return BillingSystemResponseBuilder.responseBuilder(
					BillingSystemConstants.INTERNAL_ERROR + ": " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
					null);
		} catch (Exception ex) {
			return BillingSystemResponseBuilder.responseBuilder(
					BillingSystemConstants.UNEXPECTED_ERROR + ": " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
					null);
		}
	}

}
