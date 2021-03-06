package com.example.myapplication.Presenters.impel;

import android.util.Log;

import com.example.myapplication.Bean.CommodityBean;
import com.example.myapplication.Presenters.AllData;
import com.example.myapplication.datas.DataCollection;
import com.example.myapplication.ui.callbacks.AllCommodity;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class AllDataImpel implements AllData {
    private String LOG="AllDataImpel";
    @Override
    public void loadAllData() {
        Observer observer=new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                Log.e(LOG,o.toString());
                commodity.getAllCommodity((List<CommodityBean>) o);

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        DataCollection.getAllCommodity(observer,"SELECT gid,gimage,gname,gnumber,gdes,gprice,phoneNum,guser,originalprice FROM goods");
    }


    private AllCommodity commodity;
    @Override
    public void register(AllCommodity callback) {
        commodity=callback;
    }
}
