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
import com.hyphenate.chat.EMClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecycleViewChatContentAdapter extends RecyclerView.Adapter<RecycleViewChatContentAdapter.ViewHolder> {
    private List<MsgBean>list;
    private Context context;

    public RecycleViewChatContentAdapter(List<MsgBean>list, Context context){
        this.list=list;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if(viewType==0){
            view=View.inflate(parent.getContext(), R.layout.item_mymessage,null);
        }else if(viewType==1) {
            view=View.inflate(parent.getContext(),R.layout.item_opposemessage,null);
        }
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(R.mipmap.head_man).into(holder.imageView);
        holder.chatContent.setText(list.get(position).getTxt());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(list.get(position).getFrom().equals(EMClient.getInstance().getCurrentUser()))
        {
            return 1;
        }
        else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @Nullable
        @BindView(R.id.chat_content)
        public TextView chatContent;
        @Nullable
        @BindView(R.id.image_icon)
        public ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
