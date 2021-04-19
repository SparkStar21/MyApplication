package com.example.myapplication.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Bean.CommodityBean;
import com.example.myapplication.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {


    private List<CommodityBean>list;
    private Context context;
    public RecycleViewAdapter(List<CommodityBean>list,Context context){
        this.list=list;
        this.context=context;
    }
    private OnItemClick onItemClick;

    public void setOnItemClickListener(OnItemClick onItemClickListener){
        this.onItemClick=onItemClickListener;
    }

    public interface OnItemClick{
        void onItemClickListener(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.commodityitem, null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getBitmaps().get(0)).into(holder.commodityImage);
        holder.commodityName.setText(list.get(position).getName());
        holder.commodityDes.setText(list.get(position).getDescription());
        holder.commodityPrice.setText("¥"+list.get(position).getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClickListener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.commodityImage)
        public ImageView commodityImage;
        @BindView(R.id.commodityName)
        public TextView commodityName;
        @BindView(R.id.commodityDes)
        public TextView commodityDes;
        @BindView(R.id.commodityPrice)
        public TextView commodityPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
