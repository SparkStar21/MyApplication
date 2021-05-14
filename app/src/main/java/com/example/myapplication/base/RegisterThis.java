package com.example.myapplication.base;

public interface RegisterThis <T> {
    void register(T callback);
    void unregister(T callback);
}
