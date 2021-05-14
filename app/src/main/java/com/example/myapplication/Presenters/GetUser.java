package com.example.myapplication.Presenters;

import com.example.myapplication.base.RegisterThis;
import com.example.myapplication.ui.activities.ChatActivity;
import com.example.myapplication.ui.callbacks.GetUserInfo;
import com.example.myapplication.ui.callbacks.Login;

public interface GetUser extends RegisterThis<GetUserInfo> {
    void getUser(String userName);
}
