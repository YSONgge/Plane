package com.example.yeye.plane.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yeye.plane.R;

public class AirplaneEnActivity extends AppCompatActivity {

    private android.support.v7.app.ActionBar bar;
    private EditText cityName;
    private TextView airportPhone,airportWeather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airplane_en);

        cityName = (EditText)findViewById(R.id.edit_query_city);
        airportPhone = (TextView)findViewById(R.id.txt_weather);
        airportWeather = (TextView)findViewById(R.id.txt_weather);


        /*
        actionBar
         */
        bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setHomeButtonEnabled(true);
        bar.setTitle("机场宝典");
    }

    /*
  点击actionbar上左侧按钮结束当前界面
   */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        //return super.onOptionsItemSelected(item);
        return true;
    }
}
