package com.example.BillingSystem.model;

import java.sql.RowId;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;


public class Item {
	@NotNull
    private int itemId;
    
	@NotNull(message = "Name should not be null")
    private String name;
	@NotNull
    private String manufacturer;
    
	@NotNull
    private String hsn;
    
	@NotNull
    private int stock;
    
	@NotNull
    private double gst;
    
	@NotNull
    private String tax;
    
	@NotNull
    private double discount;
    
	@NotNull
    private double sellingPrice;
    
	@NotNull
    private LocalDate expiryDate;
    
    
	
    
	public Item() {}


	public Item(int itemId, String name, String manufacturer, String hsn, int stock, double gst, String tax,
			double discount, double sellingPrice, LocalDate expiryDate) {
		super();
		this.itemId = itemId;
		this.name = name;
		this.manufacturer = manufacturer;
		this.hsn = hsn;
		this.stock = stock;
		this.gst = gst;
		this.tax = tax;
		this.discount = discount;
		this.sellingPrice = sellingPrice;
		
		this.expiryDate = expiryDate;
	}


	public int getItemId() {
		return itemId;
	}


	public void setItemId(int itemId) {
		this.itemId = itemId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getManufacturer() {
		return manufacturer;
	}


	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}


	public String getHsn() {
		return hsn;
	}


	public void setHsn(String hsn) {
		this.hsn = hsn;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	public double getGst() {
		return gst;
	}


	public void setGst(double gst) {
		this.gst = gst;
	}


	public String getTax() {
		return tax;
	}


	public void setTax(String tax) {
		this.tax = tax;
	}


	public double getDiscount() {
		return discount;
	}


	public void setDiscount(double discount) {
		this.discount = discount;
	}


	public double getSellingPrice() {
		return sellingPrice;
	}


	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}


	


	public LocalDate getExpiryDate() {
		return expiryDate;
	}


	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	
	
	

}


