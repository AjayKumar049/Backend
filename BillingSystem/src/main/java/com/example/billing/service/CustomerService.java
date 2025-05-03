package com.example.billing.service;

import java.util.List;

import com.example.billing.model.Customer;
import com.example.billing.model.Item;


public interface CustomerService {
	
	Customer addCustomer(Customer customer);
	List<Customer> getAllCustomers();
	Customer UpdateCustomer(Customer customer);
	Customer DeleteCustomer(Customer customer);
	Customer searchCustomerByName(Customer customer);

}
