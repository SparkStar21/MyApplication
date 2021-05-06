package com.example.myapplication.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CharityActivity extends BaseActivity {
    @Nullable
    @BindView(R.id.detailNews)
    public WebView detailNews;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_news);
        ButterKnife.bind(this);
        Intent intent=this.getIntent();
        detailNews.loadUrl(intent.getStringExtra("url"));
    }


}
