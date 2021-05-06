package com.example.myapplication.Presenters.impel;

import com.example.myapplication.Bean.CommodityBean;
import com.example.myapplication.Presenters.UpLoad;
import com.example.myapplication.datas.DataCollection;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class UploadImpel implements UpLoad {
    @Override
    public void upload(CommodityBean commodityBean) {
        Observer observer=new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        DataCollection.uploadCommodity(observer,commodityBean);
    }
}
