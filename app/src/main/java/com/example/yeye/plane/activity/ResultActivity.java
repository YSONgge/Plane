package com.example.yeye.plane.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.yeye.plane.R;
import com.example.yeye.plane.entity.Flight;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultActivity extends AppCompatActivity {
    private ListView listview;
    private TextView origin,dest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        listview = (ListView) findViewById(R.id.listView);
        origin=(TextView)findViewById(R.id.txt_result_origin);
        dest= (TextView)findViewById(R.id.txt_result_dest);
        List<Map<String,String>> flightList = (List<Map<String,String>>) getIntent().getSerializableExtra("list");


//        List<Map<String,String>> list = new ArrayList<>();
        /*for (int i = 0; i < flightList.size(); i++) {
            Map<String, String> keyValuePair = new HashMap<String, String>();
            keyValuePair.put("flightStartTime", );
            keyValuePair.put("Button", "Button" + i);
            list.add(keyValuePair);
        }*/

        SimpleAdapter adapter = new SimpleAdapter(ResultActivity.this, flightList, R.layout.result_item,
                new String[]{"flightStartTime", "flightArriveTime", "flightId", "flightFare"}, new int[]{R.id.textClock, R.id.textClock2, R.id.txt_result_airportId, R.id.txt_result_pare});

        Intent intent = getIntent();
        String origin=intent.getStringExtra("origin");
        String dest=intent.getStringExtra("dest");

    }

}
