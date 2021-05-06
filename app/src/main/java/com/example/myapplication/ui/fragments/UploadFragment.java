package com.example.myapplication.ui.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.myapplication.BaseApplication;
import com.example.myapplication.Bean.CommodityBean;
import com.example.myapplication.Presenters.UpLoad;
import com.example.myapplication.Presenters.impel.UploadImpel;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

public class UploadFragment extends BaseFragment implements View.OnClickListener {
    @Nullable
    @BindView(R.id.commodityName)
    public EditText commodityName;
    @Nullable
    @BindView(R.id.originalPrice)
    public EditText originalPrice;
    @Nullable
    @BindView(R.id.currentPrice)
    public EditText currentPrice;
    @Nullable
    @BindView(R.id.commodityDes)
    public EditText commodityDes;
    @Nullable
    @BindView(R.id.buildingNum)
    public EditText buildingNum;
    @Nullable
    @BindView(R.id.submit)
    public Button submit;
    @Nullable
    @BindView(R.id.finish)
    public Button finish;
    @Nullable
    @BindView(R.id.choosePicture)
    public ImageView choosePicture;
    @Nullable
    @BindView(R.id.imagelyaout)
    public LinearLayout imageLayout;

    private List <Bitmap> list=new ArrayList<>();

    private ImageView imageView;
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,root);
        initSet();
        return root;
    }

    private void initSet(){
        choosePicture.setOnClickListener(this);
        submit.setOnClickListener(this);
        finish.setOnClickListener(this);
    }

    private void initWidget(){
        commodityName.setText("");
        originalPrice.setText("");
        currentPrice.setText("");
        commodityDes.setText("");
        buildingNum.setText("");
        imageLayout.removeAllViews();
    }


    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //请求权限
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1);
        } else {
            //调用
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivityForResult(intent, 8888);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 8888 && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            list.add(photo);
            imageView=new ImageView(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(300, 300);//两个400分别为添加图片的大小
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            View view=new View(getActivity());
            view.setX(10.0f);
            Glide.with(getActivity()).load(photo).into(imageView);
            imageLayout.addView(imageView);
        }
    }

    private void uploadData(){
        CommodityBean commodityBean=new CommodityBean();
        commodityBean.setUser(BaseApplication.getUser().getUsername());
        Log.e("",commodityBean.getUser());
        commodityBean.setPhone("1562023832");
        commodityBean.setAmount("1");
        if(commodityName.getText().toString().isEmpty()){
            showToast(getActivity(),"商品名称为空");
            return;
        }else {
            commodityBean.setName(commodityName.getText().toString().trim());
        }
        if(originalPrice.getText().toString().isEmpty()){
            showToast(getActivity(),"请输入商品价格");
            return;
        }else {
            commodityBean.setOriginPrice(originalPrice.getText().toString().trim());
        }
        if(currentPrice.getText().toString().isEmpty()){
            showToast(getActivity(),"请输入出售价格");
            return;
        }else {
            commodityBean.setPrice(currentPrice.getText().toString().trim());
        }
        if(commodityDes.getText().toString().isEmpty()){
            showToast(getActivity(),"请描述商品");
            return;
        }else {
            commodityBean.setDescription(commodityDes.getText().toString().trim());
        }
        if(buildingNum.getText().toString().isEmpty()){
            showToast(getActivity(),"请输入宿舍楼号");
            Toast.makeText(getActivity(),"aaa",Toast.LENGTH_SHORT).show();
            return;
        }else {
            commodityBean.setLocation(buildingNum.getText().toString().trim());
        }
        if(list.size()==0){
            commodityBean.setBitmaps(new ArrayList<Bitmap>());
        }else {
            commodityBean.setBitmaps(list);
            commodityBean.setPicturePath("C:\\Users\\dell\\Desktop\\毕业设计\\picture_secondhand\\"+commodityBean.getUser()+"\\"+commodityBean.getName());
            initWidget();
        }
        UpLoad upLoad=new UploadImpel();
        upLoad.upload(commodityBean);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.choosePicture:{
                requestPermission();
                break;
            }
            case R.id.submit:{
                uploadData();
                break;
            }
            case R.id.finish:{

                break;
            }
            default:break;
        }

    }
}