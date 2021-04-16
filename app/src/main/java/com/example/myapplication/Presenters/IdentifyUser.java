package com.example.myapplication.Presenters;

import com.example.myapplication.base.RegisterThis;
import com.example.myapplication.Bean.User;
import com.example.myapplication.ui.callbacks.Login;

public interface IdentifyUser extends RegisterThis<Login> {
    void identifyUser(User user);
}
