package com.example.myapplication.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.utils.LineChartDraw;
import com.github.mikephil.charting.charts.LineChart;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnalysisFragment extends Fragment {
    @Nullable
    @BindView(R.id.sellingChart)
    public LineChart sellingChart;
    @Nullable
    @BindView(R.id.analysis_textViewSum)
    public TextView textViewSum;
    @Nullable
    @BindView(R.id.analysis_textViewSingleMonth)
    public TextView textViewSingleMonth;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        ButterKnife.bind(this,root);
        LineChartDraw lineChartDraw=new LineChartDraw(sellingChart,getContext());
        List <Float>list=new ArrayList<>();
        Random random=new Random();
        ;
        for(int i=0;i<12;i++){
            list.add((float) random.nextInt(100));
        }
        lineChartDraw.linechart_draw(list,list.size());
        return root;
    }



    private void initPresenter(){

    }
}