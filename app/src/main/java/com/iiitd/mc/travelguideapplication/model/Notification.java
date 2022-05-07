package com.iiitd.mc.travelguideapplication.model;

import java.time.LocalDateTime;

public class Notification {
    private String text;
    private String timeStamp;

    public Notification(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
