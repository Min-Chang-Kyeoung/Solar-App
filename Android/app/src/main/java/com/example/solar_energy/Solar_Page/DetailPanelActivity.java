package com.example.solar_energy.Solar_Page;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.solar_energy.R;

public class DetailPanelActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView txtTitle;
    private TextView txtCompany;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_solar);
        imageView = findViewById(R.id.img_detail_solar);
        txtTitle = findViewById(R.id.txt_detail_panel);
        txtCompany = findViewById(R.id.txt_detail_company);
        getIncomingIntent();
    }

    private void getIncomingIntent(){
        if(getIntent().hasExtra("img")||getIntent().hasExtra("title")||getIntent().hasExtra("company")){
            int image = getIntent().getIntExtra("img",1);
            String title = getIntent().getStringExtra("title");
            String company = getIntent().getStringExtra("company");
            setData(image,title,company);
        }
    }

    private void setData(int image,String title,String company){
        Log.e("item",title + "\n"+ company);
        Drawable drawable = ContextCompat.getDrawable(this,image);
        imageView.setImageDrawable(drawable);
        txtTitle.setText(title);
        txtCompany.setText(company);
    }
}
