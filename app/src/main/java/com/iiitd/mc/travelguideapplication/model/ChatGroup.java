package com.iiitd.mc.travelguideapplication.model;

import java.util.List;

public class ChatGroup {
    private List<User> coTravellers;
    private List<Message> chatHistory;

    public ChatGroup(List<User> coTravellers, List<Message> chatHistory) {
        this.coTravellers = coTravellers;
        this.chatHistory = chatHistory;
    }

    public List<User> getCoTravellers() {
        return coTravellers;
    }

    public void setCoTravellers(List<User> coTravellers) {
        this.coTravellers = coTravellers;
    }

    public List<Message> getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(List<Message> chatHistory) {
        this.chatHistory = chatHistory;
    }
}
