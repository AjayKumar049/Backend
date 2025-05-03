package com.example.billing.model;
import java.time.LocalDate;

public class User {
    private int id;
    private String userName;
    private String email;
    private String password;
    private LocalDate createdDate; // Using LocalDate instead of java.sql.Date
    
    public User() {}

    public User(int id, String userName, String email, String password, LocalDate createdDate) {
        super();
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
