package com.example.yeye.plane.activity;


import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.yeye.plane.R;


public class ChangePwdActivity extends AppCompatActivity {
    private EditText oldPwd, newPwd, confirmPwd;
    private ActionBar bar;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);

        // TODO: 2015/12/11 don't need confirm password, make password show and hide
        oldPwd = (EditText) findViewById(R.id.edit_old_pwd);
        newPwd = (EditText) findViewById(R.id.edit_new_pwd);
        confirmPwd = (EditText) findViewById(R.id.edit_confirm_pwd);

        bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setHomeButtonEnabled(true);
        bar.setTitle(R.string.change_pwd);
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

    /**
     * onclick reset btn
     * @param v
     */
    public void reset(View v) {
        oldPwd.setText("");
        newPwd.setText("");
        confirmPwd.setText("");
    }

    /**
     * on click submit btn
     * @param v
     */
    public void submit(View v) {

    }
}
