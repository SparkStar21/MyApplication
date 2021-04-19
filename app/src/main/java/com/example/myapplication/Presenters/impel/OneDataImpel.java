package com.example.myapplication.Presenters.impel;

import com.example.myapplication.Bean.CommodityBean;
import com.example.myapplication.Presenters.OneData;
import com.example.myapplication.datas.DataCollection;
import com.example.myapplication.ui.callbacks.Channel;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class OneDataImpel implements OneData {
    @Override
    public void getImage(int position) {
        Observer observer=new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                channel.dataChannel((CommodityBean) o);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        DataCollection.getDetail(observer,String.format("SELECT gid,gimage,gname,gnumber,gdes,gprice,phoneNum,guser,originalprice FROM goods WHERE gid = %d",position));
    }

    private Channel channel;
    @Override
    public void register(Channel callback) {
        channel=callback;
    }
}
