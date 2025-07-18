package com.example.hitcapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView productRecycler, productRecycler1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        productRecycler = findViewById(R.id.productRecycler);
        productRecycler1 = findViewById(R.id.productRecycler1);

        fetchProductsFromApi();

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) return true;
            else if (id == R.id.nav_search) {
                Toast.makeText(this, "Favorite chưa hoạt động", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.nav_cart) {
                startActivity(new Intent(this, CartActivity.class));
                return true;
            } else if (id == R.id.nav_profile) {
                Toast.makeText(this, "Profile chưa hoạt động", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });
    }

    private void fetchProductsFromApi() {
        String url = "https://fakestoreapi.com/products";

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    List<Product> productList = new ArrayList<>();
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject obj = response.getJSONObject(i);
                            String name = obj.getString("title");
                            String price = "$" + obj.getString("price");
                            String imageUrl = obj.getString("image");
                            String description = obj.getString("description");

                            Product product = new Product(name, price, imageUrl);
                            productList.add(product);
                        }

                        ProductAdapter productAdapter = new ProductAdapter(productList, this);
                        productRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                        productRecycler.setAdapter(productAdapter);

                        productRecycler1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                        productRecycler1.setAdapter(productAdapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    Toast.makeText(this, "Lỗi kết nối API!", Toast.LENGTH_SHORT).show();
                });

        queue.add(jsonArrayRequest);
    }
}
