package com.example.billing.model;

import jakarta.validation.constraints.NotNull;

public class Customer {

    private int customerId;

    @NotNull(message = "FirstName is required")
    private String firstName;

    @NotNull(message = "LastName is required")
    private String lastName;

    @NotNull(message = "Company is required")
    private String company;

    @NotNull(message = "Email is required")
    private String email;

    @NotNull(message = "GST number is required")
    private String gstNumber;

    @NotNull(message = "Phonenumber is required")
    private String phoneNumber;
    
    @NotNull(message = "type is required")
    private String type;
    
    @NotNull(message = "Salutation is required")
    private String salutation;

    public Customer() {
    }

    private Customer(Builder builder) {
        this.customerId = builder.customerId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.company = builder.company;
        this.email = builder.email;
        this.gstNumber = builder.gstNumber;
        this.phoneNumber = builder.phoneNumber;
        this.type = builder.type;
        this.salutation = builder.salutation;
    }

    // Getters and Setters

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    // Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int customerId;
        private String firstName;
        private String lastName;
        private String company;
        private String email;
        private String gstNumber;
        private String phoneNumber;
        private String type;
        private String salutation;

        public Builder customerId(int customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder company(String company) {
            this.company = company;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder gstNumber(String gstNumber) {
            this.gstNumber = gstNumber;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder salutation(String salutation) {
            this.salutation = salutation;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
