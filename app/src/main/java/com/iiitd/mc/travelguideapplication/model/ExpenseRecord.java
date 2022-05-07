package com.iiitd.mc.travelguideapplication.model;

public class ExpenseRecord {
    private int userID;
    private String purpose;
    private String comment;
    private float amount;

    public ExpenseRecord() {
    }

    public ExpenseRecord(int userID, String purpose, String comment, float amount) {
        this.userID = userID;
        this.purpose = purpose;
        this.comment = comment;
        this.amount = amount;
    }

    public int getUser() {
        return userID;
    }

    public void setUser(User user) {
        this.userID = userID;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
