package com.example.solar_energy.Solar_Page;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.solar_energy.R;
import com.example.solar_energy.network.Config;
import com.example.solar_energy.network.NetworkUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PanelActivity extends AppCompatActivity {

    private NetworkUtil networkUtil;
    private RecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;
    private List<Solar_Item> items = new ArrayList<>();

    private boolean onceDataLoad = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soloar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                int position = rv.getChildAdapterPosition(child);

                if(position > -1) {
                    Intent intent = new Intent(PanelActivity.this, DetailPanelActivity.class);
                    intent.putExtra("_id", items.get(position).get_id());
                    intent.putExtra("imgUrl", items.get(position).getImage());
                    intent.putExtra("name", items.get(position).getTitle());
                    intent.putExtra("company", items.get(position).getCompany());
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        new StringTask().execute();
    }


    class StringTask extends AsyncTask<Void, String, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

                Log.e("dddd","asdfasdfasdf");
                requestGetItems();

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            recyclerAdapter = new RecyclerAdapter(items);
            recyclerView.setAdapter(recyclerAdapter);

            recyclerAdapter.notifyDataSetChanged();
        }
    }

    public void requestGetItems() {
        networkUtil = new NetworkUtil(getApplicationContext());
        Log.e("asdfasdf",Config.MAIN_URL + Config.GET_ITEMS);
        networkUtil.requestServer(Config.MAIN_URL + Config.GET_ITEMS, networkProductSuccessListener(), networkErrorListener());

    }

    private Response.Listener<JSONArray> networkProductSuccessListener() {
        return new Response.Listener<JSONArray>() {
            public void onResponse(JSONArray response) {
                Log.e("asdf","success");
                getProductJsonArray(response);
                onceDataLoad = false;
            }
        };
    }

    private Response.ErrorListener networkErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.getMessage() != null) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    public void getProductJsonArray(JSONArray response) {
        Log.e("items info : ", response.toString());
        try {
            JSONObject jresponse;
            for (int i = 0; i < response.length(); i++) {
                jresponse = response.getJSONObject(i);
                items.add(new Solar_Item(jresponse.getString("_id"),
                        jresponse.getString("imgUrl"),
                        jresponse.getString("name"),
                        jresponse.getString("company")
                ));
                recyclerAdapter.notifyDataSetChanged();
            }

        } catch (JSONException e) {
            throw new IllegalArgumentException("Failed to parse the String: ");
        }
    }
}