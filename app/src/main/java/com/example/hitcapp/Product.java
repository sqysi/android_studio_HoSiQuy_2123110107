package com.example.hitcapp;

public class Product {
    private String name;
    private String price;
    private String imageUrl;
    private String description;
    private double rate; // Thêm thuộc tính điểm đánh giá
    private double priceValue; // Giá trị số của giá, dùng để sort

    public Product(String name, String price, String imageUrl, String description, double rate, double priceValue) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.description = description;
        this.rate = rate;
        this.priceValue = priceValue;
    }

    public String getName() { return name; }
    public String getPrice() { return price; }
    public String getImageUrl() { return imageUrl; }
    public String getDescription() { return description; }
    public double getRate() { return rate; }
    public double getPriceValue() { return priceValue; }
}
