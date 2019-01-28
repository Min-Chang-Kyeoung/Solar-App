package com.example.solar_energy;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import java.util.ArrayList;
import java.util.List;


public class DetailGraph extends AppCompatActivity {
    BarChart barChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_graph);
        barChart = findViewById(R.id.barchart);
        setBarGraph(50,50,50,50,50,50);
    }
    void setBarGraph(float electric1,float electric2,float electric3
            ,float solar1,float solar2,float solar3){
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(false);
        List<BarEntry> entries = new ArrayList<>();

        entries.add(new BarEntry(1f, new float[]{electric1,solar1}));
        entries.add(new BarEntry(3f, new float[]{electric2,solar2}));
        entries.add(new BarEntry(5f, new float[]{electric3,solar3}));


        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(153,233,242));
        colors.add(Color.rgb(150,242,215));
        BarDataSet dataSet = new BarDataSet(entries, "아낄수 있었던 전기ㅠㅠ");
        dataSet.setDrawIcons(false);
        dataSet.setStackLabels(new String[]{"발전량","절약량"});
        dataSet.setColors(colors);


        BarData data = new BarData(dataSet);

        data.setBarWidth(0.9f); // set custom bar width

        barChart.setData(data);
        barChart.setFitBars(true); // make the x-axis fit exactly all bars
        barChart.invalidate();
        barChart.setDrawBorders(false);
        barChart.setDrawGridBackground(false);
        barChart.setHighlightFullBarEnabled(false);
        barChart.getDescription().setEnabled(false);

    }

}
