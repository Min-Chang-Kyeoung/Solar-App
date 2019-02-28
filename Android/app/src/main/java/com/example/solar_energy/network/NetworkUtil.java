package com.example.solar_energy.network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

public class NetworkUtil {
    private JsonObjectRequest jsonObjectRequest;
    private JsonArrayRequest jsonArrayRequest;
    private RequestQueue requestQueue;

    public NetworkUtil(Context context){
        requestQueue  = MyVolley.getInstance(context).getRequestQueue();
    }

    /*      POST, PUT, DELETE       */
    public void requestServer(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        jsonObjectRequest = new JsonObjectRequest(method, url, jsonRequest, listener, errorListener);
        requestQueue.add(jsonObjectRequest);
    }

    /*       GET OBJECT       */
    public void requestServer(String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        jsonObjectRequest = new JsonObjectRequest(url, jsonRequest, listener, errorListener);
        requestQueue.add(jsonObjectRequest);
    }

    /*       GET ARRAY       */
    public void requestServer(String url, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        jsonArrayRequest = new JsonArrayRequest(url, listener, errorListener);
        requestQueue.add(jsonArrayRequest);
    }
}
