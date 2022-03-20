package com.iiitd.mc.travelguideapplication;

import java.util.List;

public class Trip {
    private String name;
    private String id;
    private String startDate;
    private ChatGroup chat;
    private Expenses expenses;
    private List<Image> images;
    private List<Place> route;

    public Trip(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public ChatGroup getChat() {
        return chat;
    }

    public void setChat(ChatGroup chat) {
        this.chat = chat;
    }

    public Expenses getExpenses() {
        return expenses;
    }

    public void setExpenses(Expenses expenses) {
        this.expenses = expenses;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Place> getRoute() {
        return route;
    }

    public void setRoute(List<Place> route) {
        this.route = route;
    }
}
