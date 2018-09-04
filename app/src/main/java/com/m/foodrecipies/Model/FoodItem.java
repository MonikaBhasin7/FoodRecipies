package com.m.foodrecipies.Model;

import android.widget.ImageView;

public class FoodItem {

    public String name;
    public String ingredients;
    public String recipies;
    public String imageLink;


    public FoodItem() {
    }

    public FoodItem(String name, String ingredients, String recipies, String imageLink) {
        this.name = name;
        this.ingredients = ingredients;
        this.recipies = recipies;
        this.imageLink = imageLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getRecipies() {
        return recipies;
    }

    public void setRecipies(String recipies) {
        this.recipies = recipies;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
