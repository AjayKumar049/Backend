package com.example.BillingSystem.repository;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.BillingSystem.exception.BillingSystemInternalException;
import com.example.BillingSystem.model.User;

@Repository
public class AuthenticationRepository {
	
	 @Autowired
	 private JdbcTemplate jdbcTemplate;
	
	public int save(User user) {
		    // Set current date for createdDate (LocalDate)
		    user.setCreatedDate(LocalDate.now());  
		    
		    String sql = "INSERT INTO signup (username, email, password, created_date) VALUES (?, ?, ?, ?)";

		    try {
		        // Convert LocalDate to java.sql.Date before passing it to jdbcTemplate
		        return jdbcTemplate.update(sql, 
		            user.getUserName(), 
		            user.getEmail(), 
		            user.getPassword(),
		            Date.valueOf(user.getCreatedDate()));  // Convert LocalDate to java.sql.Date
		    } catch (DataAccessException e) {
		        System.err.println("Error while signup: " + e.getMessage());
		        return 0;
		    }
		}
	
	
	 
	// EXISTS BY Email
	    public boolean existsByEmail(String name) {
	        try {
	            Integer count = jdbcTemplate.queryForObject(
	                    "SELECT COUNT(*) FROM signup WHERE email = ?", Integer.class, name);
	            return count != null && count > 0;
	        } catch (DataAccessException e) {
	        	throw new BillingSystemInternalException("Error accessing DB while checking email existence: " + e.getMessage());
	            
	        }
	    }

	//Signin 
	public User findByEmailAndPassword(String email, String password) {
	        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
	        try {
	            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
	                User user = new User();
	                user.setId(rs.getInt("id"));
	                user.setUserName(rs.getString("user_name"));
	                user.setEmail(rs.getString("email"));
	                user.setPassword(rs.getString("password"));
	                return user;
	            }, email, password);
	        } catch (EmptyResultDataAccessException e) {
	            return null; // No matching user
	        } catch (DataAccessException e) {
	            throw new BillingSystemInternalException("Database error: " + e.getMessage());
	        }
	    
	    }
	
	
	

}
