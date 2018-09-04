package com.m.foodrecipies;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.m.foodrecipies.Model.FoodItem;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

public class FoodDetail extends AppCompatActivity {

    ImageView image;
    TextView ingredients;
    TextView recipies;
    String data,data_previous;
    DatabaseReference dbFoodDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        Bundle bundle=getIntent().getExtras();
        data=bundle.getString("name");
        data_previous=bundle.getString("nameprevious");
        data=data.replace(" ","-");
        data=data.trim();
        data_previous=data_previous.replace(" ","-");
        data_previous=data_previous.trim();
        //Toast.makeText(getApplicationContext(),"data->"+data,Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(),"data_previous->"+data_previous,Toast.LENGTH_SHORT).show();



        image=(ImageView)findViewById(R.id.image);
        ingredients=(TextView)findViewById(R.id.ingredients);
        recipies=(TextView)findViewById(R.id.recipies);
    }

    @Override
    protected void onStart() {
        super.onStart();
        final int ing_count=0;
        dbFoodDetail= FirebaseDatabase.getInstance().getReference("Food_Detail").child(data_previous).child(data);
        dbFoodDetail.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                FoodItem foodItem=new FoodItem();
                foodItem=dataSnapshot.getValue(FoodItem.class);
                foodItem.ingredients=foodItem.ingredients.replace("'","\n->>");
                ingredients.setText(foodItem.getIngredients());
                foodItem.recipies=foodItem.recipies.replace("'","\n>>");
                recipies.setText(foodItem.getRecipies());
                final FoodItem finalFoodItem = foodItem;
                Picasso.get()
                        .load(foodItem.getImageLink())
                        .memoryPolicy(MemoryPolicy.NO_CACHE)
                        .into(image, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError(Exception e) {
                                Picasso.get()
                                        .load(finalFoodItem.getImageLink())
                                        .error(R.drawable.food_photo)
                                        .into(image, new Callback() {
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


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
