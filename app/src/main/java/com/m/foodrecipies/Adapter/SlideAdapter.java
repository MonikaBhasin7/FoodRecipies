package com.m.foodrecipies.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.m.foodrecipies.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

//public class SlideAdapter extends PagerAdapter {

    /*private List<SliderItem> sliderList;
    private LayoutInflater inflater;
    private Context context;

    public SlideAdapter(Context context, List<SliderItem> sliderList) {
        this.context = context;
        this.sliderList=sliderList;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return sliderList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {

        final SliderItem sliderItem=sliderList.get(position);

        View myImageLayout = inflater.inflate(R.layout.slide, view, false);
        final ImageView myImage = (ImageView) myImageLayout.findViewById(R.id.image);

        Picasso.get()
                .load(sliderItem.getImageLink())
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(myImage, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso.get()
                                .load(sliderItem.getImageLink())
                                .error(R.drawable.food_photo)
                                .into(myImage, new Callback() {
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
        view.addView(myImageLayout, 0);
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }*/
//}
