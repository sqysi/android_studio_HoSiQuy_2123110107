package com.example.hitcapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
        int imageRes = intent.getIntExtra("imageRes", 0);

        productImage.setImageResource(imageRes);
        productName.setText(name);
        productPrice.setText(price);

        addToCartBtn.setOnClickListener(v -> {
            CartManager.addProduct(new Product(name, price, imageRes));
            Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show();
        });
    }
}
