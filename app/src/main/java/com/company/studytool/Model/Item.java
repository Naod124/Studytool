package com.company.studytool.Model;

public class Item {
    private String name;
    private int quantity;
    private double price;
    private String url;
    private String description;

    public Item() {
    }

    public Item(String name, int quantity, double price, String url, String description) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.url = url;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}