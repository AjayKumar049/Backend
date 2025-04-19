package com.example.BillingSystem.service;

import com.example.BillingSystem.model.Customer;

public interface CustomerService {
	
	Customer addCustomer(Customer customer);
	List<Customer> getAllCustomers();
	Customer UpdateCustomer(Customer customer);
	Customer DeleteCustomer(Customer customer);
	Customer searchCustomerByName(Customer customer);
	
	
	

}
