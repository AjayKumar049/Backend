package com.example.billing.dto;

import java.time.LocalDate;

public class ForgotPasswordDto {
    
    private Long forgotPasswordId;  
    private String email;
    private LocalDate requestedDate;

    public ForgotPasswordDto() {}

    public Long getForgotPasswordId() {
        return forgotPasswordId;
    }

    public void setForgotPasswordId(Long forgotPasswordId) {
        this.forgotPasswordId = forgotPasswordId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(LocalDate requestedDate) {
        this.requestedDate = requestedDate;
    }
}
