package com.example.billing.service;

import com.example.billing.model.Estimate;

import com.example.billing.repository.EstimateRepository;
import com.example.billing.utility.EstimateNumberGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstimateServiceImpl implements EstimateService {

    private final EstimateRepository estimateRepository;
    private final EstimateNumberGenerator estimateNumberGenerator;

    @Autowired
    public EstimateServiceImpl(EstimateRepository estimateRepository,
                               EstimateNumberGenerator estimateNumberGenerator) {
        this.estimateRepository = estimateRepository;
        this.estimateNumberGenerator = estimateNumberGenerator;
    }

    @Override
    public void createEstimate(Estimate estimate) {
        try {
            int lastId = estimateRepository.getLastEstimateId();
            String newEstimateNumber = estimateNumberGenerator.generate(lastId);
            estimate.setEstimateNumber(newEstimateNumber);

            estimateRepository.saveEstimate(estimate);
        } catch (Exception e) {
            
            System.err.println("Error creating estimate: " + e.getMessage());
            throw new RuntimeException("Failed to create estimate", e);
        }
    }
}
