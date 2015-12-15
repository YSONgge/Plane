package com.example.yeye.plane.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
 * {@link mineFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link mineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class mineFragment extends Fragment {

    private View view;
    private Button changePass, airportEn, logoutBtn;
    TextView usernameTxt;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment mineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static mineFragment newInstance() {
        mineFragment fragment = new mineFragment();
        return fragment;
    }

    public mineFragment() {
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
                Intent intent = new Intent(getContext(), ChangePwdActivity.class);
                startActivity(intent);
            }
        });

        airportEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AirplaneEnActivity.class);
                startActivity(intent);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("username", "").apply();
                Intent intent = new Intent(getContext(), LoginActivity.class);
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }


    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }


}
