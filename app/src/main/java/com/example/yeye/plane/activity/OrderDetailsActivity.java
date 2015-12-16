package com.example.yeye.plane.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.yeye.plane.R;
import com.example.yeye.plane.entity.Ticket;

import java.io.Serializable;

import android.support.v7.app.ActionBar;


public class OrderDetailsActivity extends AppCompatActivity implements Serializable {

    private TextView origin, dest, passenger, contactPerson, phone, FlightId;
    private ActionBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
//        origin = (TextView) findViewById(R.id.txt_orderT_origin);
//        dest = (TextView) findViewById(R.id.txt_order_dest);
        phone = (TextView) findViewById(R.id.txt_orderT_number);
        FlightId = (TextView) findViewById(R.id.txt_orderT_flightId);
        passenger = (TextView) findViewById(R.id.txt_orderT_passenger);
        contactPerson = (TextView) findViewById(R.id.txt_orderT_contactP);


        Ticket data = (Ticket) getIntent().getSerializableExtra("order");
        passenger.setText(data.getpName());
        contactPerson.setText(data.getcName());
        FlightId.setText(data.getFlightId());
        phone.setText(data.getcPhone());


        bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setHomeButtonEnabled(true);
        bar.setTitle(R.string.orderdetail);
    }

    /*
点击按钮结束当前界面
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
