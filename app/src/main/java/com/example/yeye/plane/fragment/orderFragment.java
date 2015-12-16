package com.example.yeye.plane.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yeye.plane.R;
import com.example.yeye.plane.activity.OrderDetailsActivity;
import com.example.yeye.plane.entity.Ticket;
import com.example.yeye.plane.util.HttpCallbackListener;
import com.example.yeye.plane.util.HttpUtil;
import com.example.yeye.plane.util.IConst;
import com.example.yeye.plane.util.LogUtil;
import com.example.yeye.plane.util.Utility;

import java.io.Serializable;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class orderFragment extends Fragment {

    private ListView listView;
    private List<Ticket> data;
    private ArrayAdapter<Ticket> adapter;
    private String username;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment orderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static orderFragment newInstance() {
        orderFragment fragment = new orderFragment();
        return fragment;
    }

    public orderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        username = PreferenceManager.getDefaultSharedPreferences(getContext()).getString("username", "");
        data = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (username.length() == 0) {
            View v = inflater.inflate(R.layout.fragment_user_not_login, container, false);
            return v;
        }
        View v = inflater.inflate(R.layout.fragment_order, container, false);
        listView = (ListView) v.findViewById(R.id.listView);
        Button refreshBtn = (Button) v.findViewById(R.id.btn_refresh);
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(getContext(), OrderDetailsActivity.class);
                i.putExtra("order", (Serializable) data.get(position));
                startActivity(i);
            }
        });
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadOrder();
            }
        });
        return v;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint() && data.size() == 0) {
            loadOrder();
        }
    }

    private void loadOrder() {
        final String address = IConst.SERVLET_ADDR + "MyOrder";
        String postData = "username=" + username;
        HttpUtil.sendHttpRequest(address, "POST", postData, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                List<Ticket> list = Utility.handleOrderListResponse(response);
                data.clear();
                data.addAll(list);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onError(Exception e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LogUtil.e("NET", address);
                        Toast.makeText(getContext(), R.string.http_fail, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}
