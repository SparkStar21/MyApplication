package com.example.myapplication.adpter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Bean.CharityNewsBean;
import com.example.myapplication.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SlideHowFragmentAdapter extends RecyclerView.Adapter<SlideHowFragmentAdapter.ViewHolder> {
    private OnItemClick onItemClick;
    private Context context;
    private List <CharityNewsBean> data;
    public SlideHowFragmentAdapter(Context context,List<CharityNewsBean> list){
        this.context=context;
        this.data=list;
    }

    public interface OnItemClick{
        void onItemClick(View view,int position);
    }
    public void setOnItemClick(OnItemClick onItemClick){
        this.onItemClick=onItemClick;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root=View.inflate(context, R.layout.item_slidehow,null);
        ViewHolder viewHolder=new ViewHolder(root);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(data.get(position).getTitle());
        holder.content.setText(data.get(position).getContent());
        if(onItemClick!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick.onItemClick(holder.itemView,position);
                }
            });
        }
        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:{
                        holder.itemView.setBackgroundColor(R.color.LightGrey);
                        holder.title.setTextColor(Color.RED);
                       break;
                    }
                    case MotionEvent.ACTION_CANCEL:{
                        holder.itemView.setBackgroundResource(R.drawable.milkwhite);
                        holder.title.setTextColor(Color.BLACK);
                        break;
                    }
                    default: Log.e("SlideHowFragmentAdapter", String.valueOf(event));break;
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.title)
        public TextView title;
        @BindView(R.id.content)
        public TextView content;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
