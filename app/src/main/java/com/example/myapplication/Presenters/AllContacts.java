package com.example.myapplication.Presenters;

import com.example.myapplication.base.RegisterThis;
import com.example.myapplication.ui.callbacks.Contacts;

public interface AllContacts extends RegisterThis<Contacts> {
    void getAllContacts();
}
