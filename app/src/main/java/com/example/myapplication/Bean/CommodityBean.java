package com.example.myapplication.Bean;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.List;

public class CommodityBean implements Serializable {
    private int gid;				//商品ID
    private String picturePath;			//商品存储路径
    private String name;			//商品名称
    private String location;			//商品所在宿舍楼
    private String description;			//商品描述
    private String price;				//商品现价
    private String amount;			//商品数量
    private long registerTime;			//商品上传时间
    private String user;				//所属用户
    private String originPrice;			//商品原价
    private long updateTime;			//最后编辑时间
    private List<Bitmap> bitmaps;		//商品图片

    public CommodityBean(){


    }

    public CommodityBean(int gid, String picturePath, String name, String location, String description, String price,String user, String originPrice) {
        this.gid = gid;
        this.picturePath = picturePath;
        this.name = name;
        this.location = location;
        this.description = description;
        this.price = price;
        this.user = user;
        this.originPrice = originPrice;
    }

    public CommodityBean(int gid, String picturePath, String name, String location, String description, String price, String amount, long registerTime, String user, String originPrice, long updateTime, List<Bitmap> bitmaps) {
        this.gid = gid;
        this.picturePath = picturePath;
        this.name = name;
        this.location = location;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.registerTime = registerTime;
        this.user = user;
        this.originPrice = originPrice;
        this.updateTime = updateTime;
        this.bitmaps = bitmaps;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public long getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(long registerTime) {
        this.registerTime = registerTime;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(String originPrice) {
        this.originPrice = originPrice;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public List<Bitmap> getBitmaps() {
        return bitmaps;
    }

    public void setBitmaps(List<Bitmap> bitmaps) {
        this.bitmaps = bitmaps;
    }
}
