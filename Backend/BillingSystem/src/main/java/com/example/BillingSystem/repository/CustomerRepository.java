package com.example.BillingSystem.repository;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.example.BillingSystem.exception.BillingSystemInternalException;
import com.example.BillingSystem.model.Customer;
@Repository
public class CustomerRepository {
	
	//Dependency Injection
    private final JdbcTemplate jdbcTemplate;

    public CustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
  //RowMapper for converting ResultSet into Item object
    private final RowMapper<Customer> customerRowMapper = (rs, rowNum) -> {
       Customer customer = new Customer();
        customer.setCustomerId(rs.getInt("CustomerId"));
        customer.setFirstName(rs.getString("FirstName"));
        customer.setLastName(rs.getString("LastName"));
        customer.setCompany(rs.getString("Company"));
        customer.setEmail(rs.getString("Email"));
        customer.setGstNumber(rs.getString("GstNumber"));
        customer.setPhoneNumber(rs.getString("PhoneNumber"));
        customer.setShippingAddress(rs.getString("ShippingAddress"));
        customer.setAttention(rs.getString("Attention"));
        customer.setCity(rs.getString("city"));
        customer.setPincode(rs.getString("Pincode"));
        customer.setDistrict(rs.getString("District"));
        customer.setState(rs.getString("state"));
        customer.setCountry(rs.getString("Country"));
        
        return customer;
    };
    
    //Create, Read, Update, Delete, and other operation are mentioned below
    // CREATE
    public int save(Customer customer) {
        String sql = "INSERT INTO customers (firstName, lastName, company, email, gstNumber, phoneNumber, shippingAddress, attention, city, pincode, district, state, country) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            return jdbcTemplate.update(sql,
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getCompany(),
                    customer.getEmail(),
                    customer.getGstNumber(),
                    customer.getPhoneNumber(),
                    customer.getShippingAddress(),
                    customer.getAttention(),
                    customer.getCity(),
                    customer.getPincode(),
                    customer.getDistrict(),
                    customer.getState(),
                    customer.getCountry());
                    
            
        } catch (DataAccessException e) {
            System.err.println("Error saving customers: " + e.getMessage());
            return 0;
        }
    }
    
 // EXISTS BY Email
    public boolean existsByEmail(String name) {
        try {
            Integer count = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM customers WHERE email = ?", Integer.class, name);
            return count != null && count > 0;
        } catch (DataAccessException e) {
        	throw new BillingSystemInternalException("Error accessing DB while checking email existence: " + e.getMessage());
            
        }
    }

	 
// EXISTS BY GSTNumber
    public boolean existsByGstNumber(String name) {
        try {
            Integer count = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM customers WHERE gstnumber = ?", Integer.class, name);
            return count != null && count > 0;
        } catch (DataAccessException e) {
        	throw new BillingSystemInternalException("Error accessing DB while checking gstnumber existence: " + e.getMessage());
            
        }
    }


     //READ
    public List<Customer> findAll() {
        String sql = "SELECT * FROM customers";
        try {
            return jdbcTemplate.query(sql, customerRowMapper);
        } catch (DataAccessException e) {
            System.err.println("Error fetching customers: " + e.getMessage());
            return Collections.emptyList();
        }
    }

//Update
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
                    customer.getShippingAddress(),
                    customer.getAttention(),
                    customer.getCity(),
                    customer.getPincode(),
                    customer.getDistrict(),
                    customer.getState(),
                    customer.getCountry(),
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
                    "SELECT COUNT(*) FROM customers WHERE customerid = ?",
                    Integer.class,
                    customerId);
            return count != null && count > 0;
        } catch (DataAccessException e) {
            throw new BillingSystemInternalException("Error accessing DB while checking customer existence: " + e.getMessage());
        }
    }

    
    
	

    

    
    
	

}
