package com.example.yeye.plane.activity;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yeye.plane.R;
import com.example.yeye.plane.util.HttpCallbackListener;
import com.example.yeye.plane.util.HttpUtil;
import com.example.yeye.plane.util.IConst;
import com.example.yeye.plane.util.Utility;


public class ChangePwdActivity extends AppCompatActivity {
    private EditText oldPwd, newPwd;
    private ActionBar bar;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);

        oldPwd = (EditText) findViewById(R.id.edit_old_pwd);
        newPwd = (EditText) findViewById(R.id.edit_new_pwd);
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox_show_hide_pwd);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    newPwd.setTransformationMethod(new PasswordTransformationMethod());
                    oldPwd.setTransformationMethod(new PasswordTransformationMethod());
                } else {
                    newPwd.setTransformationMethod(null);
                    oldPwd.setTransformationMethod(null);
                }
            }
        });

        bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setDisplayShowHomeEnabled(true);
            bar.setHomeButtonEnabled(true);
            bar.setTitle(R.string.change_pwd);
        }
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

    /**
     * onclick reset btn
     *
     * @param v
     */
    public void reset(View v) {
        oldPwd.setText("");
        newPwd.setText("");
    }

    /**
     * on click submit btn
     *
     * @param v
     */
    public void submit(View v) {
        String url = IConst.SERVLET_ADDR + "ChangePW";
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ChangePwdActivity.this);
        String username = sp.getString("username", "");
        if (!TextUtils.isEmpty(newPwd.getText()) || !TextUtils.isEmpty(oldPwd.getText())) {
            String data = "username=" + username + "&" + "password=" + oldPwd.getText() + "&" + "newpass=" + newPwd.getText();
            HttpUtil.sendHttpRequest(url, "POST", data, new HttpCallbackListener() {
                @Override
                public void onFinish(String response) {
                    final boolean result = Utility.handleBooleanResultResponse(response);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (result) {
                                Toast.makeText(ChangePwdActivity.this, "更改成功", Toast.LENGTH_LONG).show();
                                finish();
                            } else {
                                Toast.makeText(ChangePwdActivity.this, "未更改成功", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }

                @Override
                public void onError(Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ChangePwdActivity.this, R.string.http_fail, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        } else {
            Toast.makeText(ChangePwdActivity.this, R.string.not_complete, Toast.LENGTH_LONG).show();
        }
    }
}
