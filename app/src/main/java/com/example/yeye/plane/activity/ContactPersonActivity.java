package com.example.yeye.plane.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.yeye.plane.R;
import com.example.yeye.plane.fragment.AddContactFragment;
import com.example.yeye.plane.fragment.ContactListFragment;

public class ContactPersonActivity extends AppCompatActivity
        implements ContactListFragment.OnFragmentInteractionListener,
        AddContactFragment.OnFragmentInteractionListener {

    private android.support.v7.app.ActionBar bar;
    private Button button;
    private EditText name, phone, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_personnew);

//        getFragmentManager().beginTransaction().add(R.id.fragment_content, ContactListFragment.newInstance()).commit();

       /* button = (Button) findViewById(R.id.button);
        name = (EditText) findViewById(R.id.editText);
        phone = (EditText) findViewById(R.id.editText2);
        email = (EditText) findViewById(R.id.editText3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("name", name.getText().toString());
                ContactPersonActivity.this.setResult(1, intent);
                ContactPersonActivity.this.finish();

            }
        });*/

        /*
        actionBar
         */
        bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setHomeButtonEnabled(true);
        bar.setTitle("联系人信息");
    }


    /*
   点击actionbar上左侧按钮结束当前界面
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

    @Override
    public void addContact() {
        getFragmentManager().beginTransaction().replace(R.id.fragment_content, AddContactFragment.newInstance()).commit();
    }

    @Override
    public void addFinish() {
        getFragmentManager().beginTransaction().replace(R.id.fragment_content, ContactListFragment.newInstance()).commit();
    }
}
