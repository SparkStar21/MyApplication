package com.example.myapplication.Presenters;

import com.example.myapplication.base.RegisterThis;
import com.example.myapplication.ui.callbacks.Channel;

public interface Image extends RegisterThis<Channel> {
    void getImage(String path);
}
