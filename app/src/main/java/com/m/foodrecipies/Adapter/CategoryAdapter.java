package com.m.foodrecipies.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.m.foodrecipies.Model.CategoryItem;
import com.m.foodrecipies.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter  extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<CategoryItem> categoryList;

    public CategoryAdapter(List<CategoryItem> categoryList) {
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.category_item_layout,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryAdapter.CategoryViewHolder holder, int position) {

        final CategoryItem categoryItem=categoryList.get(position);

        Picasso.get()
                .load(categoryItem.getImageLink())
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(holder.image, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso.get()
                                .load(categoryItem.getImageLink())
                                .error(R.drawable.food_photo)
                                .into(holder.image, new Callback() {
                                    @Override
                                    public void onSuccess() {

                                    }

                                    @Override
                                    public void onError(Exception e) {
                                        Log.e("error","Wallpaper not Fetched");
                                    }
                                });
                    }
                });
        holder.name.setText(categoryItem.getName());

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            image=(ImageView) itemView.findViewById(R.id.image);
            name= (TextView) itemView.findViewById(R.id.name);
        }
    }
}
