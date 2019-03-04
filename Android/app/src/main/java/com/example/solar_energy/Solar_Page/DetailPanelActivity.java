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

    }



    class StringTask extends AsyncTask<Void, String, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            getIncomingIntent();
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            requestPostDetail();
        }
    }




    private void getIncomingIntent() {
        _id = getIntent().getStringExtra("_id");
        String image = getIntent().getStringExtra("img");
        String title = getIntent().getStringExtra("title");
        String company = getIntent().getStringExtra("company");
        setData( image, title, company);
    }

    private void setData(String image, String title, String company) {
        Log.e("item", title + "\n" + company);
        Picasso.get().load(image).into(imageView);
        txtTitle.setText(title);
        txtCompany.setText(company);
    }


    public void requestPostDetail() {
        networkUtil = new NetworkUtil(this);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("_id", _id);
            networkUtil.requestServer(Request.Method.POST, Config.MAIN_URL + Config.POST_DETAIL_ITEM, jsonObject, networkSuccessListener(), networkErrorListener());
        } catch (JSONException e) {
            throw new IllegalStateException("Failed to convert the object to JSON");
        }
    }

    private Response.Listener<JSONObject> networkSuccessListener() {
        return new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject response) {
                try {
                    txtCompanyNumber.setText("상경이 다음 여자친구 ㅎㅎㅅㅎㅎ");
                    txtPrice.setText(response.getString("price"));
                    txtPanelType.setText(response.getString("type"));
                    txtPanelPercent.setText(response.getString("percent"));
                    txtPanelRange.setText(response.getString("range"));
                  //  txtPanelWeight.setText(response.getString("weight"));
                    txtPanelApperance.setText(response.getString("appearance"));

                } catch (JSONException e) {
                    throw new IllegalArgumentException(e.toString());
                }
            }
        };
    }

    private Response.ErrorListener networkErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.getMessage() != null) {
                    Toast.makeText(DetailPanelActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        };
    }
}
