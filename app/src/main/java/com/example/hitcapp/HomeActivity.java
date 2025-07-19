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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView productRecycler, productRecycler1, categoryRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        categoryRecycler = findViewById(R.id.categoryRecycler);
        productRecycler = findViewById(R.id.productRecycler);
        productRecycler1 = findViewById(R.id.productRecycler1);

        // Setup category
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category("All"));
        categoryList.add(new Category("Footwear"));
        categoryList.add(new Category("Watch"));
        categoryList.add(new Category("Chair"));

        CategoryAdapter categoryAdapter = new CategoryAdapter(categoryList);
        categoryRecycler.setAdapter(categoryAdapter);
        categoryRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Thêm khoảng cách giữa các item
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.category_item_spacing); // ví dụ 8dp
        categoryRecycler.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

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
                            double priceValue = obj.getDouble("price");
                            String price = "$" + priceValue;
                            String imageUrl = obj.getString("image");
                            String description = obj.getString("description");

                            // Lấy rate
                            JSONObject ratingObj = obj.getJSONObject("rating");
                            double rate = ratingObj.getDouble("rate");

                            Product product = new Product(name, price, imageUrl, description, rate, priceValue);
                            productList.add(product);
                        }

                        // Sắp xếp sản phẩm nổi bật theo rate giảm dần
                        List<Product> featuredProducts = new ArrayList<>(productList);
                        Collections.sort(featuredProducts, (a, b) -> Double.compare(b.getRate(), a.getRate()));
                        ProductAdapter featuredAdapter = new ProductAdapter(featuredProducts, this);
                        productRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                        productRecycler.setAdapter(featuredAdapter);

                        // Sắp xếp sản phẩm giảm giá theo giá tăng dần
                        List<Product> discountProducts = new ArrayList<>(productList);
                        Collections.sort(discountProducts, Comparator.comparingDouble(Product::getPriceValue));
                        ProductAdapter discountAdapter = new ProductAdapter(discountProducts, this);
                        productRecycler1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                        productRecycler1.setAdapter(discountAdapter);

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
