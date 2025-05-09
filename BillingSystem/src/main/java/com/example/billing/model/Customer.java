package com.example.billing.model;

public class Customer {
    private int customerId;
    private String customerNumber;
    private String firstName;
    private String lastName;
    private String company;
    private String email;
    private String gstNumber;
    private String phoneNumber;
    
    public Customer() {}
    
    
    

    public Customer(int customerId, String customerNumber, String firstName, String lastName, String company,
			String email, String gstNumber, String phoneNumber) {
		super();
		this.customerId = customerId;
		this.customerNumber = customerNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.company = company;
		this.email = email;
		this.gstNumber = gstNumber;
		this.phoneNumber = phoneNumber;
	}




	// Getters and Setters

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}  
