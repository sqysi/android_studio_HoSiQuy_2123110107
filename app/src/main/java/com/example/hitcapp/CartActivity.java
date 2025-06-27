package com.example.hitcapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    RecyclerView cartRecyclerView;
    TextView emptyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        emptyText = findViewById(R.id.emptyText);

        List<Product> cart = CartManager.getCart();

        if (cart.isEmpty()) {
            emptyText.setVisibility(View.VISIBLE);
        } else {
            cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            ProductAdapter adapter = new ProductAdapter(cart);
            cartRecyclerView.setAdapter(adapter);
        }
    }
}
