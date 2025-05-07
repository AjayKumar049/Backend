package com.example.billing.service;

import java.util.List;



import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.billing.exception.BillingSystemAlreadyExist;
import com.example.billing.exception.BillingSystemInternalException;
import com.example.billing.exception.BillingSystemNotFoundException;
import com.example.billing.model.Customer;

import com.example.billing.repository.CustomerRepository;

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
    
    @Override
    public List<Customer> getAllCustomers() {
        try {
            List<Customer> customers = customerRepository.findAll();
            if (customers.isEmpty()) {
                throw new BillingSystemInternalException("Failed to fetch items due to internal DB error");
            }
            return customers;
        } catch (DataAccessException e) {
            throw new BillingSystemInternalException("Database error while fetching customers: " + e.getMessage());
        }
    }
    
    @Override
    public Customer UpdateCustomer(Customer customer) {
        try {
            if (!customerRepository.existsById(customer.getCustomerId())) {
                throw new BillingSystemNotFoundException("Customer not exist");
            }

            int result = customerRepository.update(customer);
            if (result == 0) {
                throw new BillingSystemInternalException("Failed to update customer due to internal DB error");
            }

            return customer;
        } catch (DataAccessException e) {
            throw new BillingSystemInternalException("Database error while updating customer: " + e.getMessage());
        }
    }
    

    @Override
    public Customer DeleteCustomer(Customer customer) {
        try {
            if (!customerRepository.existsById(customer.getCustomerId())) {
                throw new BillingSystemNotFoundException("Customer does not exist");
            }

            int result = customerRepository.delete(customer);
            if (result == 0) {
                throw new BillingSystemInternalException("Failed to delete customer due to internal DB error");
            }

            return customer;
        } catch (DataAccessException e) {
            throw new BillingSystemInternalException("Database error while deleting customer: " + e.getMessage());
        }
    }
    
    @Override
    public Customer searchCustomerByName(Customer customer) {
        try {
            if (customer.getFirstName() == null || customer.getFirstName().trim().isEmpty()) {
                throw new BillingSystemNotFoundException("Customer name must not be empty");
            }

            List<Customer> customerList = customerRepository.findByFirstName(customer.getFirstName());

            if (customerList.isEmpty()) {
                throw new BillingSystemNotFoundException("No customer found with name: " + customer.getFirstName());
            }

            return customerList.get(0); // return the first matched item
        } catch (DataAccessException e) {
            throw new BillingSystemInternalException("Database error occurred while searching customer: " + e.getMessage());
        }
    }
}
