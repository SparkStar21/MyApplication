package com.example.myapplication.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends BaseFragment {
    @Nullable
    @BindView(R.id.bottomNavigation)
    public BottomBar bottomBar;
    @Nullable
    @BindView(R.id.showArea)
    public LinearLayout showArea;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View root = inflater.inflate(R.layout.fragment_main,container,false);
        ButterKnife.bind(this,root);
        initBar();
        return root;

    }


    private void initBar(){
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                Log.e("", String.valueOf(tabId));
                switch (tabId){
                    case 2131231024:{
                        showArea.removeAllViews();
                        getParentFragmentManager().beginTransaction().add(R.id.showArea,new CommodityFragment()).commit();
                        break;
                    }
                    case 2131231025:{
                        showArea.removeAllViews();
                        getParentFragmentManager().beginTransaction().add(R.id.showArea,new MessageFragment()).commit();
                        break;
                    }
                    case 2131231026:{
                        showArea.removeAllViews();
                        getParentFragmentManager().beginTransaction().add(R.id.showArea,new RecordFragment()).commit();
                        break;
                    }
                    default:break;
                }
            }
        });
    }

}
