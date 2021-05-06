package com.example.myapplication.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Bean.CharityNewsBean;
import com.example.myapplication.R;
import com.example.myapplication.adpter.SlideHowFragmentAdapter;
import com.example.myapplication.ui.activities.CharityActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SlideshowFragment extends Fragment implements SlideHowFragmentAdapter.OnItemClick {

    @Nullable
    @BindView(R.id.slideHow_recycleView)
    public RecyclerView recyclerView;

    private String [] titles=new String[]{"救灾扶贫","助医扶残","支教助学","安老助孤","专项基金","重大灾难"};
    private String [] content = new String[]{"为积极响应习近平总书记在十九大报告中关于“健康中国”战略的部署，切实帮助经济困难的大病患者及其家庭巩固脱贫攻坚成果，中华慈善总会发起“善济病困项目。","中华慈善总会启动的“一张纸献爱心行动...","项目面向欠发达地区的幼儿园及小学学生，以及重大灾害发生时受灾地区的幼儿园及小学学生，为他们提供卫生防护用品，从洗手洗脸开始，教育培养儿童养成良好的日常生活、卫生习惯，减少疾病和疫情的发生.....","中华慈善总会大众慈善基金自2009年3月成立以来，本着“让大众参与慈善，让慈善惠及大众”的精神理念，...","慈爱孤儿工程项目是2000年由中华慈善总会发起，以救助百万孤儿为目标的大型慈善项目。项目宗旨：推进社会福利化的进程，引导全社会同献爱心，让孤残儿童获得美好童年...","2010年4月14日青海省玉树藏族自治州玉树县发生7.1级地震，人民群众生命财产遭受严重损失。中..."};
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        ButterKnife.bind(this,root);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        List <CharityNewsBean> list=new ArrayList<>();
        for(int i=0;i<titles.length;i++){
            list.add(new CharityNewsBean(titles[i],content[i]));
        }
        SlideHowFragmentAdapter slideHowFragmentAdapter=new SlideHowFragmentAdapter(getContext(),list);
        recyclerView.setAdapter(slideHowFragmentAdapter);
        slideHowFragmentAdapter.setOnItemClick(this);
        return root;
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent=new Intent(getActivity(), CharityActivity.class);
        switch (position) {
            case 0:{
                intent.putExtra("url","http://www.chinacharityfederation.org/p.html?pid=9489ed61-18be-48e9-b77a-140b49dbee60");
                startActivity(intent);
                break;
            }
            case 1:{
                intent.putExtra("url","http://www.chinacharityfederation.org/p.html?pid=991af453-085b-496a-9aff-13270bea1151");
                startActivity(intent);
                break;
            }
            case 2:{
                intent.putExtra("url","http://www.chinacharityfederation.org/p.html?pid=fc93f81a-3a24-4b49-bd52-1e3ce05825c0");
                startActivity(intent);
                break;
            }
            case 3:{
                intent.putExtra("url","http://www.chinacharityfederation.org/p.html?pid=df0e38fa-a9b3-43b3-a734-cc4c433e915a");
                startActivity(intent);
                break;
            }
            case 4:{
                intent.putExtra("url","http://www.chinacharityfederation.org/p.html?pid=75f2ee6f-767b-4972-b024-5390e4183690");
                startActivity(intent);
                break;
            }
            case 5:{
                intent.putExtra("url","http://www.chinacharityfederation.org/p.html?pid=15479eb3-0510-43e9-ad0b-14542c9a0125");
                startActivity(intent);
                break;
            }
            default:break;
        };
    }
}