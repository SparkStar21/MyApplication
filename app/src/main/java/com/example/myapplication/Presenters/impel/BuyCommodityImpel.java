package com.example.myapplication.Presenters.impel;

import com.example.myapplication.Bean.BuyCommodityBean;
import com.example.myapplication.Presenters.BuyCommodity;
import com.example.myapplication.datas.DataCollection;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class BuyCommodityImpel implements BuyCommodity {
    @Override
    public void changeState(BuyCommodityBean buyCommodityBean) {
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
        DataCollection.buyCommodity(observer,buyCommodityBean);
    }
}
