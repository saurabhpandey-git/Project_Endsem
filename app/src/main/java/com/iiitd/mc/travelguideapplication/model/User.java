package com.iiitd.mc.travelguideapplication.model;

import java.util.List;

public class User {
    private static int idGenerator = 0;
    private int id;
    private String name;
    private int age;
    private String mobileNumber;
    private String email;
    private String profileImage;
    private Location lastSavedLocation;
    private Trip currentTripPlan;
    private List<Trip> travelHistory;
    private List<Notification> notifications;

    public User() {
        this.id = idGenerator++;
    }

    public User(String name, int age, String mobileNumber, String email) {
        this.name = name;
        this.age = age;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.id = idGenerator++;
    }

    public User(String name, int age, String mobileNumber, String email, String profileImage, Location lastSavedLocation, Trip currentTripPlan, List<Trip> travelHistory, List<Notification> notifications) {
        this.id = idGenerator++;
        this.name = name;
        this.age = age;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.profileImage = profileImage;
        this.lastSavedLocation = lastSavedLocation;
        this.currentTripPlan = currentTripPlan;
        this.travelHistory = travelHistory;
        this.notifications = notifications;
    }

//    public boolean addExpense(ExpenseRecord record){
//
//    }











    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public Location getLastSavedLocation() {
        return lastSavedLocation;
    }

    public void setLastSavedLocation(Location lastSavedLocation) {
        this.lastSavedLocation = lastSavedLocation;
    }

    public Trip getCurrentTripPlan() {
        return currentTripPlan;
    }

    public void setCurrentTripPlan(Trip currentTripPlan) {
        this.currentTripPlan = currentTripPlan;
    }

    public List<Trip> getTravelHistory() {
        return travelHistory;
    }

    public void setTravelHistory(List<Trip> travelHistory) {
        this.travelHistory = travelHistory;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
