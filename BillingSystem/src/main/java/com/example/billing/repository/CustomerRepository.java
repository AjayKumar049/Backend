package com.example.billing.repository;


import org.slf4j.Logger;



import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.billing.exception.BillingSystemInternalException;
import com.example.billing.model.Customer;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepository {

    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(CustomerRepository.class);

    public CustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
  //RowMapper for converting ResultSet into Item object
    private final RowMapper<Customer> customerRowMapper = (rs, rowNum) -> {
       Customer customer = new Customer();
        customer.setCustomerId(rs.getInt("CustomerId"));
        customer.setCustomerNumber(rs.getString("CustomerNumber"));
        customer.setFirstName(rs.getString("FirstName"));
        customer.setLastName(rs.getString("LastName"));
        customer.setCompany(rs.getString("Company"));
        customer.setEmail(rs.getString("Email"));
        customer.setGstNumber(rs.getString("GstNumber"));
        customer.setPhoneNumber(rs.getString("PhoneNumber"));
       
        
        return customer;
    };

   
    public int save(Customer customer) {
        String sql = "INSERT INTO customer (customer_number, first_name, last_name, company_name, email, gstin, phone) values (?,?,?,?,?,?,?)";
        try {
            return jdbcTemplate.update(sql,
                    customer.getCustomerNumber(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getCompany(),
                    customer.getEmail(),
                    customer.getGstNumber(),
                    customer.getPhoneNumber());
                    
                   
        } catch (DataAccessException e) {
            logger.error("Error saving customer: {}", e.getMessage());
            return 0;
        }
    }
    
    public int getCustomerCount() {
        String sql = "SELECT COUNT(*) FROM customer";
        try {
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
            return (count != null) ? count : 0;
        } catch (DataAccessException e) {
            throw new BillingSystemInternalException("Failed to count customers: " + e.getMessage());
        }
    }

               

    public String getLastCustomerNumber() {
        String sql = "SELECT customer_number FROM customers ORDER BY customer_id DESC LIMIT 1";
        try {
            return jdbcTemplate.queryForObject(sql, String.class);
        } catch (DataAccessException e) {
            logger.warn("No previous customer number found.");
            return null;
        }
    }

    public List<Customer> findAll() {
        String sql = "SELECT * FROM customer";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Customer customer = new Customer();
            customer.setCustomerId(rs.getInt("customer_id"));
            customer.setCustomerNumber(rs.getString("customer_number"));
            customer.setFirstName(rs.getString("firstName"));
            customer.setLastName(rs.getString("lastName"));
            customer.setCompany(rs.getString("company"));
            customer.setEmail(rs.getString("email"));
            customer.setGstNumber(rs.getString("gstNumber"));
            customer.setPhoneNumber(rs.getString("phoneNumber"));
            
            return customer;
        });
    }
    
 // EXISTS BY Email
    public boolean existsByEmail(String name) {
        try {
            Integer count = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM customer WHERE email = ?", Integer.class, name);
            return count != null && count > 0;
        } catch (DataAccessException e) {
        	throw new BillingSystemInternalException("Error accessing DB while checking HSN code existence: " + e.getMessage());
            
        }
    }
    
 // EXISTS BY GSTNumber
    public boolean existsByGstNumber(String name) {
        try {
            Integer count = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM customer WHERE gstin = ?", Integer.class, name);
            return count != null && count > 0;
        } catch (DataAccessException e) {
        	throw new BillingSystemInternalException("Error accessing DB while checking gstnumber existence: " + e.getMessage());
            
        }
    }
    
    public int update(Customer customer) {
        String sql = "UPDATE customers SET firstname=?, lastname=?, company=?, email=?, gstnumber=?, phonenumber=?, shippingaddress=?, attention=?, city=?, pincode=?, district=?, state=?, country=? WHERE customerid=?";

        try {
            return jdbcTemplate.update(sql,
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getCompany(),
                    customer.getEmail(),
                    customer.getGstNumber(),
                    customer.getPhoneNumber(),
                   
                    customer.getCustomerId()); // Customer ID is the last parameter
        } catch (DataAccessException e) {
            System.err.println("Error updating item: " + e.getMessage());
            return 0;
        }
    }

    // EXISTS BY ID
    public boolean existsById(int customerId) {
        try {
            Integer count = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM customer WHERE customerid = ?",
                    Integer.class,
                    customerId);
            return count != null && count > 0;
        } catch (DataAccessException e) {
            throw new BillingSystemInternalException("Error accessing DB while checking customer existence: " + e.getMessage());
        }
    }
    
    // DELETE
    public int delete(Customer customer) {
        String sql = "DELETE FROM customers WHERE customerid=?";
        try {
            return jdbcTemplate.update(sql, customer.getCustomerId());
        } catch (DataAccessException e) {
            System.err.println("Error deleting item: " + e.getMessage());
            return 0;
        }
    }
    
    public List<Customer> findByFirstName(String firstname) {
        List<Customer> customerList = new ArrayList<>();
        try {
            if (firstname == null || firstname.trim().isEmpty()) {
                return customerList; // Return empty list if input is invalid
            }

            String sql = "SELECT * FROM customer WHERE LOWER(firstname) = LOWER(?)";
            customerList = jdbcTemplate.query(sql, customerRowMapper, firstname.trim());

            // Debugging
            System.out.println("Number of customers found: " + customerList.size());
            for (Customer customer : customerList) {
                System.out.println("Customer Name: " + customer.getFirstName());
            }

        } catch (DataAccessException e) {
            System.out.println("Error while fetching customers by firstname: " + e.getMessage());
        }

        return customerList;
    }


    
    
}
