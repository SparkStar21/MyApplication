package com.example.myapplication.Bean;

import java.io.Serializable;

public class MsgBean implements Serializable {
    private String from;
    private String to;
    private String txt;
    private String time;
    private int unreadCount;
    public MsgBean(){


    }
    public MsgBean(String txt,String from){
        this.txt=txt;
        this.from=from;
    }

    public MsgBean(String from,String txt,String time){
        this.from=from;
        this.txt=txt;
        this.time=time;
    }

    public MsgBean(String from, String to, String txt,String time,int unreadCount) {
        this.from = from;
        this.to = to;
        this.txt = txt;
        this.time=time;
        this.unreadCount=unreadCount;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
    }
}
