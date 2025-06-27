package edu.psu.sweng888.practiceiii.model;

import android.os.Parcel;

public class Product {
    private int id;
    private String name;
    private String description;
    private String seller;
    private double price;
    private String picture; // URL or file path to the image

    // Constructor
    public Product(int id, String name, String description, String seller, double price, String picture) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.seller = seller;
        this.price = price;
        this.picture = picture;
    }

    // Parcel

    protected Product(Parcel in){
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        seller = in.readString();
        price = in.readDouble();
        picture = in.readString();
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    // toString method
    @Override
    public String toString() {
        return "Product {" +
                "ID=" + id +
                ", Name='" + name + '\'' +
                ", Description='" + description + '\'' +
                ", Seller='" + seller + '\'' +
                ", Price=$" + price +
                ", Picture='" + picture + '\'' +
                '}';
    }
}

