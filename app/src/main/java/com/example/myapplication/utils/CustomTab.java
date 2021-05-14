package com.example.myapplication.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myapplication.R;


public class CustomTab extends LinearLayout {
    //命名空间
    private ImageView imageView;
    private TextView textView;
    public CustomTab(Context context) {
        super(context);
    }

    public CustomTab(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
       initView(context);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomTab);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.CustomTab_icon:
                    int resource = a.getResourceId(i, 0);//对于属性值是一个资源id的，这里拿到资源id值再复制给子控件
                    imageView.setImageResource(resource);
                    imageView.setTag("unchoosed");
                    break;
                case R.styleable.CustomTab_name:
                    // 默认颜色设置为黑色
                    String title = a.getString(i);
                    textView.setText(title);
                    break;
            }
        }
    }

    public CustomTab(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomTab(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    private void initView(Context context){
        View root=View.inflate(context, R.layout.button_custom,this);
        imageView=root.findViewById(R.id.image);
        textView=root.findViewById(R.id.texts);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageView.getTag().equals("unchoosed"))
                {
                    imageView.setImageResource(R.drawable.start_choosed);
                    imageView.setTag("choosed");
                    Toast.makeText(context,"收藏成功",Toast.LENGTH_SHORT).show();
                }
                else {
                    imageView.setImageResource(R.drawable.star_unchoosed);
                    imageView.setTag("unchoosed");
                    Toast.makeText(context,"取消收藏",Toast.LENGTH_SHORT).show();
                }
                    Log.e("click","点击");
            }
        });
    }

}
