package com.iiitd.mc.travelguideapplication.model;

import android.graphics.Bitmap;

import java.time.LocalDateTime;

public class Image {
    private String name;
    private String image;
    private int addedByUserID;
    private String timeStamp;
    private Location location;

    public Image(String name, String image, int addedByUserID, String timeStamp, Location location) {
        this.name = name;
        this.image = image;
        this.addedByUserID = addedByUserID;
        this.timeStamp = timeStamp;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getAddedBy() {
        return addedByUserID;
    }

    public void setAddedBy(int addedBy) {
        this.addedByUserID = addedBy;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
