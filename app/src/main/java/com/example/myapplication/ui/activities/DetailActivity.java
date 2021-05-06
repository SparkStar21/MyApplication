package com.example.myapplication.ui.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

import com.example.myapplication.BaseApplication;
import com.example.myapplication.Bean.BuyCommodityBean;
import com.example.myapplication.Bean.CommodityBean;
import com.example.myapplication.Presenters.BuyCommodity;
import com.example.myapplication.Presenters.OneData;
import com.example.myapplication.Presenters.impel.BuyCommodityImpel;
import com.example.myapplication.Presenters.impel.OneDataImpel;
import com.example.myapplication.R;
import com.example.myapplication.adpter.UltraPagerAdapter;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.ui.callbacks.Channel;
import com.example.myapplication.utils.CustomTab;
import com.example.myapplication.utils.WindowShow;
import com.tmall.ultraviewpager.UltraViewPager;
import com.tmall.ultraviewpager.UltraViewPagerAdapter;
import com.tmall.ultraviewpager.transformer.UltraDepthScaleTransformer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity implements Channel, View.OnClickListener {
    @Nullable
    @BindView(R.id.return_return)
    public ImageView finish;
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
    @Nullable
    @BindView(R.id.collect)
    public CustomTab collect;
    @Nullable
    @BindView(R.id.contactSeller)
    public TextView contactSeller;
    @Nullable
    @BindView(R.id.buy)
    public TextView buy;

    private CommodityBean commodityBean;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Intent intent=this.getIntent();
        initPresenter(intent.getIntExtra("position",0));
        initSet();
    }

    private void initSet(){
        finish.setOnClickListener(this);
        collect.setOnClickListener(this);
        contactSeller.setOnClickListener(this);
        buy.setOnClickListener(this);
    }

    private void initPresenter(int position){
        OneData oneData =new OneDataImpel();
        oneData.getImage(position);
        oneData.register(this);
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
    public void dataChannel(CommodityBean commodityBean) {
        Log.e("", String.valueOf(commodityBean.getBitmaps().size()));
        initPager(commodityBean.getBitmaps());
        originalPrice.setText(originalPrice.getText()+"¥"+commodityBean.getOriginPrice());
        currentPrice.setText(currentPrice.getText()+"¥"+commodityBean.getPrice());
        commodityName.setText(commodityBean.getName());
        buildingNum.setText(commodityBean.getLocation());
        contactPhone.setText(commodityBean.getPhone());
        contactMan.setText(commodityBean.getUser());
        commodityDes.setText(commodityBean.getDescription());
        this.commodityBean=commodityBean;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.finish:
                finish();
            case R.id.contactSeller:
                Intent intent=new Intent(this,ChatActivity.class);
                intent.putExtra("user",commodityBean.getGid());
                startActivity(intent);
            case R.id.buy:
                WindowShow windowShow=new WindowShow(this,R.layout.window_buy);
                windowShow.show();
                BuyCommodityBean buyCommodityBean=new BuyCommodityBean(commodityBean.getGid(),commodityBean.getName(),commodityBean.getDescription(),commodityBean.getPrice(),commodityBean.getUser(),commodityBean.getOriginPrice(),commodityBean.getPhone(), String.valueOf(BaseApplication.getUser().getUid()));
                BuyCommodity buyCommodity=new BuyCommodityImpel();
                buyCommodity.changeState(buyCommodityBean);
        }
    }
}
