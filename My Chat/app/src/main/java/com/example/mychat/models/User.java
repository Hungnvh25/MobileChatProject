package com.example.mychat.models;

import com.google.firebase.Timestamp;

public class User {

    private String phone;
    private String userName;
    private Timestamp createdTimestamp;

    public User() {
    }

    public User(String phone, String userName, Timestamp createdTimestamp) {
        this.phone = phone;
        this.userName = userName;
        this.createdTimestamp = createdTimestamp;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Timestamp getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Timestamp createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }
}
