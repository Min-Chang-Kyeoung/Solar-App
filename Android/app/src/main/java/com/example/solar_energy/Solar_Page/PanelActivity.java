package com.example.solar_energy.Solar_Page;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.solar_energy.R;

import java.util.ArrayList;
import java.util.List;

public class PanelActivity extends AppCompatActivity {

    int ITEM_SIZE = 5;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soloar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        List<Solar_Item> items = new ArrayList<>();
        Solar_Item[] item = new Solar_Item[ITEM_SIZE];
        item[0] = new Solar_Item(R.drawable.solar_test, "#1","aa");
        item[1] = new Solar_Item(R.drawable.solar_test, "#2","nn");
        item[2] = new Solar_Item(R.drawable.solar_test, "#3","aa");
        item[3] = new Solar_Item(R.drawable.solar_test, "#4","wd");
        item[4] = new Solar_Item(R.drawable.solar_test, "#5","aa");

        for (int i = 0; i < ITEM_SIZE; i++) {
            items.add(item[i]);
        }

        recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), items, R.layout.activity_main));
    }
}
