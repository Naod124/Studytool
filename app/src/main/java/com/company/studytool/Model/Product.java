package com.company.studytool.Model;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Product implements Serializable {

    @Exclude
    String key;

    String title;
    String description;
    String img;
    String username;
    String email;
    String phone;
    Boolean isSold = false;

    public Product() {
    }

    public Product(String title, String description, String img, String username, String email, String phone, Boolean isSold) {
        this.title = title;
        this.description = description;
        this.img = img;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.isSold = isSold;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getIsSold() {
        return isSold;
    }

    public void setIsSold(Boolean sold) {
        isSold = sold;
    }
}
