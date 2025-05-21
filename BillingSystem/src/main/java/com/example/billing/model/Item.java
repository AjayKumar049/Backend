package com.example.billing.model;
import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Item {

	@NotNull
	private Long itemId;

	@NotNull(message = "Item name is required")
	private String name;

	@NotNull(message = "Manufacturer is required")
	private String manufacturer;
	
	@Size(max = 6)
	@NotNull(message = "HSN code is required")
	private String hsn;

	@NotNull(message = "Stock quantity is required")
	private Integer stock;

	@NotNull(message = "GST value is required")
	private Double gst;

	@NotNull(message = "Tax type is required")
	private String tax;

	@NotNull(message = "Discount value is required")
	private Double discount;

	@NotNull(message = "Selling price is required")
	private Double sellingPrice;

	@NotNull(message = "Expiry date is required")
	private LocalDate expiryDate;

	@NotNull(message = "Medicine type is required")
	private String medicineType;

	@NotNull(message = "Dosage is required")
	private String dosage;

	// Default constructor
	public Item() {
	}

	// Private constructor for Builder
	private Item(Builder builder) {
		this.itemId = builder.itemId;
		this.name = builder.name;
		this.manufacturer = builder.manufacturer;
		this.hsn = builder.hsn;
		this.stock = builder.stock;
		this.gst = builder.gst;
		this.tax = builder.tax;
		this.discount = builder.discount;
		this.sellingPrice = builder.sellingPrice;
		this.expiryDate = builder.expiryDate;
		this.medicineType = builder.medicineType;
		this.dosage = builder.dosage;
	}

	// Getters and Setters
	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long id) {
		this.itemId = id;
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

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Double getGst() {
		return gst;
	}

	public void setGst(Double gst) {
		this.gst = gst;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getMedicineType() {
		return medicineType;
	}

	public void setMedicineType(String medicineType) {
		this.medicineType = medicineType;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	// Static Builder class
	public static class Builder {
		private Long itemId;
		private String name;
		private String manufacturer;
		private String hsn;
		private Integer stock;
		private Double gst;
		private String tax;
		private Double discount;
		private Double sellingPrice;
		private LocalDate expiryDate;
		private String medicineType;
		private String dosage;

		public Builder itemId(Long itemId) {
			this.itemId = itemId;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder manufacturer(String manufacturer) {
			this.manufacturer = manufacturer;
			return this;
		}

		public Builder hsn(String hsn) {
			this.hsn = hsn;
			return this;
		}

		public Builder stock(Integer stock) {
			this.stock = stock;
			return this;
		}

		public Builder gst(Double gst) {
			this.gst = gst;
			return this;
		}

		public Builder tax(String tax) {
			this.tax = tax;
			return this;
		}

		public Builder discount(Double discount) {
			this.discount = discount;
			return this;
		}

		public Builder sellingPrice(Double sellingPrice) {
			this.sellingPrice = sellingPrice;
			return this;
		}

		public Builder expiryDate(LocalDate expiryDate) {
			this.expiryDate = expiryDate;
			return this;
		}

		public Builder medicineType(String medicineType) {
			this.medicineType = medicineType;
			return this;
		}

		public Builder dosage(String dosage) {
			this.dosage = dosage;
			return this;
		}

		public Item build() {
			return new Item(this);
		}
	}
}
