package com.example.foodtracker;

import java.io.Serializable;

public class FoodItem implements Serializable {
    private int id;  // Unique ID for the food item
    private String name;
    private String expiryDate;
    private int daysLeft;
    private String category;

    // Main constructor with all parameters including ID
    public FoodItem(int id, String name, String expiryDate, int daysLeft, String category) {
        this.id = id;
        this.name = name;
        this.expiryDate = expiryDate;
        this.daysLeft = daysLeft;
        this.category = category;
    }

    // Overloaded constructor without ID (for creating new items)
    public FoodItem(String name, String expiryDate, int daysLeft, String category) {
        this(-1, name, expiryDate, daysLeft, category);
    }

    // Overloaded constructor with default category
    public FoodItem(String name, String expiryDate, int daysLeft) {
        this(-1, name, expiryDate, daysLeft, "General");
    }

    // Overloaded constructor without daysLeft (you can calculate later)
    public FoodItem(String name, String expiryDate) {
        this(-1, name, expiryDate, 0, "General");
    }

    // Getter and Setter for ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public String getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
