package com.example.yeye.plane.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.yeye.plane.R;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ResultActivity extends AppCompatActivity {
    private ListView listView;
    private TextView titleTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        listView = (ListView) findViewById(R.id.listView);
        titleTxt = (TextView) findViewById(R.id.txt_title);

        Intent intent = getIntent();
        String origin = intent.getStringExtra("origin");
        String dest = intent.getStringExtra("dest");
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        String titleStr = origin + "  TO  " + dest;
        titleTxt.setText(titleStr);
        final List<Map<String, String>> flightList = (List<Map<String, String>>) intent.getSerializableExtra("list");

        SimpleAdapter adapter = new SimpleAdapter(ResultActivity.this, flightList, R.layout.result_item,
                new String[]{"flightStartTime", "flightArriveTime", "flightId", "flightFare"}, new int[]{R.id.txt_start_time, R.id.txt_arrive_time, R.id.txt_result_flightId, R.id.txt_result_fare});
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ResultActivity.this, OrderTicketActivity.class);
                i.putExtra("flightInfo", (Serializable) flightList.get(position));
                startActivity(i);
                finish();
            }
        });
    }

}
