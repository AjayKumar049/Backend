package com.example.BillingSystem.repository;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.example.BillingSystem.dto.SignupDto;
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

	 // findbyEmail
	    public SignupDto findByEmailAndPassword(String email, String password) {
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

	            // Save login details into signin table
	            jdbcTemplate.update(insertSql, signupDto.getId(), signupDto.getEmail(), signupDto.getPassword()); // Correct order of parameters

	            return signupDto;

	        } catch (EmptyResultDataAccessException e) {
	            // Return null if no user is found with the given email
	            return null;
	        } catch (DataAccessException e) {
	            throw new BillingSystemInternalException("Database error: " + e.getMessage());
	        }
	    }


}
