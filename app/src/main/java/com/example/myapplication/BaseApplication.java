package com.example.myapplication;

import android.app.Application;
import android.content.Context;

import com.example.myapplication.Bean.CommodityBean;
import com.example.myapplication.Presenters.AllData;
import com.example.myapplication.Presenters.impel.AllDataImpel;
import com.example.myapplication.datas.DataCollection;
import com.example.myapplication.ui.callbacks.AllCommodity;

import java.util.List;

public class BaseApplication extends Application implements AllCommodity {
    private static Context context;
    public static List<CommodityBean>unitList;
    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(new Runnable() {
            @Override
            public void run() {
                DataCollection dataCollection=new DataCollection();
                initPresenter();
            }
        }).start();

    }
    private void initPresenter(){
        AllData allData=new AllDataImpel();
        allData.loadAllData();
        allData.register(this);
    }
    @Override
    public void getAllCommodity(List<CommodityBean> list) {
        unitList=list;
    }
}
