package com.example.solar_energy.Solar_Page;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.solar_energy.R;
import com.example.solar_energy.network.Config;
import com.example.solar_energy.network.NetworkUtil;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailPanelActivity extends AppCompatActivity {
    private NetworkUtil networkUtil;

    private ImageView imageView;
    private TextView txtTitle;
    private TextView txtCompany;
    private TextView txtCompanyNumber;
    private TextView txtPrice;
    private TextView txtPanelType;
    private TextView txtPanelPercent;
    private TextView txtPanelRange;
    private TextView txtPanelWeight;
    private TextView txtPanelApperance;

    String _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_solar);
        imageView = findViewById(R.id.img_detail_solar);
        txtTitle = findViewById(R.id.txt_detail_panel);
        txtCompany = findViewById(R.id.txt_detail_company);
        txtCompanyNumber = findViewById(R.id.txt_company_number);
        txtPrice = findViewById(R.id.txt_price);
        txtPanelType = findViewById(R.id.txt_panel_type);
        txtPanelPercent = findViewById(R.id.txt_panel_percent);
        txtPanelRange = findViewById(R.id.txt_panel_range);
        txtPanelWeight = findViewById(R.id.txt_panel_weight);
        txtPanelApperance = findViewById(R.id.txt_panel_appearance);
        getIncomingIntent();
    }



    private void getIncomingIntent() {
        _id = getIntent().getStringExtra("_id");
        String image = getIntent().getStringExtra("imgUrl");
        String company = getIntent().getStringExtra("company");
        String type = getIntent().getStringExtra("type");
        String price = getIntent().getStringExtra("price");
        String percent = getIntent().getStringExtra("percent");
        String range = getIntent().getStringExtra("range");
        String appearance = getIntent().getStringExtra("appearance");
        String name = getIntent().getStringExtra("name");
        Log.e("percent",percent);
        setData(name, image, company, type, price, percent, range, appearance);
    }

    private void setData(String name, String image, String company,String type, String price
            , String percent, String range, String appearance) {

        Picasso.get().load(image).into(imageView);
        txtTitle.setText(name);
        txtCompany.setText(company);
        txtPrice.setText(price);
        txtPanelType.setText(type);
        txtPanelPercent.setText(percent);
        txtPanelRange.setText(range);
        txtPanelApperance.setText(appearance);
    }


}
