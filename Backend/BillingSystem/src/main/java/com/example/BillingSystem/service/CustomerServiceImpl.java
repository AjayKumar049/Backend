package com.example.BillingSystem.service;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.BillingSystem.exception.BillingSystemAlreadyExist;
import com.example.BillingSystem.exception.BillingSystemInternalException;
import com.example.BillingSystem.model.Customer;
import com.example.BillingSystem.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer addCustomer(Customer customer) {
        try {
            // Check if the email already exists
            if (customerRepository.existsByEmail(customer.getEmail())) {
                throw new BillingSystemAlreadyExist("Email already exists");
            }
            //Check if the Gstnumber already exists
            if (customerRepository.existsByGstNumber(customer.getGstNumber())) {
                throw new BillingSystemAlreadyExist("GSTNumber already exists");
            }

            // Save the customer to the database
            int save = customerRepository.save(customer);
            if (save == 0) {
                throw new BillingSystemInternalException("Failed to save customer due to internal DB error");
            }

            return customer;

        } catch (DataAccessException e) {
            throw new BillingSystemInternalException("Database error while saving customer: " + e.getMessage());
        }
    }
}
