package com.example.myapplication.Presenters;

import com.example.myapplication.base.RegisterThis;
import com.example.myapplication.ui.callbacks.GetRecord;

public interface ShoppingRecord extends RegisterThis <GetRecord> {
    void getShoppingRecord(String user,String type);
}
