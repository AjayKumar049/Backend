package com.example.BillingSystem.service;

import com.example.BillingSystem.model.Customer;

public interface CustomerService {
	
	Customer addCustomer(Customer customer);
	List<Customer> getAllCustomers();

}
