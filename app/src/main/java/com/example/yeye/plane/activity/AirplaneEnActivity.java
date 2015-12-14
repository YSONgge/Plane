package com.example.yeye.plane.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yeye.plane.R;
import com.example.yeye.plane.util.HttpCallbackListener;
import com.example.yeye.plane.util.HttpUtil;
import com.example.yeye.plane.util.IConst;
import com.example.yeye.plane.util.Utility;

public class AirplaneEnActivity extends AppCompatActivity {

    private android.support.v7.app.ActionBar bar;
    private EditText cityName;
    private TextView airportPhone, airportWeather;
    private Button queryAirport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airplane_en);

        cityName = (EditText) findViewById(R.id.edit_query_city);
        airportPhone = (TextView) findViewById(R.id.txt_phone);
        airportWeather = (TextView) findViewById(R.id.txt_weather);
        queryAirport = (Button) findViewById(R.id.btn_query_airport);

        queryAirport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cityName.getText() != null) {
                    String url = IConst.SERVLET_ADDR + "QueryAirport";
                    String data = "aName=" + cityName.getText();
                    // String phoneNumber = null;
                    HttpUtil.sendHttpRequest(url, "POST", data, new HttpCallbackListener() {
                        @Override
                        public void onFinish(String response) {
                            final String phoneNumber = Utility.handleAirportPhoneNumber(response);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    airportPhone.setText(phoneNumber);
                                    airportWeather.setText("多云");
                                }
                            });
                        }

                        @Override
                        public void onError(Exception e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(AirplaneEnActivity.this, R.string.http_fail, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                } else {
                    Toast.makeText(AirplaneEnActivity.this, "没填城市名", Toast.LENGTH_LONG).show();
                }
            }
        });
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
