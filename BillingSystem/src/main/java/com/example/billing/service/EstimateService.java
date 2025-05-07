package com.example.billing.service;

import org.springframework.stereotype.Service;

import com.example.billing.model.Estimate;

@Service
public interface EstimateService {
	void createEstimate(Estimate estimate);

}
