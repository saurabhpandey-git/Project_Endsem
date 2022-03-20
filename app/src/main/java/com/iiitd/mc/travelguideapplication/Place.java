package com.iiitd.mc.travelguideapplication;

import java.time.LocalDateTime;
import java.util.List;

public class Place {
    private Location location;
    private LocalDateTime durationOfStay;
    private List<String> itemsToPackRecommendationBasedOnLocationAndTime;
    private String city;
    private String state;
    private String country;

    public Place(Location location, String city, String state, String country) {
        this.location = location;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocalDateTime getDurationOfStay() {
        return durationOfStay;
    }

    public void setDurationOfStay(LocalDateTime durationOfStay) {
        this.durationOfStay = durationOfStay;
    }

    public List<String> getItemsToPackRecommendationBasedOnLocationAndTime() {
        return itemsToPackRecommendationBasedOnLocationAndTime;
    }

    public void setItemsToPackRecommendationBasedOnLocationAndTime(List<String> itemsToPackRecommendationBasedOnLocationAndTime) {
        this.itemsToPackRecommendationBasedOnLocationAndTime = itemsToPackRecommendationBasedOnLocationAndTime;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
