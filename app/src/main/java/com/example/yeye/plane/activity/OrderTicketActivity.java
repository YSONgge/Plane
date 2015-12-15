package com.example.yeye.plane.activity;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yeye.plane.R;
import com.example.yeye.plane.enity.Contact;
import com.example.yeye.plane.enity.Passenger;
import com.example.yeye.plane.util.HttpCallbackListener;
import com.example.yeye.plane.util.HttpUtil;
import com.example.yeye.plane.util.IConst;
import com.example.yeye.plane.util.LogUtil;
import com.example.yeye.plane.util.Utility;

import org.json.JSONException;
import org.json.JSONObject;

public class OrderTicketActivity extends AppCompatActivity {


    private ImageView imageView1, imageView2;
    private TextView name1;
    private EditText name2;
    private Button submit;
    private Contact contact = null;
    private JSONObject passengerjson = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_ticket);

        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        name1 = (TextView) findViewById(R.id.textView22);
        name2 = (EditText) findViewById(R.id.editText9);
        submit = (Button) findViewById(R.id.btn_submit);


        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OrderTicketActivity.this, PassengerActivity.class);
                startActivityForResult(i, 0);
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OrderTicketActivity.this, ContactPersonActivity.class);
                startActivityForResult(i, 1);
            }
        });
        submit.setOnClickListener(new onSubmit());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            if (requestCode == 0 && resultCode == 0) {
                String passenger = data.getStringExtra("passenger");
                try {
                    passengerjson = new JSONObject(passenger);
                    name1.setText(passengerjson.getString("name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    LogUtil.e("passengerJson", e.getMessage());
                }
            } else if (requestCode == 1 && resultCode == 1) {
                contact = (Contact) data.getSerializableExtra("contact");
                name2.setText(contact.getName());
            }
            //super.onActivityResult(requestCode, resultCode, data);

        }
    }

    private class onSubmit implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // TODO: 2015/12/14 check complete
            if (contact == null || passengerjson == null) {
                Toast.makeText(OrderTicketActivity.this, R.string.not_complete, Toast.LENGTH_SHORT).show();
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
                data.append("uid=");
                //// TODO: 2015/12/14 get uid first
                data.append(PreferenceManager.getDefaultSharedPreferences(OrderTicketActivity.this).getInt("uid", 0));
                data.append("&");
                data.append("flightId=");
                data.append(getIntent().getStringExtra("flightId"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            HttpUtil.sendHttpRequest(address, "POST", data.toString(), new HttpCallbackListener() {
                @Override
                public void onFinish(String response) {
                    Boolean result = Utility.handleBooleanResultResponse(response);
                    Toast.makeText(OrderTicketActivity.this, result.toString(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(Exception e) {
                    Toast.makeText(OrderTicketActivity.this, R.string.http_fail, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
