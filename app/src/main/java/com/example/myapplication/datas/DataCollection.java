package com.example.myapplication.datas;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myapplication.Bean.CommodityBean;
import com.example.myapplication.utils.SqlServer;

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
                observer.onNext(sqlServer.getString(str));
            }
        };
        subscribe(o,observable);
    }

    public static void getAllCommodity(Observer o,String str){
        Observable observable=new Observable() {
            @Override
            protected void subscribeActual(Observer observer) {
                List <CommodityBean> list=sqlServer.getList(str);
                for (CommodityBean c:list){
                    if(!c.getPicturePath().isEmpty()){
                        try {
                            c.setBitmaps(socketImage(c.getPicturePath()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                observer.onNext(list);
            }
        };
        subscribe(o,observable);
    }


    private static void subscribe(Observer s,Observable o){
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())//子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .retry(0)//请求失败重连次数
                .subscribe(s);
    }

    private static List<Bitmap> socketImage(String path) throws IOException {
        List <Bitmap> list=new ArrayList<>();
        Socket socket=new Socket("10.0.2.2",1122);
        DataOutputStream dataOutputStream=new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeUTF("start");
        dataOutputStream.flush();
        dataOutputStream.writeUTF(path);
        dataOutputStream.flush();
        dataOutputStream.close();
        DataInputStream dataInputStream=new DataInputStream(socket.getInputStream());
        int count=dataInputStream.read();
        for(int i=0;i<count;i++){
            byte [] de=new byte[dataInputStream.available()];
            dataInputStream.read(de);
            dataInputStream.reset();
            list.add(BitmapFactory.decodeByteArray(de, 0, de.length));
        }
        dataOutputStream.writeUTF("end");
        return list;
    }

}
