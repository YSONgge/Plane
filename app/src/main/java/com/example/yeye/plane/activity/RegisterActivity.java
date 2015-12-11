package com.example.yeye.plane.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yeye.plane.R;
import com.example.yeye.plane.util.HttpCallbackListener;
import com.example.yeye.plane.util.HttpUtil;
import com.example.yeye.plane.util.IConst;
import com.example.yeye.plane.util.Utility;


public class RegisterActivity extends AppCompatActivity {

    EditText usernameEdit;
    EditText passwordEdit;
    Button registerBtn;

    private ActionBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setHomeButtonEnabled(true);
        bar.setTitle("注册");

        usernameEdit = (EditText) findViewById(R.id.edit_register_username);
        passwordEdit = (EditText) findViewById(R.id.edit_register_pwd);
        registerBtn = (Button) findViewById(R.id.register_button);

        registerBtn.setOnClickListener(new onRegisterButtonClickListener());
        passwordEdit.setOnFocusChangeListener(new onPasswordFocusChangeListener());
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
     * check username and password , if not empty, do register.
     */
    private class onRegisterButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (TextUtils.isEmpty(usernameEdit.getText()) || TextUtils.isEmpty(passwordEdit.getText())) {
                //username or password cant be null or ""
                Toast.makeText(RegisterActivity.this, R.string.uname_pwd_null_alert, Toast.LENGTH_SHORT).show();
            } else {
                String url = IConst.SERVLET_ADDR + "UserRegister";
                String data = "username=" + usernameEdit.getText() + "&" + "password=" + passwordEdit.getText();
                HttpUtil.sendHttpRequest(url, "POST", data, new HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        final boolean result = Utility.handleBooleanResultResponse(response);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (result) {
                                    Toast.makeText(RegisterActivity.this, R.string.register_success, Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(RegisterActivity.this, R.string.register_fail, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                    @Override
                    public void onError(Exception e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this, R.string.http_fail, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }
    }

    private class onPasswordFocusChangeListener implements View.OnFocusChangeListener {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                if (TextUtils.isEmpty(usernameEdit.getText())) {
                    Toast.makeText(RegisterActivity.this, R.string.uname_pwd_null_alert, Toast.LENGTH_SHORT).show();
                } else {
                    String url = IConst.SERVLET_ADDR + "CheckUserExist?" + "username=" + usernameEdit.getText();
                    HttpUtil.sendHttpRequest(url, "GET", null, new HttpCallbackListener() {
                        @Override
                        public void onFinish(String response) {
                            final boolean result = Utility.handleBooleanResultResponse(response);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (result) {
                                        Toast.makeText(RegisterActivity.this, R.string.register_user_exist, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }

                        @Override
                        public void onError(Exception e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(RegisterActivity.this, R.string.http_fail, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
            }
        }
    }

    public static void actionStart(Context context) {
        Intent i = new Intent(context, RegisterActivity.class);
        context.startActivity(i);
    }
}
