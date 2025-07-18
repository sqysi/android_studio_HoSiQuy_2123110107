package com.example.hitcapp;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    RecyclerView cartRecyclerView;
    TextView totalPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartRecyclerView  = findViewById(R.id.cartListView);
        totalPrice = findViewById(R.id.totalPrice);

        List<Product> cartItems = CartManager.getCart();
        CartAdapter adapter = new CartAdapter(this, cartItems);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(adapter);

        double total = 0;
        for (Product p : cartItems) {
            total += Double.parseDouble(p.getPrice().replace("$", ""));
        }

        totalPrice.setText("Tổng: $" + total);
    }
}
