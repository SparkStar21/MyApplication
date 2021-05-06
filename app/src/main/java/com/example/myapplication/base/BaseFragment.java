package com.example.myapplication.base;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    public void navigationTo(Context context,Class c){
        Intent intent=new Intent(context,c);
        startActivity(intent);
    }

    public void showToast(Context context,String str){
        Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
    }

}
