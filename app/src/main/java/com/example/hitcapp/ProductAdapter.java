package com.example.hitcapp;

import java.util.List;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    List<Product> products;

    public ProductAdapter(List<Product> products) {
        this.products = products;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productPrice;

        public ProductViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productName  = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
        }
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item_product, parent, false);

        return new ProductViewHolder(view);
    }

    @Override public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product p = products.get(position);
        holder.productImage.setImageResource(p.imageRes);
        holder.productName.setText(p.name);
        holder.productPrice.setText(p.price);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ProductDetailActivity.class);
            intent.putExtra("name", p.name);
            intent.putExtra("price", p.price);
            intent.putExtra("imageRes", p.imageRes);
            v.getContext().startActivity(intent);
        });

    }

    @Override public int getItemCount() {
        return products.size();
    }

}
