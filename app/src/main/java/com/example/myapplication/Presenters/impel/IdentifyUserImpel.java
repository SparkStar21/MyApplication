package com.example.myapplication.Presenters.impel;

import com.example.myapplication.Bean.User;
import com.example.myapplication.Presenters.IdentifyUser;
import com.example.myapplication.datas.DataCollection;
import com.example.myapplication.ui.callbacks.Login;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class IdentifyUserImpel implements IdentifyUser {
    @Override
    public void identifyUser(User user) {
        Observer observer=new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {
                if(value.equals(user.getUsercode()))
                {
                    login.loginCallback(true);
                }else {
                    login.loginCallback(false);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        DataCollection.getOne(observer,String.format("select Pwd from users where Name='%s'",user.getUsername()));
    }
    private Login login;
    @Override
    public void register(Login callback) {
        login=callback;
    }
}
