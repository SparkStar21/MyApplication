package com.example.myapplication.ui.activities;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Bean.User;
import com.example.myapplication.Presenters.IdentifyUser;
import com.example.myapplication.Presenters.impel.IdentifyUserImpel;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.ui.callbacks.Login;
import com.example.myapplication.utils.CodeUtils;

/**
 *登录
**/

public class LoginActivity extends BaseActivity implements Login {
    private EditText edAccount;
    private EditText edyzm;
    private EditText edPwd;
    private Button loginbtn;
    private ImageView mCodeYzm;
    private TextView mTvForgetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
    }

    /**
     * 绑定控件
     */
    private void initView() {
        edAccount = (EditText) findViewById(R.id.login_account);
        edPwd = (EditText) findViewById(R.id.login_pwd);
        edyzm = (EditText) findViewById(R.id.ed_yzm);
        loginbtn = (Button) findViewById(R.id.login_button);
        mCodeYzm = (ImageView) findViewById(R.id.code_yzm);
        mTvForgetPassword = (TextView) findViewById(R.id.tv_Forget_password);
    }

    /**
     * 点击事件
     */
    private void initData() {
        CodeUtils codeUtils=CodeUtils.getInstance();
        Bitmap bitmap = codeUtils.createBitmap();
        mCodeYzm.setImageBitmap(bitmap);
        mCodeYzm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CodeUtils codeUtils = CodeUtils.getInstance();
                //        生成bitmap
                Bitmap bitmap = codeUtils.createBitmap();
//            设置进imageView
                mCodeYzm.setImageBitmap(bitmap);
                String code = codeUtils.getCode().toLowerCase();
                System.out.println("code------------:" + code);
            }
        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account="";
                String password="";
                String codeStr="";
                String code = codeUtils.getCode().toLowerCase();
                if(edAccount.getText().toString().trim().isEmpty()){
                    showToast("请输入账号");
                }else {
                    account = edAccount.getText().toString().trim();
                }
                if( edPwd.getText().toString().trim().isEmpty()){
                    showToast("请输入密码");
                }else {
                    password = edPwd.getText().toString().trim();
                }
                if( edyzm.getText().toString().trim().toLowerCase().isEmpty()){
                    showToast("请输入验证码");
                }else {
                    codeStr = edyzm.getText().toString().trim().toLowerCase();
                    Log.e(code,codeStr);
                }
                if(codeStr.equals(code)){
                    IdentifyUser identifyUser=new IdentifyUserImpel();
                    identifyUser.identifyUser(new User(account,password));
                }else {
                    showToast("验证码输入错误");
                }
            }
        });
    }


    @Override
    public void loginCallback(boolean result) {
        if(result){
            navigationTo(this,MainActivity.class);
        }else {
            showToast("账号或密码不存在");
        }
    }
}