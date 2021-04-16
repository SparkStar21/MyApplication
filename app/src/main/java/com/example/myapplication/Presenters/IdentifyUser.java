package com.example.myapplication.Presenters;

import com.example.myapplication.Bean.RegisterThis;
import com.example.myapplication.Bean.User;
import com.example.myapplication.ui.callbacks.Login;

public interface IdentifyUser extends RegisterThis<Login> {
    void identifyUser(User user);
}
