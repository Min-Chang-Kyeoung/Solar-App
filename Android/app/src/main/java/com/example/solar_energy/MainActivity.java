package com.example.solar_energy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.solar_energy.Solar_Page.PanelActivity;
import com.example.solar_energy.UnityPlayerActivity;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.reflect.Method;
import java.util.ArrayList;
import com.example.solar_energy.network.NetworkUtil;
import com.example.solar_energy.network.Config;

public class MainActivity extends AppCompatActivity {

    PieChart pieChart;
    Button btn_detail;
    ImageButton btn_ar;
    ImageButton btn_information;
    TextView txtBillingName;
    TextView txtMachineLearningName1;
    TextView  txtMachineLearningName2;
    TextView txtMachineLearningResult;
    TextView txtUsageNumPercent;
    TextView txtPresentElectricPercentWon;
    TextView txtDevelopedElecttricWon;
    TextView txtPridictBillWon;
    TextView txtDescription;
    String rnnValue;
    NetworkUtil networkUtil;
    double parseHardwareData;
    double parseSolarData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        networkUtil = new NetworkUtil(getApplicationContext());
        pieChart = (PieChart) findViewById(R.id.piechart);
        btn_information = (ImageButton)findViewById(R.id.btn_information);
        btn_detail = (Button)findViewById(R.id.btn_detail);
        btn_ar = (ImageButton)findViewById(R.id.btn_ar);
        txtUsageNumPercent = findViewById(R.id.txt_usage_num_percent);
        txtPresentElectricPercentWon = findViewById(R.id.present_electric_percent_won);
        txtDevelopedElecttricWon = findViewById(R.id.developed_electric_won);
        txtPridictBillWon = findViewById(R.id.txt_pridict_bill_won);
        txtBillingName = findViewById(R.id.txt_billing_name);
        txtMachineLearningName1 = findViewById(R.id.txt_machine_learning_name1);
        txtMachineLearningName2 = findViewById(R.id.txt_machine_learning_name2);
        txtMachineLearningResult = findViewById(R.id.txt_machine_learning_result);
        txtDescription = findViewById(R.id.txt_description2);
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
        new StringTask().execute();
    }



    class StringTask extends AsyncTask<Void, String, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            requestRnnValueToServer();
            requestSolarValueServer();
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            txtMachineLearningResult.setText(rnnValue);
            setPieChart(parseSolarData,parseHardwareData);
            //txtMachineLearningResult.notify();
        }
    }


    private void requestRnnValueToServer() {
        networkUtil.requestServer(Config.MAIN_URL + Config.GET_RNNDATA,rnnValueSuccessListener(),rnnValueErrorListener() );
    }

    private void requestSolarValueServer(){
        networkUtil.requestServer(Config.MAIN_URL + Config.GET_SOLAR_DATA,solarValueSuccessListener(),solarValueErrorListener());
    }


    private Response.Listener<JSONArray> solarValueSuccessListener() {
        return new Response.Listener<JSONArray>() {
            public void onResponse(JSONArray response) {
                JSONObject jresponse;
                JSONObject solar;
                String hardwareData;
                String solarData;

                for(int i=0;i<response.length();i++) {
                    try {
                        jresponse = response.getJSONObject(i);
                        solar = jresponse.getJSONObject("solar");
                        hardwareData = jresponse.getString("hardware");
                        solarData = solar.getString("value");
                        parseHardwareData = Double.parseDouble(hardwareData);
                        parseSolarData = Double.parseDouble(solarData);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setPieChart(parseSolarData,parseHardwareData);
                double percent = ((parseHardwareData + parseSolarData) / parseSolarData) * 100;
                int saveWon,payWon;
                saveWon = calulateWatToWon(parseSolarData * 30);
                payWon = calulateWatToWon(parseHardwareData * 30);
                double res = Math.abs(payWon-saveWon);
                txtUsageNumPercent.setText((int) percent + "%");
                txtPresentElectricPercentWon.setText((int)payWon+"원");
                txtDevelopedElecttricWon.setText((int) saveWon+"원");
                if(saveWon > payWon){
                    txtPridictBillWon.setText("+" + res);
                }
                else{
                    txtPridictBillWon.setText("-" + res);
                }
                Intent intent = getIntent();
                String name = intent.getExtras().getString("name");
                txtDescription.setText(name+" 님이 ㅓ무ㅕ" + (int)res/4000 + "잔의 커피를 마실수 있는 금액 입니다." );
            }
        };
    }

    private Response.ErrorListener solarValueErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.getMessage() != null) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                }
            }
        };
    }

    private Response.ErrorListener rnnValueErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.getMessage() != null) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    private Response.Listener<JSONArray> rnnValueSuccessListener() {
        return new Response.Listener<JSONArray>() {
            public void onResponse(JSONArray response) {
                JSONObject jresponse;
                for(int i=0;i<response.length();i++){
                    try {
                        jresponse = response.getJSONObject(i);
                        rnnValue = jresponse.getString("value");
                        txtMachineLearningResult.setText(rnnValue + "W");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        };
    }
    private int calulateWatToWon(double kwh){
        int won = 0;
        if(kwh > 200){
            won += kwh * 93;
        }
        if(kwh > 400 && kwh < 200){
            double over200kwh = kwh - 200;
            won += over200kwh *180;
        }
        if (kwh > 400){
            double over400kwh = kwh - 400;
            won += over400kwh * 280;
        }
        return won;
    }

    private void setName(){
        Intent intent = getIntent();
        String name = intent.getExtras().getString("name");
        txtBillingName.setText(name);
        txtMachineLearningName1.setText(name);
        txtMachineLearningName2.setText(name);
    }


    private void setPieChart(double power_generation, double usage){

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
