package com.iiitd.mc.travelguideapplication;

import java.time.LocalDateTime;

public class Notification {
    private String text;
    private LocalDateTime timeStamp;

    public Notification(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
