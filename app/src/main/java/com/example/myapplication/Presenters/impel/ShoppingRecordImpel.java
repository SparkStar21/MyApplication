package com.example.myapplication.Presenters.impel;

import com.example.myapplication.Bean.ShoppingRecordBean;
import com.example.myapplication.Presenters.ShoppingRecord;
import com.example.myapplication.datas.DataCollection;
import com.example.myapplication.ui.callbacks.GetRecord;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class ShoppingRecordImpel implements ShoppingRecord {
    @Override
    public void getShoppingRecord(String user,String type) {
        Observer observer=new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                try {
                    getRecord.getRecord((List<ShoppingRecordBean>) o);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        DataCollection.getAllRecord(observer,user,type);
    }

    private GetRecord getRecord;
    @Override
    public void register(GetRecord callback) {
        getRecord=callback;
    }

}
