package com.example.billing.model;

public class EstimateItem {
	private int estimateItemId;
    private int estimatedId;
    private int itemId;
    private String name;
    private String tax;
    private double gst;
    private double discount;
    private int quantity;
    private double unitPrice;
    private double totalAmount; 
    
    public EstimateItem() {}

	public EstimateItem(int estimateItemId, int estimatedId, int itemId, String name, String tax, double gst,
			double discount, int quantity, double unitPrice, double totalAmount) {
		super();
		this.estimateItemId = estimateItemId;
		this.estimatedId = estimatedId;
		this.itemId = itemId;
		this.name = name;
		this.tax = tax;
		this.gst = gst;
		this.discount = discount;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.totalAmount = totalAmount;
	}

	public int getEstimateItemId() {
		return estimateItemId;
	}

	public void setEstimateItemId(int estimateItemId) {
		this.estimateItemId = estimateItemId;
	}

	public int getEstimatedId() {
		return estimatedId;
	}

	public void setEstimatedId(int estimatedId) {
		this.estimatedId = estimatedId;
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

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public double getGst() {
		return gst;
	}

	public void setGst(double gst) {
		this.gst = gst;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
    
}

	