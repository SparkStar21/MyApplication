package com.example.myapplication.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.myapplication.R;

public class WindowShow {
    private Dialog dialog;
    private View view;
    public WindowShow(Context context,int layoutId){
        dialog = new Dialog(context, R.style.DialogTheme);
        //2、设置布局
        view = View.inflate(context, layoutId, null);
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    }
    public void show(){
        dialog.show();
    }
    public void close(){
        dialog.dismiss();
    }

}
