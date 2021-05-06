package com.example.myapplication.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Bean.MsgBean;
import com.example.myapplication.Presenters.AllContacts;
import com.example.myapplication.Presenters.impel.AllContactsImpel;
import com.example.myapplication.R;
import com.example.myapplication.adpter.RecycleViewMessageAdapter;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.ui.activities.ChatActivity;
import com.example.myapplication.ui.callbacks.Contacts;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageFragment extends BaseFragment implements Contacts {
    @Nullable
    @BindView(R.id.messageRecycleView)
    RecyclerView messageRecycleView;

    private List<MsgBean> msgBeans = new ArrayList<>();
    private EMMessage emMessage;
    private MsgBean msgBean;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_message, container, false);
        ButterKnife.bind(this, root);
        initPresenter();
        return root;
    }

    private void initPresenter() {
        AllContacts allContacts = new AllContactsImpel();
        allContacts.getAllContacts();
        allContacts.register(this);
    }

    private void initSet(){

    }


    @Override
    public void loadData(List<String> contacts) {
        for(int i=0;i<contacts.size();i++){
            EMMessage emMessage=EMMessage.createTxtSendMessage("您好！".trim(),contacts.get(i));
            EMClient.getInstance().chatManager().sendMessage(emMessage);
        }
        System.out.println("登录人员" + contacts.size()+"名");
        Date date;
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm");
        Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
        for (int i = 0; i < contacts.size(); i++) {
            emMessage = conversations.get(contacts.get(i)).getLastMessage();
            date=new Date(emMessage.getMsgTime());
            EMTextMessageBody emTextMessageBody= (EMTextMessageBody) emMessage.getBody();
            msgBean = new MsgBean(emMessage.getUserName(), emMessage.getTo(),emTextMessageBody.getMessage(),simpleDateFormat.format(date),conversations.get(contacts.get(0)).getUnreadMsgCount());
            msgBeans.add(msgBean);
        }
        RecycleViewMessageAdapter recycleViewMessageAdapter =new RecycleViewMessageAdapter(msgBeans,getActivity());
        messageRecycleView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        messageRecycleView.setAdapter(recycleViewMessageAdapter);
        recycleViewMessageAdapter.setOnItemOnClickListener(new RecycleViewMessageAdapter.OnItemOnClick() {
            @Override
            public void onItemOnClick(View view, int position) {
                Intent intent=new Intent(getActivity(), ChatActivity.class);
                conversations.get(contacts.get(position)).markAllMessagesAsRead();
                if(msgBeans.get(position).getFrom().equals(EMClient.getInstance().getCurrentUser())){
                    intent.putExtra("friend",msgBeans.get(position).getTo());
                }else {
                    intent.putExtra("friend",msgBeans.get(position).getFrom());
                }
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
