package com.example.myapplication.Bean;

public class User {
    private String username;
    private String usercode;
    private int usericon;
    private String sex;
    private int uid;
    private String phone;

    public User(){

    }

    public User(String username, String usercode) {
        this.username = username;
        this.usercode = usercode;
    }

    public User(int uid,String username, String usercode, int usericon, String sex) {
        this.username = username;
        this.usercode = usercode;
        this.usericon=usericon;
        this.sex=sex;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getUsericon() {
        return usericon;
    }

    public void setUsericon(int usericon) {
        this.usericon = usericon;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getUsercode() {
        return usercode;
    }

    public String getUsername() {
        return username;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
