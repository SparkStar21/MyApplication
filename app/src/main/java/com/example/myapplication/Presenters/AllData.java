package com.example.myapplication.Presenters;

import com.example.myapplication.base.RegisterThis;
import com.example.myapplication.ui.callbacks.AllCommodity;

public interface AllData extends RegisterThis<AllCommodity> {
    void loadAllData();
}
