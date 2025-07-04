package edu.psu.sweng888.practiceiii.model;

import android.os.Parcel;

import java.util.UUID;

public class Product {
    private UUID id;
    private String name;
    private String description;
    private String seller;
    private double price;
    private String picture; // URL or file path to the image

    // Empty Constructor
    public Product() {
        this.id = UUID.randomUUID();;
    }

    // Constructor
    public Product(int id, String name, String description, String seller, double price, String picture) {
        this.id = UUID.randomUUID();;
        this.name = name;
        this.description = description;
        this.seller = seller;
        this.price = price;
        this.picture = picture;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}

