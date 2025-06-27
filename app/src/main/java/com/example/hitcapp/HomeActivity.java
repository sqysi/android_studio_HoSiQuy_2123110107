package com.example.hitcapp;

import static com.example.hitcapp.R.id.productRecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    RecyclerView productRecyclerView;
    LinearLayout categoryContainer;
    Button viewCartBtn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        productRecyclerView = findViewById(R.id.productRecyclerView);
        categoryContainer = findViewById(R.id.categoryContainer);

        setupCategories();
        setupProducts();

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                return true;
            } else if (id == R.id.nav_cart) {
                startActivity(new Intent(HomeActivity.this, CartActivity.class));
                return true;
            }

            return false;
        });
    }


    private void setupCategories() {
        String[] categories = {"Violin", "Guitar", "Saxophone"};
        for (String cat : categories) {
            Button btn = new Button(this);
            btn.setText(cat);
            btn.setBackgroundResource(R.drawable.category_button_bg);
            categoryContainer.addView(btn);
        }
    }

    private void setupProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Guitar 1", "$132.00", R.drawable.pro1));
        products.add(new Product("Violin 1", "$1100.00", R.drawable.pro2));
        products.add(new Product("Guitar 3", "$1100.00", R.drawable.pro3));
        products.add(new Product("Guitar 4", "$1100.00", R.drawable.pro4));
        products.add(new Product("Guitar 4", "$1100.00", R.drawable.pro5));
        products.add(new Product("Guitar 4", "$1100.00", R.drawable.pro1));


        ProductAdapter adapter = new ProductAdapter(products);
        productRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        productRecyclerView.setAdapter(adapter);
    }
}