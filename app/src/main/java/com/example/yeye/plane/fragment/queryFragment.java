package com.example.yeye.plane.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import com.example.yeye.plane.R;
import com.example.yeye.plane.activity.ResultActivity;
import com.example.yeye.plane.entity.Flight;
import com.example.yeye.plane.util.HttpCallbackListener;
import com.example.yeye.plane.util.HttpUtil;
import com.example.yeye.plane.util.IConst;
import com.example.yeye.plane.util.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link queryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link queryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class queryFragment extends Fragment implements View.OnClickListener {

    private OnFragmentInteractionListener mListener;

    private View view;
    private EditText origin, dest;
    private DatePicker datePicker;
    private Button convert, query;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment queryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static queryFragment newInstance() {
        queryFragment fragment = new queryFragment();
        return fragment;
    }

    public queryFragment() {
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
        view = inflater.inflate(R.layout.fragment_query, container, false);
        origin = (EditText) view.findViewById(R.id.edit_query_origin);
        dest = (EditText) view.findViewById(R.id.edit_query_dest);
        datePicker = (DatePicker) view.findViewById(R.id.datePicker);
        convert = (Button) view.findViewById(R.id.btn_query_convert);
        query = (Button) view.findViewById(R.id.btn_query_query);
        initComponent();
        return view;
    }

    private void initComponent() {


        convert.setOnClickListener(this);
        query.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (view.getId()) {
            case (R.id.btn_query_convert):
                if (origin != null && dest != null) {
                    String temp = origin.getText().toString();
                    origin.setText(dest.getText());
                    dest.setText(temp);
                } else {
                    Toast.makeText(getContext(), "不可为空", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn_query_query:
                String url = IConst.SERVLET_ADDR + "QueryFLight";
                String data = "origin=" + origin.getText() + "&" + "dest=" + dest.getText() + "&" + "flightDate=" + null;
                HttpUtil.sendHttpRequest(url, "POST", data, new HttpCallbackListener() {
                            @Override
                            public void onFinish(String response) {
                                List<Map<String,String>> list = Utility.handleTicketResultResponse(response);


                                Intent intent = new Intent(getContext(), ResultActivity.class);
                                intent.putExtra("list", (Serializable) list);

                                intent.putExtra("origin", origin.getText().toString());
                                intent.putExtra("dest", dest.getText().toString());
                                startActivity(intent);

                            }

                            @Override
                            public void onError(Exception e) {
                                Toast.makeText(getContext(), R.string.http_fail, Toast.LENGTH_SHORT).show();
                            }

                        }

                );
                break;
        }
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
