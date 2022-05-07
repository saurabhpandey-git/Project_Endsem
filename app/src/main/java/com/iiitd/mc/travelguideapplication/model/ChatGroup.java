package com.iiitd.mc.travelguideapplication.model;

import java.util.List;

public class ChatGroup {
    private List<Integer> coTravellersUserIDs;
    private List<Message> chatHistory;

    public ChatGroup(List<Integer> coTravellersUserIDs, List<Message> chatHistory) {
        this.coTravellersUserIDs = coTravellersUserIDs;
        this.chatHistory = chatHistory;
    }

    public List<Integer> getCoTravellers() {
        return coTravellersUserIDs;
    }

    public void setCoTravellers(List<Integer> coTravellersUserIDs) {
        this.coTravellersUserIDs = coTravellersUserIDs;
    }

    public List<Message> getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(List<Message> chatHistory) {
        this.chatHistory = chatHistory;
    }
}
