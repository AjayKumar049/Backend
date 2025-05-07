package com.example.billing.repository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.billing.model.Estimate;

@Repository
public class EstimateRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Get the last estimate ID
    public int getLastEstimateId() {
        String sql = "SELECT COALESCE(MAX(estimate_id), 0) FROM estimate";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    // Save a new estimate
    public void saveEstimate(Estimate estimate) {
        String sql = "INSERT INTO estimate (estimate_number, customer_id, estimate_date, expiry_date) " +
                     "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                estimate.getEstimateNumber(),
                estimate.getCustomerId(),
                estimate.getEstimateDate(),
                estimate.getExpiryDate());
    }
}
