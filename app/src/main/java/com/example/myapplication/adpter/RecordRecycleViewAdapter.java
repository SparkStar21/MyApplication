package com.example.myapplication.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Bean.ShoppingRecordBean;
import com.example.myapplication.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecordRecycleViewAdapter extends RecyclerView.Adapter<RecordRecycleViewAdapter.ViewHolder> {

    private List<ShoppingRecordBean> list;
    private Context context;
    public RecordRecycleViewAdapter(List<ShoppingRecordBean>list,Context context){
        this.list=list;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.item_record,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.commodityName.setText(list.get(position).getName());
        holder.commodityPrice.setText("¥"+list.get(position).getPrice());
        holder.commodityDes.setText(list.get(position).getDescription());
        holder.phoneNum.setText(list.get(position).getPhoneNum());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.commodityName)
        public TextView commodityName;
        @BindView(R.id.commodityPrice)
        public TextView commodityPrice;
        @BindView(R.id.commodityDes)
        public TextView commodityDes;
        @BindView(R.id.phoneNum)
        public TextView phoneNum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
