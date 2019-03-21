package com.example.solar_energy;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.solar_energy.network.NetworkUtil;
import com.example.solar_energy.network.Config;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    Button btnSignin;
    TextView txtId;
    TextView txtPw;
    NetworkUtil networkUtil;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btn_login);
        btnSignin = findViewById(R.id.btn_signin);
        txtId = findViewById(R.id.txt_id);
        txtPw = findViewById(R.id.txt_pw);
        btnSignin.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SiginupActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnLogin.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
              requestServer();
            }
        });
    }

    private void requestServer() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id",txtId.getText().toString());
            jsonObject.put("pw",txtPw.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        networkUtil = new NetworkUtil(getApplicationContext());
        networkUtil.requestServer(Request.Method.POST,Config.MAIN_URL + Config.POST_LOGIN,
                jsonObject,networkSuccessListener(),networkErrorListner());
    }
    private Response.ErrorListener networkErrorListner() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.getMessage() != null) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    private Response.Listener<JSONObject> networkSuccessListener() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                String hardware, name, authToken;
                try {
                    JSONObject infoJson  = response.getJSONObject("info");

                    name = infoJson.getString("name");
                    hardware = infoJson.getString("hardware");
                    authToken = infoJson.getString("token");
                    storeLocalCache(name, hardware, authToken);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
    }


    private  void storeLocalCache(String name, String hardware, String authToken){
        sharedPreferences = getSharedPreferences("authToken",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token",authToken);
        editor.commit();

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.putExtra("name",name);
        intent.putExtra("hardware",hardware);

        startActivity(intent);
        finish();

    }

}

