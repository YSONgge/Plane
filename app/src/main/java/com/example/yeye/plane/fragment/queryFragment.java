package com.example.yeye.plane.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


import com.example.yeye.plane.R;
import com.example.yeye.plane.activity.ResultActivity;
import com.example.yeye.plane.util.HttpCallbackListener;
import com.example.yeye.plane.util.HttpUtil;
import com.example.yeye.plane.util.IConst;
import com.example.yeye.plane.util.Utility;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class QueryFragment extends Fragment implements View.OnClickListener {

    private View view;
    private EditText origin, dest;
    private DatePicker datePicker;
    private Button convert, query;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment QueryFragment.
     */
    public static QueryFragment newInstance() {
        QueryFragment fragment = new QueryFragment();
        return fragment;
    }

    public QueryFragment() {
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
        if (TextUtils.isEmpty(origin.getText()) || TextUtils.isEmpty(dest.getText())) {
            Toast.makeText(getContext(), "始发地目的地不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        Calendar c = Calendar.getInstance();
        c.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(c.getTime());
        switch (v.getId()) {
            case (R.id.btn_query_convert):
                String temp = origin.getText().toString();
                origin.setText(dest.getText());
                dest.setText(temp);
                break;
            case R.id.btn_query_query:
                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(false);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();
                String url = IConst.SERVLET_ADDR + "QueryFlight";
                String data = "origin=" + origin.getText() + "&" +
                        "dest=" + dest.getText() + "&" +
                        "flightDate=" + date;
                HttpUtil.sendHttpRequest(url, "POST", data, new HttpCallbackListener() {
                            @Override
                            public void onFinish(String response) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressDialog.dismiss();
                                    }
                                });
                                List<Map<String, String>> list = Utility.handleTicketResultResponse(response);
                                if (list.size() > 0) {
                                    Intent intent = new Intent(getContext(), ResultActivity.class);
                                    intent.putExtra("list", (Serializable) list);
                                    intent.putExtra("origin", origin.getText().toString());
                                    intent.putExtra("dest", dest.getText().toString());
                                    startActivity(intent);
                                } else {
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getContext(), R.string.no_flight, Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }

                            @Override
                            public void onError(Exception e) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressDialog.dismiss();
                                        Toast.makeText(getContext(), R.string.http_fail, Toast.LENGTH_SHORT).show();

                                    }
                                });
                            }
                        }

                );
                break;
        }
    }

}
