package com.example.myapplication.Presenters.impel;

import com.example.myapplication.Presenters.AllContacts;
import com.example.myapplication.datas.DataCollection;
import com.example.myapplication.ui.callbacks.Contacts;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class AllContactsImpel implements AllContacts {
    @Override
    public void getAllContacts() {
        Observer observer=new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                handle((List<String>) o);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        DataCollection.getAllContacts(observer);
    }

    private void handle(List<String> list){
        contacts.loadData(list);
    }

    private Contacts contacts;
    @Override
    public void register(Contacts callback) {
        contacts=callback;
    }
}
