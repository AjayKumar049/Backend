package com.example.billing.repository;
import java.sql.Date;



import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;

import com.example.billing.dto.SignupDto;
import com.example.billing.exception.BillingSystemInternalException;
import com.example.billing.model.User;

@Repository
public class AuthenticationRepository {
	
	 
	 private JdbcTemplate jdbcTemplate;
	 private static final Logger logger = LoggerFactory.getLogger(AuthenticationRepository.class);

	 
	
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
		    	logger.error("Error while signup: {}", e.getMessage());

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

	 // findbyEmail
	    public SignupDto findByEmail(String email) {
	        String selectSql = "SELECT * FROM signup WHERE email = ?";
	        String insertSql = "INSERT INTO signin (signup_id, email, password, login_time) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";

	        try {
	            // Fetch user by email
	            SignupDto signupDto = jdbcTemplate.queryForObject(selectSql, (rs, rowNum) -> {
	                SignupDto u = new SignupDto();
	                u.setId(rs.getLong("signup_id"));
	                u.setEmail(rs.getString("email"));
	                u.setPassword(rs.getString("password"));
	                return u;
	            }, email);

	            // Safeguard against null (even though unlikely if query succeeds)
	            if (signupDto != null) {
	                jdbcTemplate.update(insertSql, signupDto.getId(), signupDto.getEmail(), signupDto.getPassword());
	            }

	            return signupDto;

	        } catch (EmptyResultDataAccessException e) {
	            // Return null if no user is found with the given email
	            return null;
	        } catch (DataAccessException e) {
	            throw new BillingSystemInternalException("Database error");
	        }
	    }

}



        
	
	
	

