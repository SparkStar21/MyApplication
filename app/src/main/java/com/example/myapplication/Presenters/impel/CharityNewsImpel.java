package com.example.myapplication.Presenters.impel;

import com.example.myapplication.Presenters.CharityNews;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class CharityNewsImpel implements CharityNews {
    @Override
    public void getCharityNews() {
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

    }
}
