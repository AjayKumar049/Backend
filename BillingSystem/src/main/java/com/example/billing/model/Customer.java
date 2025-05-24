package com.example.billing.model;

import jakarta.validation.constraints.NotNull;

public class Customer {
    
    @NotNull(message="CustomerId is required")
    private Integer customerId;

    @NotNull(message = "Type is required")
    private String type;           // Business or Individual

    @NotNull(message = "Salutation is required")
    private String salutation;

    @NotNull(message = "First Name is required")
    private String firstName;

    @NotNull(message = "Last Name is required")
    private String lastName;

    @NotNull(message = "Company Name is required")
    private String companyName;

    @NotNull(message = "Email is required")
    private String email;

    @NotNull(message = "GSTIN is required")
    private String gstin;

    @NotNull(message = "Phone is required")
    private String phone;

    @NotNull(message = "Attention is required")
    private String attention;

    @NotNull(message = "Shipping Address is required")
    private String shippingAddress;

    @NotNull(message = "City is required")
    private String city;

    @NotNull(message = "District is required")
    private String district;

    @NotNull(message = "State is required")
    private String state;

    @NotNull(message = "Country is required")
    private String country;
    
    public Customer() {
        
    }


    private Customer(Builder builder) {
        this.customerId = builder.customerId;
        this.type = builder.type;
        this.salutation = builder.salutation;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.companyName = builder.companyName;
        this.email = builder.email;
        this.gstin = builder.gstin;
        this.phone = builder.phone;
        this.attention = builder.attention;
        this.shippingAddress = builder.shippingAddress;
        this.city = builder.city;
        this.district = builder.district;
        this.state = builder.state;
        this.country = builder.country;
    }

    
    public Integer getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
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


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getGstin() {
		return gstin;
	}


	public void setGstin(String gstin) {
		this.gstin = gstin;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAttention() {
		return attention;
	}


	public void setAttention(String attention) {
		this.attention = attention;
	}


	public String getShippingAddress() {
		return shippingAddress;
	}


	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getDistrict() {
		return district;
	}


	public void setDistrict(String district) {
		this.district = district;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public static class Builder {
        private Integer customerId;
        private String type;
        private String salutation;
        private String firstName;
        private String lastName;
        private String companyName;
        private String email;
        private String gstin;
        private String phone;
        private String attention;
        private String shippingAddress;
        private String city;
        private String district;
        private String state;
        private String country;

        public Builder customerId(Integer customerId) {
            this.customerId = customerId;
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

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder gstin(String gstin) {
            this.gstin = gstin;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder attention(String attention) {
            this.attention = attention;
            return this;
        }

        public Builder shippingAddress(String shippingAddress) {
            this.shippingAddress = shippingAddress;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
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
