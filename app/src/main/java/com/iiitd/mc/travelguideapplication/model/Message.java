package com.iiitd.mc.travelguideapplication.model;

import java.time.LocalDateTime;

public class Message {
    private String message;
    private int userID;
    private String timeStamp;

    public Message(String message, int userID, String timeStamp) {
        this.message = message;
        this.userID = userID;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUser() {
        return userID;
    }

    public void setUser(String user) {
        this.userID = userID;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
