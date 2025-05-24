package com.example.billing.service;

import java.util.List;

import com.example.billing.model.Customer;
import com.example.billing.model.Item;


public interface CustomerService {
	
	Customer addCustomer(Customer customer);
	List<Customer> getAllCustomers();
	Customer updateCustomer(Customer customer);
	Customer deleteCustomer(Customer customer);
	List<Customer> searchCustomerByName(Customer customer);

}
