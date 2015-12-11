package com.example.yeye.plane.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yeye.plane.R;

public class OrderTicketActivity extends AppCompatActivity {


    private ImageView imageView1, imageView2;
    private TextView name1;
    private EditText name2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_ticket);

        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        name1 = (TextView) findViewById(R.id.textView22);
        name2 = (EditText) findViewById(R.id.editText9);

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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            if (requestCode == 0 && resultCode == 0) {
                Bundle data1 = data.getExtras();
                String passenger = data1.getString("name");
                name1.setText(passenger);
                System.out.println("passenger = " + passenger);

            } else if (requestCode == 1 && resultCode == 1) {
                Bundle data2 = data.getExtras();
                String contact = data2.getString("name");
                name2.setText(contact);
                System.out.println("contact = " + contact);
            }
            //super.onActivityResult(requestCode, resultCode, data);

        }
    }
}
