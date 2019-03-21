package com.example.solar_energy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.solar_energy.Solar_Page.PanelActivity;
import com.example.solar_energy.UnityPlayerActivity;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    PieChart pieChart;
    Button btn_detail;
    ImageButton btn_ar;
    ImageButton btn_information;
    TextView txtBillingName;
    TextView txtMachineLearningName1;
    TextView  txtMachineLearningName2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pieChart = (PieChart) findViewById(R.id.piechart);
        btn_information = (ImageButton)findViewById(R.id.btn_information);
        btn_detail = (Button)findViewById(R.id.btn_detail);
        btn_ar = (ImageButton)findViewById(R.id.btn_ar);

        txtBillingName = findViewById(R.id.txt_billing_name);
        txtMachineLearningName1 = findViewById(R.id.txt_machine_learning_name1);
        txtMachineLearningName2 = findViewById(R.id.txt_machine_learning_name2);
        setName();

        btn_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PanelActivity.class);
                startActivity(intent);
            }
        });

        btn_detail.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        DetailGraph.class);
                startActivity(intent);
            }
        });

        btn_ar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),UnityPlayerActivity.class);
                startActivity(intent);
            }
        });
        setPieChart(50,50);
    }

    void setName(){
        Intent intent = getIntent();
        String name = intent.getExtras().getString("name");
        txtBillingName.setText(name);
        txtMachineLearningName1.setText(name);
        txtMachineLearningName2.setText(name);
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

    @Override
    protected void onStop(){
        super.onStop();
         SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
         SharedPreferences.Editor editor = preferences.edit();
         editor.clear();
         editor.commit();
    }

}
