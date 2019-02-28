package com.example.solar_energy.network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MyVolley {
    private static MyVolley volley;
    private  RequestQueue requestQueue;

    private MyVolley(Context context){
        requestQueue = Volley.newRequestQueue(context);
    }

    public static MyVolley getInstance(Context context){
        if(volley == null){
            volley = new MyVolley(context);
        }
        return volley;
    }

    public RequestQueue getRequestQueue(){
        return requestQueue;
    }
}
