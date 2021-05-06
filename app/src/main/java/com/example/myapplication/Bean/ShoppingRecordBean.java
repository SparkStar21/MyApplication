package com.example.myapplication.Bean;

public class ShoppingRecordBean {
    private String name;
    private String user;
    private String description;
    private String phoneNum;
    private String price;

    public ShoppingRecordBean(){

    }

    public ShoppingRecordBean(String name, String user, String description, String phoneNum, String price) {
        this.name = name;
        this.user = user;
        this.description = description;
        this.phoneNum = phoneNum;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
