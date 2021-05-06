package com.example.myapplication.datas;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myapplication.Bean.BuyCommodityBean;
import com.example.myapplication.Bean.CommodityBean;
import com.example.myapplication.Bean.ShoppingRecordBean;
import com.example.myapplication.utils.SqlServer;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DataCollection {
    private static SqlServer  sqlServer=new SqlServer("10.0.2.2", "SecondHand_Market", "SA", "meng1033");
    public DataCollection(){
        sqlServer.connect();
    }

    public static void getOne(Observer o, String str){
        Observable observable=new Observable() {
            @Override
            protected void subscribeActual(io.reactivex.Observer observer) {
                observer.onNext(sqlServer.getUser(str));
            }
        };
        subscribe(o,observable);
    }

    public static void getAllContacts(Observer observer){
        Observable observable=new Observable() {
            @Override
            protected void subscribeActual(Observer observer) {
                try {
                    observer.onNext(EMClient.getInstance().contactManager().getAllContactsFromServer());
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        };
        subscribe(observer,observable);
    }

    public static void uploadCommodity(Observer o,CommodityBean commodityBean){
        Observable observable= new Observable() {
            @Override
            protected void subscribeActual(Observer observer) {
                sqlServer.getNum(String.format("INSERT INTO [dbo].[goods]([gimage],[gname],[gnumber],[gdes],[gprice],[gamount],[guser],[originalprice],[phoneNum])VALUES('%s','%s','%s','%s','%s','%s','%s','%s','%s')",commodityBean.getPicturePath(),commodityBean.getName(),commodityBean.getLocation(),commodityBean.getDescription(),commodityBean.getPrice(),commodityBean.getAmount(),commodityBean.getUser(),commodityBean.getOriginPrice(),commodityBean.getPhone()));
                try {
                    if(commodityBean.getBitmaps().size()!=0)
                    socketUploadImage(commodityBean.getBitmaps(),commodityBean.getPicturePath());
                    String commodityId=String.format("SELECT gid FROM goods WHERE gdes ='%s'",commodityBean.getDescription());
                    String sellUserId=String.format("SELECT uid FROM users WHERE Name ='%s'",commodityBean.getUser());
                    sqlServer.getNum(String.format("INSERT INTO table_sell (SellUserId,CommodityId)values ('%s','%s')",sellUserId,commodityId));
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        subscribe(o,observable);
    }

    public static void getAllCommodity(Observer o,String str){
        Observable observable=new Observable() {
            @Override
            protected void subscribeActual(Observer observer) {
                List <CommodityBean> list=sqlServer.getList(str);
                try {
                    for(int i=0;i<list.size();i++)
                    list.get(i).setBitmaps(socketOneImage((list.get(i).getPicturePath())));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                observer.onNext(list);
            }
        };
        subscribe(o,observable);
    }

    public static void getDetail(Observer o,String sql){
        Observable observable=new Observable() {
            @Override
            protected void subscribeActual(Observer observer) {
                CommodityBean commodityBean=sqlServer.getCommodity(sql);
                try {
                    commodityBean.setBitmaps(socketImages(commodityBean.getPicturePath()));
                    observer.onNext(commodityBean);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        subscribe(o,observable);
    }

    public static void insertData(Observer observer,String sql){
        Observable observable=new Observable() {
            @Override
            protected void subscribeActual(Observer observer) {
                observer.onNext(sqlServer.getNum(sql));
            }
        };
        subscribe(observer,observable);
    }

    public static void buyCommodity(Observer observer,BuyCommodityBean buyCommodityBean){
        Observable observable=new Observable() {
            @Override
            protected void subscribeActual(Observer observer) {
                sqlServer.getNum(String.format("INSERT INTO soldgoods (gid,gname,gdes,gprice,guserId,originalprice,phoneNum,purchaserId)values ('%d','%s','%s','%s','%s','%s','%s','%s')",buyCommodityBean.getGid(),buyCommodityBean.getgName(),buyCommodityBean.getgDes(),buyCommodityBean.getgPrice(),buyCommodityBean.getgUserId(),buyCommodityBean.getOriginalPrice(),buyCommodityBean.getPhoneNum(),buyCommodityBean.getPurchaserId()));
                sqlServer.getNum(String.format("DELETE From goods where gid = '%d'",buyCommodityBean.getGid()));
            }
        };
        subscribe(observer,observable);
    }

    public static void getImages(Observer o,String path){
        Observable observable=new Observable() {
            @Override
            protected void subscribeActual(Observer observer) {

            }
        };
    }

    public static void getCharityNewsFormBrowser(Observer observer){
        Observable observable=new Observable() {
            @Override
            protected void subscribeActual(Observer observer) {

                observer.onNext("");
            }
        };
        subscribe(observer,observable);
    }

    public static void getAllRecord(Observer observer,String user,String type){
        Observable observable=new Observable() {
            @Override
            protected void subscribeActual(Observer observer) {
                List <ShoppingRecordBean>list = null;
                if(type=="table_sell"){
                    list=sqlServer.getAllRecord(String.format("SELECT gname,purchaserId,gdes,phoneNum,gprice FROM soldgoods Where guserId = '%s'",user));
                }else if (type=="table_buy"){
                    list=sqlServer.getAllRecord(String.format("SELECT gname,guserId,gdes,phoneNum,gprice FROM soldgoods Where purchaserId = '%s'",user));
                }
                observer.onNext(list);

            }
        };
        subscribe(observer,observable);
    }



    private static void socketUploadImage(List <Bitmap> list,String path) throws IOException, InterruptedException {
        byte [] de;
        Socket socket=new Socket("10.0.2.2",1122);
        DataOutputStream dataOutputStream=new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeUTF("upload");
        dataOutputStream.writeUTF(path);
        dataOutputStream.write(list.size());
        dataOutputStream.flush();
        for(int i=0;i<list.size();i++){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            list.get(i).compress(Bitmap.CompressFormat.PNG, 50, baos);
            de=baos.toByteArray();
            dataOutputStream.write(de);
            dataOutputStream.close();
            socket.close();
            socket=new Socket("10.0.2.2",1122);
            dataOutputStream=new DataOutputStream(socket.getOutputStream());
        }
        dataOutputStream.close();
        socket.close();
    }

    private static List<Bitmap> socketOneImage(String path) throws IOException, InterruptedException {
        List<Bitmap> list=new ArrayList<>();
        byte [] de;
        Socket socket=new Socket("10.0.2.2",1122);
        DataOutputStream dataOutputStream=new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeUTF("downloadOne");
        dataOutputStream.writeUTF(path);
        socket.close();
        dataOutputStream.close();
        socket=new Socket("10.0.2.2",1122);
        DataInputStream dataInputStream=new DataInputStream(socket.getInputStream());
        Thread.sleep(10);
        de=new byte[dataInputStream.available()];
        dataInputStream.read(de);
        list.add(BitmapFactory.decodeByteArray(de,0,de.length));
        socket.close();
        dataInputStream.close();
        return list;
    }

    private static List<Bitmap> socketImages(String path) throws IOException, InterruptedException {
        List <Bitmap> list=new ArrayList<>();
        byte [] de;
        Socket socket=new Socket("10.0.2.2",1122);
        DataOutputStream dataOutputStream=new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeUTF("downloadAll");
        dataOutputStream.writeUTF(path);
        socket.close();
        dataOutputStream.close();
        socket=new Socket("10.0.2.2",1122);
        DataInputStream dataInputStream=new DataInputStream(socket.getInputStream());
        int count=dataInputStream.read();
        for(int i=0;i<count;i++){
            Thread.sleep(20);
            de=new byte[dataInputStream.available()];
            dataInputStream.read(de);
            System.out.println(de.length);
            list.add(BitmapFactory.decodeByteArray(de, 0, de.length));
            socket.close();
            dataInputStream.close();
            socket=new Socket("10.0.2.2",1122);
            dataInputStream=new DataInputStream(socket.getInputStream());
        }
        dataOutputStream.close();
        dataInputStream.close();
        socket.close();
        return list;
    }

    private static void subscribe(Observer s,Observable o){
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())//子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .retry(0)//请求失败重连次数
                .subscribe(s);
    }

}
