package com.example.myapplication.datas;

import com.example.myapplication.utils.SqlServer;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DataCollection {
    private static SqlServer  sqlServer=new SqlServer("10.0.2.2", "SecondHand_Market", "SA", "meng1033");
    public DataCollection(){
        sqlServer.connect();
    }

    public static void getOne(Observer o, String str){
        Observable observable=new Observable() {
            @Override
            protected void subscribeActual(io.reactivex.Observer observer) {
                observer.onNext(sqlServer.getString(str));
            }
        };
        subscribe(o,observable);
    }

    private static void subscribe(Observer s,Observable o){
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())//子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .retry(0)//请求失败重连次数
                .subscribe(s);
    }

}
