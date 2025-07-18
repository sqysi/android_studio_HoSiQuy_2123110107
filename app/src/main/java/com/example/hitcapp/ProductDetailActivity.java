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
    TextView productName, productPrice;
    Button addToCartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        productImage = findViewById(R.id.detailProductImage);
        productName = findViewById(R.id.detailProductName);
        productPrice = findViewById(R.id.detailProductPrice);
        addToCartBtn = findViewById(R.id.addToCartBtn);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        String image = intent.getStringExtra("imageUrl");

        productName.setText(name);
        productPrice.setText(price);
        Glide.with(this).load(image).into(productImage);

        addToCartBtn.setOnClickListener(v -> {
            CartManager.addProduct(new Product(name, price, image));
            Toast.makeText(this, "Đã thêm vào giỏ", Toast.LENGTH_SHORT).show();
        });
    }
}
