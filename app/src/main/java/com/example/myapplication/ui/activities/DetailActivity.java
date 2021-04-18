package com.example.myapplication.ui.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

import com.example.myapplication.BaseApplication;
import com.example.myapplication.Bean.CommodityBean;
import com.example.myapplication.Presenters.Image;
import com.example.myapplication.Presenters.impel.ImageImpel;
import com.example.myapplication.R;
import com.example.myapplication.adpter.UltraPagerAdapter;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.ui.callbacks.Channel;
import com.tmall.ultraviewpager.UltraViewPager;
import com.tmall.ultraviewpager.UltraViewPagerAdapter;
import com.tmall.ultraviewpager.transformer.UltraDepthScaleTransformer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity implements Channel {
    @Nullable
    @BindView(R.id.images)
    public UltraViewPager ultraViewPager;
    @Nullable
    @BindView(R.id.originalPrice)
    public TextView originalPrice;
    @Nullable
    @BindView(R.id.currentPrice)
    public TextView currentPrice;
    @Nullable
    @BindView(R.id.commodityName)
    public TextView commodityName;
    @Nullable
    @BindView(R.id.buildingNum)
    public TextView buildingNum;
    @Nullable
    @BindView(R.id.contactMan)
    public TextView contactMan;
    @Nullable
    @BindView(R.id.contactPhone)
    public TextView contactPhone;
    @Nullable
    @BindView(R.id.commodityDes)
    public TextView commodityDes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Intent intent=this.getIntent();
        initData(intent.getIntExtra("position",0));
    }

    private void initData(int position){
        CommodityBean commodityBean= BaseApplication.unitList.get(position);
        originalPrice.setText(originalPrice.getText()+"¥"+commodityBean.getOriginPrice());
        currentPrice.setText(currentPrice.getText()+"¥"+commodityBean.getPrice());
        commodityName.setText(commodityBean.getName());
        buildingNum.setText(commodityBean.getLocation());
        contactPhone.setText(commodityBean.getPhone());
        contactMan.setText(commodityBean.getUser());
        commodityDes.setText(commodityBean.getDescription());
        Image image=new ImageImpel();
        image.getImage(commodityBean.getPicturePath());
        image.register(this);
    }



    private void initPager(List<Bitmap> list){
        PagerAdapter adapter = new UltraViewPagerAdapter(new UltraPagerAdapter(list,this));
        ultraViewPager.setAdapter(adapter);
        ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        ultraViewPager.setPageTransformer(false,new UltraDepthScaleTransformer());
        ultraViewPager.setMultiScreen(0.5f);
//内置indicator初始化

//设定页面循环播放
        ultraViewPager.setInfiniteLoop(true);
//设定页面自动切换  间隔2秒
        ultraViewPager.setAutoScroll(3000);
    }

    @Override
    public void dataChannel(List <Bitmap> list) {
        Log.e("", String.valueOf(list.size()));
        initPager(list);
    }
}
