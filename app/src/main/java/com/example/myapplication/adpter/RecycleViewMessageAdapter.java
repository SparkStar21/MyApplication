package com.example.myapplication.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Bean.MsgBean;
import com.example.myapplication.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecycleViewMessageAdapter extends RecyclerView.Adapter<RecycleViewMessageAdapter.MessageHolder> {
    String LOG="RecycleViewMessageAdapter";
    private List <MsgBean>list;
    private Context context;
    private OnItemOnClick onItemOnClickListener;
    public RecycleViewMessageAdapter(List <MsgBean> list, Context context){
        this.list=list;
        this.context=context;
    }

    public void setOnItemOnClickListener(OnItemOnClick onItemOnClickListener){
        this.onItemOnClickListener=onItemOnClickListener;
    }
    public interface OnItemOnClick{
        void onItemOnClick(View view,int position);
    }
    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(parent.getContext(), R.layout.item_msg,null);
        MessageHolder messageHolder =new MessageHolder(view);
        return messageHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position) {
        Glide.with(context).load(R.mipmap.head_man).into(holder.personIcon);
        holder.contact.setText(list.get(position).getFrom());
        if(!(list.get(position).getUnreadCount() ==0)){
            holder.unreadWarning.setText(list.get(position).getUnreadCount());
            holder.unreadWarning.setVisibility(View.VISIBLE);
        }
        holder.lastMessageContent.setText(list.get(position).getTxt());
        holder.time.setText(list.get(position).getTime());
        if(onItemOnClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.unreadWarning.setVisibility(View.GONE);
                    onItemOnClickListener.onItemOnClick(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MessageHolder extends RecyclerView.ViewHolder{
        @Nullable
        @BindView(R.id.personIcon)
        public ImageView personIcon;
        @Nullable
        @BindView(R.id.contact)
        public TextView contact;
        @Nullable
        @BindView(R.id.time)
        public TextView time;
        @Nullable
        @BindView(R.id.lastMessageContent)
        public TextView lastMessageContent;
        @Nullable
        @BindView(R.id.unreadWarning)
        public TextView unreadWarning;
        public MessageHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
