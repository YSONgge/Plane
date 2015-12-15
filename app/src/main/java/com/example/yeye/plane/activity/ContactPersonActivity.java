package com.example.yeye.plane.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.yeye.plane.R;
import com.example.yeye.plane.fragment.AddContactFragment;
import com.example.yeye.plane.fragment.ContactListFragment;

public class ContactPersonActivity extends AppCompatActivity
        implements ContactListFragment.OnFragmentInteractionListener,
        AddContactFragment.OnFragmentInteractionListener {

    private ActionBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_personnew);
        getFragmentManager().beginTransaction().add(R.id.fragment_content, ContactListFragment.newInstance()).commit();
        /*
        actionBar
         */
        bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setDisplayShowHomeEnabled(true);
            bar.setHomeButtonEnabled(true);
            bar.setTitle("联系人信息");
        }
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
        return super.onOptionsItemSelected(item);
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
