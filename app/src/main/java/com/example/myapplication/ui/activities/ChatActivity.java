package com.example.myapplication.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Bean.MsgBean;
import com.example.myapplication.R;
import com.example.myapplication.adpter.RecycleViewChatContentAdapter;
import com.example.myapplication.base.BaseActivity;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatActivity extends BaseActivity implements View.OnClickListener {
    @Nullable
    @BindView(R.id.return_return)
    ImageView finish;
    @Nullable
    @BindView(R.id.title)
    public TextView title;
    @Nullable
    @BindView(R.id.recycleViewChatContent)
    public RecyclerView recycleViewChatContent;
    @Nullable
    @BindView(R.id.message)
    public EditText message;
    @Nullable
    @BindView(R.id.sendMessage)
    TextView sendMessage;
    private List<MsgBean>list=new ArrayList<>();
    private String user;
    private RecycleViewChatContentAdapter recycleViewChatContentAdapter;
    private EMMessageListener msgListener;
    private List <EMMessage> messages;
    private EMMessage emMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatcontent);
        ButterKnife.bind(this);
        Intent intent=this.getIntent();
        int userId=intent.getIntExtra("user",0);
            if(userId!=0){
                EMMessage emMessage=EMMessage.createTxtSendMessage("您好！".trim(),user);
                EMClient.getInstance().chatManager().sendMessage(emMessage);
            }else {
                user=intent.getStringExtra("friend");
                title.setText(user);
                initData();
                initButton();
            }
    }

    private void initButton(){
        finish.setOnClickListener(this);
        sendMessage.setOnClickListener(this);
        msgListener = new EMMessageListener() {

            @Override
            public void onMessageReceived(List<EMMessage> messages) {
                //收到消息
                if(messages.get(0).getFrom().equals(user)){
                    EMTextMessageBody emMessageBody=(EMTextMessageBody)messages.get(0).getBody();
                    list.add(new MsgBean(emMessageBody.getMessage(),user));
                }
            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> messages) {
                //收到透传消息
            }

            @Override
            public void onMessageRead(List<EMMessage> messages) {
                //收到已读回执
            }

            @Override
            public void onMessageDelivered(List<EMMessage> message) {
                //收到已送达回执
            }
            @Override
            public void onMessageRecalled(List<EMMessage> messages) {
                //消息被撤回
            }

            @Override
            public void onMessageChanged(EMMessage message, Object change) {
                //消息状态变动
            }
        };
        EMClient.getInstance().chatManager().addMessageListener(msgListener);
    }


    private void initData(){
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(user);
        //获取此会话的所有消息
        emMessage=conversation.getLastMessage();
        messages=conversation.loadMoreMsgFromDB(emMessage.getMsgId(),20);
        messages.add(emMessage);
        Log.e("", String.valueOf(conversation.getAllMessages().size()));
        Date date;
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
        for(int i=0;i<messages.size();i++){
            Log.e(String.valueOf(i), String.valueOf(messages.get(i).getBody()));
            date=new Date(messages.get(i).getMsgTime());
            EMTextMessageBody emTextMessageBody= (EMTextMessageBody) messages.get(i).getBody();
            list.add(new MsgBean(messages.get(i).getFrom(),emTextMessageBody.getMessage(),simpleDateFormat.format(date)));
        }
        recycleViewChatContentAdapter=new RecycleViewChatContentAdapter(list,this);
        recycleViewChatContent.setLayoutManager(new GridLayoutManager(this,1));
        recycleViewChatContent.setAdapter(recycleViewChatContentAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.return_return:{
                finish();
                break;
            }
            case R.id.sendMessage:{
                EMMessage emMessage=EMMessage.createTxtSendMessage(message.getText().toString().trim(),user);
                EMClient.getInstance().chatManager().sendMessage(emMessage);
                list.add(new MsgBean(message.getText().toString().trim(),EMClient.getInstance().getCurrentUser()));
                recycleViewChatContentAdapter.notifyDataSetChanged();
                message.setText("");
                break;
            }
            default:break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EMClient.getInstance().chatManager().removeMessageListener(msgListener);
    }
}
