package com.iiitd.mc.travelguideapplication.model;

import java.util.List;

public class Expenses {
    private List<ExpenseRecord> records;
    private static int idGenerator;
    private int id;

    public Expenses() {
        this.id = idGenerator++;
    }

    public Expenses(List<ExpenseRecord> records) {
        this.records = records;
        this.id = idGenerator++;
    }

    public List<ExpenseRecord> getRecords() {
        return records;
    }

    public void setRecords(List<ExpenseRecord> records) {
        this.records = records;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}