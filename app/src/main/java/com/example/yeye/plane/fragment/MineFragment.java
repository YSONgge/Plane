package com.example.yeye.plane.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.yeye.plane.R;
import com.example.yeye.plane.activity.AirplaneEnActivity;
import com.example.yeye.plane.activity.ChangePwdActivity;
import com.example.yeye.plane.activity.LoginActivity;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link MineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MineFragment extends Fragment {

    private View view;
    private Button changePass, airportEn, logoutBtn;
    TextView usernameTxt;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MineFragment.
     */
    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    public MineFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_mine, container, false);
        changePass = (Button) view.findViewById(R.id.btn_change_pass);
        airportEn = (Button) view.findViewById(R.id.btn_airplane_en);
        usernameTxt = (TextView) view.findViewById(R.id.txt_username);
        logoutBtn = (Button) view.findViewById(R.id.btn_log_out);

        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangePwdActivity.class);
                startActivity(intent);
            }
        });

        airportEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AirplaneEnActivity.class);
                startActivity(intent);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("username", "").apply();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        String username = PreferenceManager.getDefaultSharedPreferences(getContext()).getString("username", "");
        if (username.length() == 0) {
            usernameTxt.setText(getString(R.string.user_not_login));
            changePass.setVisibility(View.GONE);
            logoutBtn.setVisibility(View.GONE);
        } else {
            usernameTxt.setText(username);
            changePass.setVisibility(View.VISIBLE);
            logoutBtn.setVisibility(View.VISIBLE);
        }
        return view;
    }

}
