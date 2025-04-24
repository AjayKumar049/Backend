package com.example.BillingSystem.model;
import java.sql.Timestamp;

public class User {
    private int id;
    private String userName;
    private String email;
    private String password;
    private Timestamp created_at;

    
    public User() {}

    public User(int id, String userName, String email, String password, Timestamp created_at) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.created_at = created_at;
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

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}
