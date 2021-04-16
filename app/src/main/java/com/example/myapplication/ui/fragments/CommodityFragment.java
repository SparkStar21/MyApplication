package com.example.myapplication.ui.fragments;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.myapplication.Bean.CommodityBean;
import com.example.myapplication.Presenters.AllData;
import com.example.myapplication.Presenters.impel.AllDataImpel;
import com.example.myapplication.R;
import com.example.myapplication.adpter.RecycleViewAdapter;
import com.example.myapplication.adpter.UltraPagerAdapter;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.ui.callbacks.AllCommodity;
import com.tmall.ultraviewpager.UltraViewPager;
import com.tmall.ultraviewpager.UltraViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommodityFragment extends BaseFragment implements AllCommodity {
    @Nullable
    @BindView(R.id.ultraViewpager)
    public UltraViewPager ultraViewPager;
    @Nullable
    @BindView(R.id.recycleViewCommodity)
    public RecyclerView recyclerViewCommodity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View root =inflater.inflate(R.layout.fragment_commodity,container,false);
        ButterKnife.bind(this,root);
     //  initPresenter();
        List<Bitmap>list=new ArrayList<>();


        return root;
    }

    private void initPresenter(){
        AllData allData=new AllDataImpel();
        allData.loadAllData();
        allData.register(this);
    }

    private void initData(){


    }



    private void initPager(List <Bitmap> list){
        PagerAdapter adapter = new UltraViewPagerAdapter(new UltraPagerAdapter(list,getActivity()));
        ultraViewPager.setAdapter(adapter);
        ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
//内置indicator初始化
        ultraViewPager.initIndicator();
//设置indicator样式
        ultraViewPager.getIndicator()
                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                .setFocusColor(Color.GREEN)
                .setNormalColor(Color.WHITE)
                .setRadius((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()));
//设置indicator对齐方式
        ultraViewPager.getIndicator().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
//构造indicator,绑定到UltraViewPager
        ultraViewPager.getIndicator().build();

//设定页面循环播放
        ultraViewPager.setInfiniteLoop(true);
//设定页面自动切换  间隔2秒
        ultraViewPager.setAutoScroll(2000);
    }

    @Override
    public void getAllCommodity(List<CommodityBean> list) {
        recyclerViewCommodity.setLayoutManager(new GridLayoutManager(getActivity(),1));
        recyclerViewCommodity.setAdapter(new RecycleViewAdapter(list,getActivity()));
    }
}
