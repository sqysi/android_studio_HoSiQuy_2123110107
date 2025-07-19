package com.example.hitcapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

public class ProductDetailActivity extends AppCompatActivity {
    ImageView productImage;
    TextView productName, productPrice, productDescription;
    Button addToCartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Ánh xạ view
        productImage = findViewById(R.id.detailProductImage);
        productName = findViewById(R.id.detailProductName);
        productPrice = findViewById(R.id.detailProductPrice);
        productDescription = findViewById(R.id.detailProductDescription);
        addToCartBtn = findViewById(R.id.addToCartBtn);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        String image = intent.getStringExtra("imageUrl");
        String description = intent.getStringExtra("description");
        double rate = intent.getDoubleExtra("rate", 0.0);
        double priceValue = intent.getDoubleExtra("priceValue", 0.0);

        // Hiển thị dữ liệu lên giao diện
        productName.setText(name);
        productPrice.setText(price);
        productDescription.setText(description);
        Glide.with(this).load(image).into(productImage);

        // Thêm vào giỏ hàng khi click
        addToCartBtn.setOnClickListener(v -> {
            Product product = new Product(name, price, image, description, rate, priceValue);
            CartManager.addProduct(product);
            Toast.makeText(this, "Đã thêm vào giỏ", Toast.LENGTH_SHORT).show();
        });
    }
}
