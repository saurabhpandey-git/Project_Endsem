package com.iiitd.mc.travelguideapplication;

public class ExpenseListItem {
    private String tripName;
    private float amount;

    public ExpenseListItem(String tripName, float amount) {
        this.tripName = tripName;
        this.amount = amount;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ExpenseListItem{" +
                "tripName='" + tripName + '\'' +
                ", amount=" + amount +
                '}';
    }
}
