package edu.psu.sweng888.practiceiii.model;

import java.io.Serializable;
import java.util.UUID;

public class Product implements Serializable {
    private UUID id;
    private String name;
    private String description;
    private String seller;
    private double price;
    private int picture; // URL or file path to the image

    // Empty Constructor
    public Product() {
        this.id = UUID.randomUUID();;
    }

    // Constructor
    public Product(String name, String description, String seller, double price, int picture) {
        this.id = UUID.randomUUID();;
        this.name = name;
        this.description = description;
        this.seller = seller;
        this.price = price;
        this.picture = picture;
    }

    public Product(UUID id, String name, String description, String seller, double price, int picture) {
        this.id = id;
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

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return id.equals(product.id);
    }
}

