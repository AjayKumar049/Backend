package com.example.BillingSystem.model;
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

	public Customer(int customerId, @NotNull(message = "FirstName is required") String firstName,
			@NotNull(message = "LastName is required") String lastName,
			@NotNull(message = "Company is required") String company,
			@NotNull(message = "Email is required") String email,
			@NotNull(message = "GST number is required") String gstNumber,
			@NotNull(message = "Phonenumber is required") String phoneNumber,
			@NotNull(message = "ShippingAddress is required") String shippingAddress,
			@NotNull(message = "Attention is required") String attention,
			@NotNull(message = "City is required") String city,
			@NotNull(message = "Pincode is required") String pincode,
			@NotNull(message = "District is required") String district,
			@NotNull(message = "State is required") String state,
			@NotNull(message = "Country is required") String country) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.company = company;
		this.email = email;
		this.gstNumber = gstNumber;
		this.phoneNumber = phoneNumber;
		this.shippingAddress = shippingAddress;
		this.attention = attention;
		this.city = city;
		this.pincode = pincode;
		this.district = district;
		this.state = state;
		this.country = country;
	}

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

		