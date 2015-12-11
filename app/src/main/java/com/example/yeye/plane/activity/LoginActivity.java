package com.example.yeye.plane.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.yeye.plane.util.LogUtil;
import com.example.yeye.plane.util.Utility;

import java.util.Calendar;
import java.util.Date;

public class LoginActivity extends AppCompatActivity {

    private ActionBar bar;
    private Button loginBtn;
    private Button registerBtn;
    private EditText usernameEdit;
    private EditText passwordEdit;

    private boolean doLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        doLogin = getIntent().getBooleanExtra("do_login", false);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!TextUtils.isEmpty(prefs.getString("username", null)) && !doLogin) {
            MainActivity.actionStart(LoginActivity.this);
            finish();
            return;
        }
        bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setHomeButtonEnabled(true);
        bar.setTitle(R.string.login);

        loginBtn = (Button) findViewById(R.id.btn_login);
        registerBtn = (Button) findViewById(R.id.btn_register);
        usernameEdit = (EditText) findViewById(R.id.edit_username);
        passwordEdit = (EditText) findViewById(R.id.edit_pwd);

        loginBtn.setOnClickListener(new LoginBtnClickedListener());
        registerBtn.setOnClickListener(new RegisterBtnClickedListener());
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

    private class LoginBtnClickedListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (TextUtils.isEmpty(usernameEdit.getText()) || TextUtils.isEmpty(passwordEdit.getText())) {
                //username or password cant be null or ""
                Toast.makeText(LoginActivity.this, R.string.uname_pwd_null_alert, Toast.LENGTH_SHORT).show();
            } else {
                String url = IConst.SERVLET_ADDR + "UserLogin";
                String data = "username=" + usernameEdit.getText() + "&" + "password=" + passwordEdit.getText();
                HttpUtil.sendHttpRequest(url, "POST", data, new HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        final boolean result = Utility.handleBooleanResultResponse(response);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (result) {
                                    Toast.makeText(LoginActivity.this, R.string.login_success, Toast.LENGTH_SHORT).show();
                                    rememberUser(usernameEdit.getText().toString());
                                    MainActivity.actionStart(LoginActivity.this);
                                    finish();
                                } else {
                                    Toast.makeText(LoginActivity.this, R.string.uname_pwd_wrong, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                    @Override
                    public void onError(Exception e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this, R.string.http_fail, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }
    }

    /**
     * when login, remember username and login time .
     * @param username
     */
    private void rememberUser(String username) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this).edit();
        editor.putString("username", username);
        editor.putString("last_login_time", new Date().toString());
        editor.apply();
    }

    private class RegisterBtnClickedListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            RegisterActivity.actionStart(LoginActivity.this);
        }
    }
}
