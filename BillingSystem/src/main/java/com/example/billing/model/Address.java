package com.example.billing.model;

import jakarta.validation.constraints.NotNull;

public class Address {

	@NotNull(message = "ShippingAddress is required")
	private String shippingAddress;

	@NotNull(message = "Attention is required")
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

	public Address() {
	}

	public Address(String shippingAddress, String attention, String city, String pincode, String district, String state,
			String country) {
		this.shippingAddress = shippingAddress;
		this.attention = attention;
		this.city = city;
		this.pincode = pincode;
		this.district = district;
		this.state = state;
		this.country = country;
	}

	// Getters and Setters
	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getAttention() {
		return attention;
	}

	public void setAttention(String attention) {
		this.attention = attention;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
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
}
