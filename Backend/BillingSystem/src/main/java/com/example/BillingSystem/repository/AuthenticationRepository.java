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
		 user.setCreated_at(new Timestamp(System.currentTimeMillis()));
	        String sql = "INSERT INTO users (user_name, email, password, created_at) VALUES (?, ?, ?,?)";
	        try{
	        return jdbcTemplate.update(sql, 
	        user.getUserName(), 
	        user.getEmail(), 
	        user.getPassword(),
	        user.getCreated_at());
	        }
	        catch (DataAccessException e) {
	            System.err.println("Error while signup: " + e.getMessage());
	            return 0;
	        }
	    
	     }
	 
	// EXISTS BY Email
	    public boolean existsByEmail(String name) {
	        try {
	            Integer count = jdbcTemplate.queryForObject(
	                    "SELECT COUNT(*) FROM users WHERE email = ?", Integer.class, name);
	            return count != null && count > 0;
	        } catch (DataAccessException e) {
	        	throw new BillingSystemInternalException("Error accessing DB while checking email existence: " + e.getMessage());
	            
	        }
	    }
	
	

}
