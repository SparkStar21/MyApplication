package com.example.myapplication.Presenters.impel;

import android.graphics.Bitmap;

import com.example.myapplication.Presenters.Image;
import com.example.myapplication.datas.DataCollection;
import com.example.myapplication.ui.callbacks.Channel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class ImageImpel implements Image {
    @Override
    public void getImage(String path) {
        Observer observer=new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                channel.dataChannel((List<Bitmap>)o);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        DataCollection.getImage(observer,path);
    }

    private Channel channel;
    @Override
    public void register(Channel callback) {
        channel=callback;
    }
}
