package com.iiitd.mc.travelguideapplication.model;

import java.util.List;

public class Trip {
    private String name;
    private static int idGenerator;
    private int id;
    private String startDate;
    private ChatGroup chat;
    private Expenses expenses;
    private List<Image> images;
    private List<Place> route;

    public Trip() {
    }

    public Trip(String name) {
        this.name = name;
        this.id = idGenerator++;
    }

    public Trip(String name, String startDate, ChatGroup chat, Expenses expenses, List<Image> images, List<Place> route) {
        this.name = name;
        this.id = idGenerator++;
        this.startDate = startDate;
        this.chat = chat;
        this.expenses = expenses;
        this.images = images;
        this.route = route;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
