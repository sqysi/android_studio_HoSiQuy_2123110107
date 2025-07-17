package com.example.hitcapp;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    Button viewCartBtn;
    private RecyclerView categoryRecycler, productRecycler, productRecycler1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        productRecycler = findViewById(R.id.productRecycler);
        productRecycler1 = findViewById(R.id.productRecycler1);

        categoryRecycler = findViewById(R.id.categoryRecycler);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category("All"));
        categoryList.add(new Category("Footwear"));
        categoryList.add(new Category("Watch"));
        categoryList.add(new Category("Chair"));

        CategoryAdapter categoryAdapter = new CategoryAdapter(categoryList);
        categoryRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        categoryRecycler.setAdapter(new CategoryAdapter(categoryList)); // giả sử bạn đã có adapter

// Thêm khoảng cách giữa các item
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.category_item_spacing); // ví dụ 8dp
        categoryRecycler.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Nike Shoes", "$12.00", R.drawable.pro1));
        productList.add(new Product("Chair", "$10.00", R.drawable.pro2));
        productList.add(new Product("Adidas Shoes", "$15.00", R.drawable.pro3));
        productList.add(new Product("Gaming Chair", "$40.00", R.drawable.pro4));

        ProductAdapter productAdapter = new ProductAdapter(productList);
        productRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        productRecycler.setAdapter(new ProductAdapter(productList));

        productRecycler1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        productRecycler1.setAdapter(new ProductAdapter(productList));

        // Bottom Navigation
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                return true;
            } else if (id == R.id.nav_search) {
                Toast.makeText(this, "Favorite chưa hoạt động", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.nav_cart) {
                Toast.makeText(this, "Cart chưa hoạt động", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.nav_profile) {
                Toast.makeText(this, "Profile chưa hoạt động", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });
    }
}