package com.iiitd.mc.travelguideapplication;

import android.graphics.Bitmap;

import java.time.LocalDateTime;

public class Image {
    private String name;
    private Bitmap image;
    private User addedBy;
    private LocalDateTime timeStamp;
    private Location location;

    public Image(String name, Bitmap image, User addedBy, LocalDateTime timeStamp, Location location) {
        this.name = name;
        this.image = image;
        this.addedBy = addedBy;
        this.timeStamp = timeStamp;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
