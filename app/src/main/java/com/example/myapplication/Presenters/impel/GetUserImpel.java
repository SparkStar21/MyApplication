package com.example.myapplication.Presenters.impel;

import com.example.myapplication.Bean.User;
import com.example.myapplication.Presenters.GetUser;
import com.example.myapplication.datas.DataCollection;
import com.example.myapplication.ui.activities.ChatActivity;
import com.example.myapplication.ui.callbacks.GetUserInfo;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class GetUserImpel implements GetUser {

    @Override
    public void getUser(String userName) {
        Observer observer=new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                getUserInfo.getUserInfo((User) o);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        DataCollection.getOne(observer,String.format("select uid,Name,Pwd,icon,Phone  from users where Name='%s'",userName));
    }

    private GetUserInfo getUserInfo;
    @Override
    public void register(GetUserInfo callback) {
        getUserInfo=callback;
    }
}
