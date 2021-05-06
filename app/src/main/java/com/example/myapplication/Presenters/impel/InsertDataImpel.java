package com.example.myapplication.Presenters.impel;

import android.util.Log;

import com.example.myapplication.Presenters.InsertData;
import com.example.myapplication.datas.DataCollection;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class InsertDataImpel implements InsertData {
    @Override
    public void insertData(String str) {
        Observer observer=new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                Log.e("InsertDataImpel","已插入"+o+"条");
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        DataCollection.insertData(observer,str);
    }
}
