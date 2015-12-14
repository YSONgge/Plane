package com.example.yeye.plane.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.yeye.plane.R;

public class PassengerActivity extends AppCompatActivity {

    private android.support.v7.app.ActionBar bar;
    private Button button;
    private EditText name, cardNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger);

        button = (Button) findViewById(R.id.button);
        name = (EditText) findViewById(R.id.editText);
        cardNumber = (EditText) findViewById(R.id.editText2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("name", name.getText().toString());
                PassengerActivity.this.setResult(0, intent);
                PassengerActivity.this.finish();
            }
        });

        // TODO: 2015/12/11 机票类型未完成

        bar = getSupportActionBar();
         bar.setDisplayHomeAsUpEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setHomeButtonEnabled(true);
        bar.setTitle("乘客信息");
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
