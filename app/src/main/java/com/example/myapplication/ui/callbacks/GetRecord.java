package com.example.myapplication.ui.callbacks;

import com.example.myapplication.Bean.ShoppingRecordBean;

import java.util.List;

public interface GetRecord {
    void getRecord(List <ShoppingRecordBean> list) throws InterruptedException;
}
