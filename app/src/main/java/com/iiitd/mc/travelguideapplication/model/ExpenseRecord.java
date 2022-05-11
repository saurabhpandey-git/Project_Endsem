package com.iiitd.mc.travelguideapplication.model;

public class ExpenseRecord {
    private String purpose;
    private String comment;
    private float amount;
    private String email;

    public ExpenseRecord() {
    }

    public ExpenseRecord(String purpose, String comment, float amount, String email) {
        this.email = email;
        this.purpose = purpose;
        this.comment = comment;
        this.amount = amount;
    }

    public String getUserID() {
        return email;
    }

    public void setUserID(User user) {
        this.email = email;
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
