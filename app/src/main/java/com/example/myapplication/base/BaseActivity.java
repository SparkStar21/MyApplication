package com.example.myapplication.base;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {


    public void navigationTo(Context context,Class c){
        startActivity(new Intent(context,c));
    }

    public void showToast(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
}
