package com.example.billing.repository;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.billing.exception.BillingSystemInternalException;
import com.example.billing.model.Customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class CustomerRepository {

    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(CustomerRepository.class);

    // Instantiate RowMapper to avoid null values
    private final CustomerRowMapper customerRowMapper = new CustomerRowMapper();

    public CustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper to map ResultSet to Customer object
    public class CustomerRowMapper implements RowMapper<Customer> {
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setCustomerId(rs.getInt("customer_id"));
            customer.setType(rs.getString("type"));
            customer.setSalutation(rs.getString("salutation"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));
            customer.setCompanyName(rs.getString("company_name"));
            customer.setEmail(rs.getString("email"));
            customer.setGstin(rs.getString("gstin"));
            customer.setPhone(rs.getString("phone"));
            customer.setShippingAddress(rs.getString("shipping_address"));
            customer.setAttention(rs.getString("attention"));
            customer.setCity(rs.getString("city"));
            customer.setDistrict(rs.getString("district"));
            customer.setState(rs.getString("state"));
            customer.setCountry(rs.getString("country"));
            return customer;
        }
    }

    // CREATE
    public int save(Customer customer) {
        String sql = "INSERT INTO customer (type, salutation, first_name, last_name, company_name, email, gstin, phone, shipping_address, attention, city, district, state, country) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            return jdbcTemplate.update(sql,
                    customer.getType(),
                    customer.getSalutation(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getCompanyName(),
                    customer.getEmail(),
                    customer.getGstin(),
                    customer.getPhone(),
                    customer.getShippingAddress(),
                    customer.getAttention(),
                    customer.getCity(),
                    customer.getDistrict(),
                    customer.getState(),
                    customer.getCountry());
        } catch (DataAccessException e) {
            logger.error("Error saving customer: {}", e.getMessage());
            return 0;
        }
    }

    // READ ALL
    public List<Customer> findAll() {
        String sql = "SELECT * FROM customer";
        try {
            return jdbcTemplate.query(sql, customerRowMapper);
        } catch (DataAccessException e) {
            logger.error("Error while fetching customers: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    // READ BY ID 
    public Customer findById(int customerId) {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{customerId}, customerRowMapper);
        } catch (DataAccessException e) {
            logger.error("Error fetching customer by ID: {}", e.getMessage());
            return null;
        }
    }

    // UPDATE
    public int update(Customer customer) {
        String sql = "UPDATE customer SET type=?, salutation=?, first_name=?, last_name=?, company_name=?, email=?, gstin=?, phone=?, shipping_address=?, attention=?, city=?, district=?, state=?, country=? WHERE customer_id=?";
        try {
            return jdbcTemplate.update(sql,
                    customer.getType(),
                    customer.getSalutation(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getCompanyName(),
                    customer.getEmail(),
                    customer.getGstin(),
                    customer.getPhone(),
                    customer.getShippingAddress(),
                    customer.getAttention(),
                    customer.getCity(),
                    customer.getDistrict(),
                    customer.getState(),
                    customer.getCountry(),
                    customer.getCustomerId());
        } catch (DataAccessException e) {
            logger.error("Error while updating customer: {}", e.getMessage());
            return 0;
        }
    }

    // DELETE
    public int delete(Customer customer) {
        String sql = "DELETE FROM customer WHERE customer_id=?";
        try {
            return jdbcTemplate.update(sql, customer.getCustomerId());
        } catch (DataAccessException e) {
            logger.error("Error while deleting customer: {}", e.getMessage());
            return 0;
        }
    }

    // EXISTS BY EMAIL
    public boolean existsByEmail(String email) {
        try {
            Integer count = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM customer WHERE email = ?", Integer.class, email);
            return count != null && count > 0;
        } catch (DataAccessException e) {
            throw new BillingSystemInternalException("Error checking email existence: " + e.getMessage());
        }
    }

    // EXISTS BY GSTIN
    public boolean existsByGstNumber(String gstin) {
        try {
            Integer count = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM customer WHERE gstin = ?", Integer.class, gstin);
            return count != null && count > 0;
        } catch (DataAccessException e) {
            throw new BillingSystemInternalException("Error checking GSTIN existence: " + e.getMessage());
        }
    }

    // EXISTS BY ID
    public boolean existsById(int customerId) {
        try {
            Integer count = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM customer WHERE customer_id = ?", Integer.class, customerId);
            return count != null && count > 0;
        } catch (DataAccessException e) {
            throw new BillingSystemInternalException("Error checking ID existence: " + e.getMessage());
        }
    }

    // FIND BY FIRST NAME
    public List<Customer> findByFirstName(String firstname) {
        List<Customer> customerList = new ArrayList<>();
        try {
            if (firstname == null || firstname.trim().isEmpty()) {
                return customerList;
            }

            String sql = "SELECT * FROM customer WHERE LOWER(first_name) = LOWER(?)";
            customerList = jdbcTemplate.query(sql, customerRowMapper, firstname.trim());

            logger.info("Customers found: {}", customerList.size());
            for (Customer customer : customerList) {
                logger.info("Customer Name: {}", customer.getFirstName());
            }
        } catch (DataAccessException e) {
            logger.error("Error while fetching customers by first name: {}", e.getMessage());
        }
        return customerList;
    }
}
