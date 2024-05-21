package com.example.mychat.models;

import com.google.firebase.Timestamp;

public class ChatMessage {
    private String message;
    private String senderId;
    private Timestamp timestamp;

    private String type;
    public ChatMessage() {
    }

    public ChatMessage(String message, String senderId, Timestamp timestamp, String type) {
        this.message = message;
        this.senderId = senderId;
        this.timestamp = timestamp;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
