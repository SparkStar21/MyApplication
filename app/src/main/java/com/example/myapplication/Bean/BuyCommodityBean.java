package com.example.myapplication.Bean;

public class BuyCommodityBean {
    private int gid;
    private String gName;
    private String gDes;
    private String gPrice;
    private String gUserId;
    private String originalPrice;
    private String phoneNum;
    private String purchaserId;

    public BuyCommodityBean(){

    }

    public BuyCommodityBean(int gid, String gName, String gDes, String gPrice, String originalPrice, String phoneNum, String purchaserId) {
        this.gid = gid;
        this.gName = gName;
        this.gDes = gDes;
        this.gPrice = gPrice;
        this.originalPrice = originalPrice;
        this.phoneNum = phoneNum;
        this.purchaserId = purchaserId;
    }

    public BuyCommodityBean(int gid, String gName, String gDes, String gPrice, String gUserId, String originalPrice, String phoneNum, String purchaserId) {
        this.gid = gid;
        this.gName = gName;
        this.gDes = gDes;
        this.gPrice = gPrice;
        this.gUserId = gUserId;
        this.originalPrice = originalPrice;
        this.phoneNum = phoneNum;
        this.purchaserId = purchaserId;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public String getgDes() {
        return gDes;
    }

    public void setgDes(String gDes) {
        this.gDes = gDes;
    }

    public String getgPrice() {
        return gPrice;
    }

    public void setgPrice(String gPrice) {
        this.gPrice = gPrice;
    }

    public String getgUserId() {
        return gUserId;
    }

    public void setgUserId(String gUserId) {
        this.gUserId = gUserId;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPurchaserId() {
        return purchaserId;
    }

    public void setPurchaserId(String purchaserId) {
        this.purchaserId = purchaserId;
    }
}
