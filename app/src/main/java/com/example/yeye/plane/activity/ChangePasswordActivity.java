package com.example.yeye.plane.activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.yeye.plane.R;


public class ChangePasswordActivity extends AppCompatActivity {
    private EditText oldpass, newpass, repass;
    private android.support.v7.app.ActionBar bar;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        oldpass = (EditText) findViewById(R.id.editText4);
        newpass = (EditText) findViewById(R.id.editText5);
        repass = (EditText) findViewById(R.id.editText6);

        bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setHomeButtonEnabled(true);
        bar.setTitle("修改密码");
    }
    /*
    点击按钮结束当前界面
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        //return super.onOptionsItemSelected(item);
        return true;
    }

    public void clean(View v) {
        oldpass.setText("");
        newpass.setText("");
        repass.setText("");
    }

    public void click(View v) {

    }
}
