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

    @NotNull(message = "ShippingAddress is required")
    private String shippingAddress;

    @NotNull(message ="Attention is required")
    private String attention;

    @NotNull(message = "City is required")
    private String city;

    @NotNull(message = "Pincode is required")
    private String pincode;

    @NotNull(message = "District is required")
    private String district;

    @NotNull(message = "State is required")
    private String state;

    @NotNull(message = "Country is required")
    private String country;

    public Customer() {}

    // Private constructor for Builder
    private Customer(Builder builder) {
        this.customerId = builder.customerId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.company = builder.company;
        this.email = builder.email;
        this.gstNumber = builder.gstNumber;
        this.phoneNumber = builder.phoneNumber;
        this.shippingAddress = builder.shippingAddress;
        this.attention = builder.attention;
        this.city = builder.city;
        this.pincode = builder.pincode;
        this.district = builder.district;
        this.state = builder.state;
        this.country = builder.country;
    }

    // Getters and Setters

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getGstNumber() { return gstNumber; }
    public void setGstNumber(String gstNumber) { this.gstNumber = gstNumber; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }

    public String getAttention() { return attention; }
    public void setAttention(String attention) { this.attention = attention; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    // Static builder method
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
        private String shippingAddress;
        private String attention;
        private String city;
        private String pincode;
        private String district;
        private String state;
        private String country;

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

        public Builder shippingAddress(String shippingAddress) {
            this.shippingAddress = shippingAddress;
            return this;
        }

        public Builder attention(String attention) {
            this.attention = attention;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder pincode(String pincode) {
            this.pincode = pincode;
            return this;
        }

        public Builder district(String district) {
            this.district = district;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
