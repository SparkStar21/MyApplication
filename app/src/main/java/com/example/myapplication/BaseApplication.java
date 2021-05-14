package com.example.myapplication;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.multidex.MultiDex;

import com.example.myapplication.Bean.User;
import com.example.myapplication.datas.DataCollection;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;

import java.util.Iterator;
import java.util.List;

public class BaseApplication extends Application {
    //上下文菜单
    private static Context context;
    //记录是否已经被初始化
    private boolean isInit = false;
    @Override
    public void onCreate() {
        super.onCreate();
        this.context=this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                DataCollection dataCollection=new DataCollection();
            }
        }).start();
        //方法数大于索引数，必须适应多方法
        MultiDex.install(this);
        // 初始化环信SDK
        initEasemob();
    }

    public static void saveUser(User user){
        SharedPreferences.Editor editor = context.getSharedPreferences("currentUser", MODE_PRIVATE).edit();
        //第一个是关键词，第二个是关键词对应的内容
        editor.putString("username",user.getUsername());
        editor.putString("usercode",user.getUsercode());
        editor.putInt("usericon",user.getUsericon());
        editor.putInt("uid",user.getUid());
        editor.putString("phone",user.getPhone());
        editor.apply();
        editor.commit();
    }

    public static User getUser(){
        SharedPreferences getBeforeTopic = context.getSharedPreferences("currentUser", MODE_PRIVATE);
        //第一个是获取关键词对应的内容，第二个是没获取到的话使用的默认值
        String username = getBeforeTopic.getString("username","null");
        String usercode=getBeforeTopic.getString("usercode","null");
        String phone=getBeforeTopic.getString("phone","null");
        int usericon=getBeforeTopic.getInt("usericon",0);
        int userId=getBeforeTopic.getInt("uid",1);
        User user=new User(userId,username,usercode,usericon,phone);
        return user;
    }


    public static int getIcon(String i){
        switch (i){
            case "1":{
                return R.mipmap.head_man;
            }
            case "2":{
                return R.mipmap.head_woman1;
            }
            case "3":{
                return R.mipmap.head_woman;
            }
            case "4":{
                return R.drawable.ic___;
            }
            case "5":{
                return R.drawable.start_choosed;
            }
            default:return R.mipmap.head_man;
        }
    }


    private void initEasemob() {
        // 获取当前进程 id 并取得进程名
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
        /**
         * 如果app启用了远程的service，此application:onCreate会被调用2次
         * 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
         * 默认的app会在以包名为默认的process name下运行，如果查到的process name不是app的process name就立即返回
         */
        if (processAppName == null || !processAppName.equalsIgnoreCase(context.getPackageName())) {
            // 则此application的onCreate 是被service 调用的，直接返回
            return;
        }
        if (isInit) {
            return;
        }

        // 调用初始化方法初始化sdk
        EMClient.getInstance().init(context, initOptions());
        // 设置开启debug模式
        EMClient.getInstance().setDebugMode(true);

        // 设置初始化已经完成
        isInit = true;
    }

    /**
     * SDK初始化的一些配置
     * 关于 EMOptions 可以参考官方的 API 文档
     * http://www.easemob.com/apidoc/android/chat3.0/classcom_1_1hyphenate_1_1chat_1_1_e_m_options.html
     */
    private EMOptions initOptions() {

        EMOptions options = new EMOptions();
        // 设置Appkey，如果配置文件已经配置，这里可以不用设置
        // options.setAppKey("lzan13#hxsdkdemo");
        // 设置自动登录
        options.setAutoLogin(true);
        // 设置是否需要发送已读回执
        options.setRequireAck(true);
        //设置是否自动登录
        options.setAutoLogin(true);
        // 设置是否需要发送回执，
        options.setRequireDeliveryAck(true);
        // 设置是否根据服务器时间排序，默认是true
        options.setSortMessageByServerTime(true);
        // 收到好友申请是否自动同意，如果是自动同意就不会收到好友请求的回调，因为sdk会自动处理，默认为true
        options.setAcceptInvitationAlways(false);
        // 设置是否自动接收加群邀请，如果设置了当收到群邀请会自动同意加入
        options.setAutoAcceptGroupInvitation(false);
        // 设置（主动或被动）退出群组时，是否删除群聊聊天记录
        options.setDeleteMessagesAsExitGroup(false);
        // 设置是否允许聊天室的Owner 离开并删除聊天室的会话
        options.allowChatroomOwnerLeave(true);
        // 设置google GCM推送id，国内可以不用设置
        // options.setGCMNumber(MLConstants.ML_GCM_NUMBER);
        // 设置集成小米推送的appid和appkey
        // options.setMipushConfig(MLConstants.ML_MI_APP_ID, MLConstants.ML_MI_APP_KEY);

        return options;
    }

    /**
     * 根据Pid获取当前进程的名字，一般就是当前app的包名
     *
     * @param pid 进程的id
     * @return 返回进程的名字
     */
    private String getAppName(int pid) {
        String processName = null;
        ActivityManager activityManager =
                (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List list = activityManager.getRunningAppProcesses();
        Iterator i = list.iterator();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info =
                    (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pid) {
                    // 根据进程的信息获取当前进程的名字
                    processName = info.processName;
                    // 返回当前进程名
                    return processName;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 没有匹配的项，返回为null
        return null;
    }


}
