package com.m.foodrecipies;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.m.foodrecipies.Adapter.CategoryAdapter;
import com.m.foodrecipies.Model.CategoryItem;

import java.util.ArrayList;

public class FoodList extends AppCompatActivity {

    CategoryAdapter mAdapter;
    ArrayList<CategoryItem> foodList=new ArrayList<>();
    RecyclerView recyclerView;
    DatabaseReference dbFoodList;
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);


        Bundle bundle=getIntent().getExtras();
        data=bundle.getString("name");
        data=data.replace(" ","-");
        data=data.trim();
        Toast.makeText(getApplicationContext(),data,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        foodList.clear();
        dbFoodList= FirebaseDatabase.getInstance().getReference("FoodList").child(data);
        dbFoodList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                CategoryItem categoryItem=new CategoryItem();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    categoryItem=postSnapshot.getValue(CategoryItem.class);
                    foodList.add(categoryItem);
                }
                RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
                mAdapter=new CategoryAdapter(foodList);
                GridLayoutManager gridLayoutManager=new GridLayoutManager(getApplicationContext(),2);



                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(mAdapter);

                recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        CategoryItem categoryItem = foodList.get(position);
                        //Toast.makeText(getApplicationContext(), cricketer.getCname() + " is selected!", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),FoodDetail.class);
                        Bundle bundle=new Bundle();
                        bundle.putString("name",categoryItem.getName().trim());
                        bundle.putString("nameprevious",data);
                        Toast.makeText(getApplicationContext(),"data->"+data,Toast.LENGTH_SHORT).show();
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
