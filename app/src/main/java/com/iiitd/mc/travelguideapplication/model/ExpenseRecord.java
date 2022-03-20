package com.iiitd.mc.travelguideapplication.model;

public class ExpenseRecord {
    private User user;
    private String purpose;
    private String comment;
    private float amount;

    public ExpenseRecord(User user, String purpose, String comment, float amount) {
        this.user = user;
        this.purpose = purpose;
        this.comment = comment;
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
