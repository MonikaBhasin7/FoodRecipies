package com.m.foodrecipies;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.m.foodrecipies.Adapter.CategoryAdapter;
import com.m.foodrecipies.Model.CategoryItem;

import java.util.ArrayList;

public class Category extends AppCompatActivity {


    //CollapsingToolbarLayout toolbar1;
    //ArrayList<SliderItem> slideList=new ArrayList<>();
    //private static ViewPager mPager;
    private static int currentPage = 0;
    //Toolbar toolbar;
    CategoryAdapter mAdapter;
    RecyclerView recyclerView;
    DatabaseReference dbCategory,dbSlider;
    ArrayList<CategoryItem> categoryList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        //toolbar=findViewById(R.id.toolbar_main);
        //toolbar.setTitle("Categories");
        //toolbar1.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        //setSupportActionBar(toolbar);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
    }


    /*private void init() {

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SlideAdapter(Category.this,slideList));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == slideList.size()) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);
    }*/

    @Override
    protected void onStart() {
        super.onStart();

        categoryList.clear();
        //slideList.clear();
        dbCategory= FirebaseDatabase.getInstance().getReference("Category");
        /*dbSlider= FirebaseDatabase.getInstance().getReference("Slider");
        dbSlider.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                SliderItem sliderItem=new SliderItem();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    sliderItem=postSnapshot.getValue(SliderItem.class);
                    slideList.add(sliderItem);
                }
                init();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
        dbCategory.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                CategoryItem categoryItem=new CategoryItem();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    categoryItem=postSnapshot.getValue(CategoryItem.class);
                    categoryList.add(categoryItem);
                }
                mAdapter=new CategoryAdapter(categoryList);
                GridLayoutManager gridLayoutManager=new GridLayoutManager(getApplicationContext(),2);
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(mAdapter);

                recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        CategoryItem categoryItem = categoryList.get(position);
                        //Toast.makeText(getApplicationContext(), cricketer.getCname() + " is selected!", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),FoodList.class);
                        Bundle bundle=new Bundle();
                        bundle.putString("name",categoryItem.getName().trim());
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
