package com.example.billing.model;

import java.time.LocalDate;

public class Estimate {
	
		private int estimateId;
	    private String estimateNumber;
	    private int customerId;
	    private LocalDate estimateDate;
	    private LocalDate expiryDate;
	    
	    public Estimate() {}
		public Estimate(int estimateId, String estimateNumber, int customerId, LocalDate estimateDate,
				LocalDate expiryDate) {
			super();
			this.estimateId = estimateId;
			this.estimateNumber = estimateNumber;
			this.customerId = customerId;
			this.estimateDate = estimateDate;
			this.expiryDate = expiryDate;
		}
		public int getEstimateId() {
			return estimateId;
		}
		public void setEstimateId(int estimateId) {
			this.estimateId = estimateId;
		}
		public String getEstimateNumber() {
			return estimateNumber;
		}
		public void setEstimateNumber(String estimateNumber) {
			this.estimateNumber = estimateNumber;
		}
		public int getCustomerId() {
			return customerId;
		}
		public void setCustomerId(int customerId) {
			this.customerId = customerId;
		}
		public LocalDate getEstimateDate() {
			return estimateDate;
		}
		public void setEstimateDate(LocalDate estimateDate) {
			this.estimateDate = estimateDate;
		}
		public LocalDate getExpiryDate() {
			return expiryDate;
		}
		public void setExpiryDate(LocalDate expiryDate) {
			this.expiryDate = expiryDate;
		}
	    
	    
	    
	    

}
