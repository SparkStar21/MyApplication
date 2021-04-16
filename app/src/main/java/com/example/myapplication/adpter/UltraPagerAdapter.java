package com.example.myapplication.adpter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

public class UltraPagerAdapter extends PagerAdapter {
    private List <Bitmap> list;
    private Context context;
    public UltraPagerAdapter(List<Bitmap> list,Context context){
        this.list=list;
        this.context=context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView=new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        Glide.with(context).load(list.get(position)).into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
