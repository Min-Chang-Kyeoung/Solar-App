package com.example.solar_energy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.solar_energy.network.NetworkUtil;
import com.example.solar_energy.network.Config;
import com.example.solar_energy.model.User;
import org.json.JSONException;
import org.json.JSONObject;



public class SiginupActivity extends AppCompatActivity {
    TextView txtEmail;
    TextView txtPw;
    TextView txtName;
    TextView txtHardwareNum;
    Button btnComplete;
    NetworkUtil networkUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siginup);
        txtEmail = (TextView) findViewById(R.id.txt_signup_email);
        txtPw = (TextView)findViewById(R.id.txt_signup_pw);
        txtName = (TextView)findViewById(R.id.txt_signup_name);
        txtHardwareNum = (TextView)findViewById(R.id.txt_siginup_hardwarenum);
        btnComplete = (Button)findViewById(R.id.btn_signup_complete);
        btnComplete.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestServer();
            }
        });
    }


    void requestServer(){

        JSONObject jsonObject = new JSONObject();
        try{
                jsonObject.put("name",txtName.getText().toString());
                jsonObject.put("id",txtEmail.getText().toString());
                jsonObject.put("password",txtPw.getText().toString());
                jsonObject.put("generator_num",txtHardwareNum.getText().toString());

        } catch (JSONException e) {
                e.printStackTrace();
        }
        networkUtil = new NetworkUtil(getApplicationContext());
        networkUtil.requestServer(Request.Method.POST,Config.MAIN_URL + Config.POST_SIGNUP,
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
               Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
               startActivity(intent);
               finish();
            }
        };
    }

}
