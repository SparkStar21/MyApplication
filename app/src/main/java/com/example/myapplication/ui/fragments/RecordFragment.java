package com.example.myapplication.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.BaseApplication;
import com.example.myapplication.Bean.ShoppingRecordBean;
import com.example.myapplication.Bean.User;
import com.example.myapplication.Presenters.ShoppingRecord;
import com.example.myapplication.Presenters.impel.ShoppingRecordImpel;
import com.example.myapplication.R;
import com.example.myapplication.adpter.RecordRecycleViewAdapter;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.ui.callbacks.GetRecord;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecordFragment extends BaseFragment implements GetRecord {
    @Nullable
    @BindView(R.id.recycleViewRecord)
    public RecyclerView recyclerViewRecord;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_record,container,false);
        ButterKnife.bind(this,root);
        initPresenter();
        return root;
    }

    private void initPresenter(){
        ShoppingRecord shoppingRecord=new ShoppingRecordImpel();
        Log.e("", String.valueOf(BaseApplication.getUser().getUsericon()));
        shoppingRecord.getShoppingRecord(String.valueOf(BaseApplication.getUser().getUsericon()),"table_buy");
        shoppingRecord.register(this);
    }

    @Override
    public void getRecord(List<ShoppingRecordBean> list) {
        RecordRecycleViewAdapter recordRecycleViewAdapter=new RecordRecycleViewAdapter(list,getContext());
        recyclerViewRecord.setLayoutManager(new GridLayoutManager(getContext(),1));
        recyclerViewRecord.setAdapter(recordRecycleViewAdapter);
    }
}
