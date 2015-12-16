package com.example.yeye.plane.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.example.yeye.plane.R;
import com.example.yeye.plane.fragment.MineFragment;
import com.example.yeye.plane.fragment.OrderFragment;
import com.example.yeye.plane.fragment.QueryFragment;
import com.example.yeye.plane.util.LogUtil;

public class MainActivity extends AppCompatActivity implements MineFragment.OnFragmentInteractionListener{

    static final int NUM_ITEMS = 3;
    CollectionPagerAdapter mPagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_eric);
        mPagerAdapter = new CollectionPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        // When swiping between pages, select the
                        // corresponding tab.
                        getSupportActionBar().setSelectedNavigationItem(position);
                    }
                });
        final ActionBar actionBar = getSupportActionBar();
        // Specify that tabs should be displayed in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create a tab listener that is called when the user changes tabs.
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {

            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }
        };

        // Add 3 tabs, specifying the tab's text and TabListener
        for (int i = 0; i < 3; i++) {
            switch (i) {
                case 0:
                    actionBar.addTab(
                            actionBar.newTab()
                                    .setText("查询")
                                    .setTabListener(tabListener));
                    break;
                case 1:
                    actionBar.addTab(
                            actionBar.newTab()
                                    .setText("订单")
                                    .setTabListener(tabListener));
                    break;
                case 2:
                    actionBar.addTab(
                            actionBar.newTab()
                                    .setText("我的")
                                    .setTabListener(tabListener));
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //// TODO: 2015/12/14 what if not clear?
        mViewPager.clearOnPageChangeListeners();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public static void actionStart(Context context) {
        Intent i = new Intent(context, MainActivity.class);
        context.startActivity(i);
    }

    public class CollectionPagerAdapter extends FragmentPagerAdapter {

        public CollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            LogUtil.d("FragmentPagerAdapter", position + "");
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = QueryFragment.newInstance();
                    break;
                case 1:
                    fragment = OrderFragment.newInstance();
                    break;
                case 2:
                    fragment = MineFragment.newInstance();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }
    }
}