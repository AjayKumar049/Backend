package com.example.billing.utility;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class EstimateNumberGenerator {
	
	public String generate(int lastId) {
        return String.format("EST-%d%04d", LocalDate.now().getYear(), lastId + 1);
    }

}
