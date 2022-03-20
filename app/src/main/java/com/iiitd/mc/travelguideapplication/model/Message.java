package com.iiitd.mc.travelguideapplication.model;

import java.time.LocalDateTime;

public class Message {
    private String message;
    private User user;
    private LocalDateTime timeStamp;

    public Message(String message, User user, LocalDateTime timeStamp) {
        this.message = message;
        this.user = user;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
