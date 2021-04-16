package com.example.myapplication;

import android.app.Application;
import android.content.Context;

import com.example.myapplication.datas.DataCollection;

public class BaseApplication extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(new Runnable() {
            @Override
            public void run() {
                DataCollection dataCollection=new DataCollection();
            }
        }).start();

        this.context=getApplicationContext();
    }


}
