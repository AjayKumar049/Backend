package com.example.billing;

import org.springframework.boot.SpringApplication;



import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class BillingSystemApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(BillingSystemApplication.class, args);
	}

}
