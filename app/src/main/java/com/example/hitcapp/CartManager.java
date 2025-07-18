package com.example.hitcapp;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static final List<Product> cart = new ArrayList<>();

    public static void addProduct(Product p) {
        cart.add(p);
    }

    public static List<Product> getCart() {
        return cart;
    }
}
