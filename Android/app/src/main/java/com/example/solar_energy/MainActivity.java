package com.example.solar_energy;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pieChart = (PieChart) findViewById(R.id.piechart);
        setPieChart(50,50);
    }

    void setPieChart(double power_generation, double usage){

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(30f);
        pieChart.setHoleRadius(50f);
        pieChart.setDragDecelerationFrictionCoef(0.95f);;

        pieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValues = new ArrayList<PieEntry>();

        yValues.add(new PieEntry((float) power_generation,"발전량"));
        yValues.add(new PieEntry((float) usage,"사용량"));



        pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic); //애니메이션

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(153,233,242));
        colors.add(Color.rgb(150,242,215));

        PieDataSet dataSet = new PieDataSet(yValues," ");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(colors);

        PieData data = new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        pieChart.setData(data);
    }

}
