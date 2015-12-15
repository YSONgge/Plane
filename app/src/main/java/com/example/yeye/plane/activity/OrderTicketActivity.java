package com.example.yeye.plane.activity;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yeye.plane.R;
import com.example.yeye.plane.entity.Contact;
import com.example.yeye.plane.util.HttpCallbackListener;
import com.example.yeye.plane.util.HttpUtil;
import com.example.yeye.plane.util.IConst;
import com.example.yeye.plane.util.LogUtil;
import com.example.yeye.plane.util.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderTicketActivity extends AppCompatActivity {


    private ImageView addPassengerImg, addContactImg;
    private TextView passengerName;
    private TextView contactName;
    private Button submit;
    private Contact contact = null;
    private JSONObject passengerjson = null;
    private ListView listView;
    private Map<String, String> map;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_ticket);

        addPassengerImg = (ImageView) findViewById(R.id.imageView_add_passenger);
        addContactImg = (ImageView) findViewById(R.id.imageView_add_contact);
        passengerName = (TextView) findViewById(R.id.txt_passenger_name);
        contactName = (TextView) findViewById(R.id.txt_contact_name);
        submit = (Button) findViewById(R.id.btn_submit);
        listView = (ListView) findViewById(R.id.lv_flight);

        map = (Map<String, String>) getIntent().getSerializableExtra("flightInfo");
        List<Map<String, String>> flightList = new ArrayList<>();
        flightList.add(map);
        SimpleAdapter adapter = new SimpleAdapter(OrderTicketActivity.this, flightList, R.layout.result_item,
                new String[]{"flightStartTime", "flightArriveTime", "flightId", "flightFare"}, new int[]{R.id.txt_start_time, R.id.txt_arrive_time, R.id.txt_result_flightId, R.id.txt_result_fare});
        listView.setAdapter(adapter);


        addPassengerImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OrderTicketActivity.this, PassengerActivity.class);
                startActivityForResult(i, 0);
            }
        });
        addContactImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OrderTicketActivity.this, ContactPersonActivity.class);
                startActivityForResult(i, 1);
            }
        });
        submit.setOnClickListener(new onSubmit());

        username = PreferenceManager.getDefaultSharedPreferences(OrderTicketActivity.this).getString("username", "");
        if (username.length() == 0) {
            Intent i = new Intent(OrderTicketActivity.this, LoginActivity.class);
            i.putExtra("fromOtherActivity", true);
            startActivity(i);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            if (requestCode == 0 && resultCode == 0) {
                String passenger = data.getStringExtra("passenger");
                try {
                    passengerjson = new JSONObject(passenger);
                    passengerName.setText(passengerjson.getString("name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    LogUtil.e("passengerJson", e.toString());
                }
            } else if (requestCode == 1 && resultCode == 1) {
                contact = (Contact) data.getSerializableExtra("contact");
                contactName.setText(contact.getName());
            }
        }
    }

    private class onSubmit implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // TODO: 2015/12/14 check complete
            if (contact == null || passengerjson == null) {
                Toast.makeText(OrderTicketActivity.this, R.string.not_complete, Toast.LENGTH_SHORT).show();
                return;
            }
            if (username.length() == 0) {
                username = PreferenceManager.getDefaultSharedPreferences(OrderTicketActivity.this).getString("username", "");
            }
            String address = IConst.SERVLET_ADDR + "TicketOrder";
            StringBuilder data = new StringBuilder();
            try {
                data.append("pName=");
                data.append(passengerjson.getString("name"));
                data.append("&");
                data.append("pCardNumber=");
                data.append(passengerjson.getString("idCard"));
                data.append("&");
                data.append("cName=");
                data.append(contact.getName());
                data.append("&");
                data.append("cPhone=");
                data.append(contact.getPhoneNumber());
                data.append("&");
                data.append("cEmail=");
                data.append(contact.getEmail());
                data.append("&");
                data.append("username=");
                data.append(username);
                data.append("&");
                data.append("flightId=");
                data.append(map.get("flightId"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            HttpUtil.sendHttpRequest(address, "POST", data.toString(), new HttpCallbackListener() {
                @Override
                public void onFinish(String response) {
                    final Boolean result = Utility.handleBooleanResultResponse(response);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(OrderTicketActivity.this, result.toString(), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
                }

                @Override
                public void onError(Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(OrderTicketActivity.this, R.string.http_fail, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }
}
