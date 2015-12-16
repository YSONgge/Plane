package com.example.yeye.plane.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.yeye.plane.R;
import com.example.yeye.plane.util.LogUtil;


import org.json.JSONException;
import org.json.JSONObject;

public class PassengerActivity extends AppCompatActivity {

    private android.support.v7.app.ActionBar bar;
    private Button submit;
    private EditText name, cardNumber;
    private RadioGroup radioGroup;
    private CheckBox delay, safe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger);

        submit = (Button) findViewById(R.id.btn_submit);
        name = (EditText) findViewById(R.id.edit_name);
        cardNumber = (EditText) findViewById(R.id.edit_id_card);
        radioGroup = (RadioGroup) findViewById(R.id.radio_level);
        delay = (CheckBox) findViewById(R.id.yanWu);
        safe = (CheckBox) findViewById(R.id.renShen);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check()) {
                    Intent intent = getIntent();
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("name", name.getText().toString());
                        jsonObject.put("idCard", cardNumber.getText().toString());
                        int level = 0;
                        switch (radioGroup.getCheckedRadioButtonId()) {
                            case R.id.jingjicang:
                                level = 1;
                                break;
                            case R.id.gongwucang:
                                level = 2;
                                break;
                            case R.id.toudengcang:
                                level = 3;
                                break;
                        }
                        jsonObject.put("level", level);
                        jsonObject.put("delay", delay.isChecked());
                        jsonObject.put("safe", safe.isChecked());
                    } catch (JSONException e) {
                        e.printStackTrace();
                        LogUtil.e("PassengerActivity", e.toString());
                    }
                    intent.putExtra("passenger", jsonObject.toString());
                    PassengerActivity.this.setResult(0, intent);
                    PassengerActivity.this.finish();
                }
            }
        });

        bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setDisplayShowHomeEnabled(true);
            bar.setHomeButtonEnabled(true);
            bar.setTitle("乘客信息");
        }
    }

    private boolean check() {
        String string1 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";//身份证号
        String cardNumber1 = cardNumber.getText().toString();

        boolean flag = true;
        if (TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(cardNumber.getText())) {
            flag = false;
            Toast.makeText(PassengerActivity.this, R.string.not_complete, Toast.LENGTH_SHORT).show();
        }
        if (!cardNumber1.matches(string1)) {
            flag = false;
            Toast.makeText(PassengerActivity.this, "身份证号有误", Toast.LENGTH_LONG).show();
        }
        return flag;
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
