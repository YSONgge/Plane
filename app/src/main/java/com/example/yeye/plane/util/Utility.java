package com.example.yeye.plane.util;

import android.text.TextUtils;
import android.util.Log;

import com.example.yeye.plane.entity.Flight;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mzz on 2015/12/10.
 */
public class Utility {
    /**
     * handle response which is a json contains a boolean result.
     *
     * @param response
     * @return
     */
    public static synchronized boolean handleBooleanResultResponse(String response) {
        String TAG = "handleBooleanResultResponse";
        boolean flag = false;
        if (TextUtils.isEmpty(response)) {
            return flag;
        }
        try {
            LogUtil.d(TAG, response);
            JSONObject jsonObject = new JSONObject(response);
            flag = jsonObject.getBoolean("result");
        } catch (JSONException e) {
            e.printStackTrace();
            //// TODO: 2015/12/10 confirm error message
            LogUtil.e(TAG, e.getStackTrace().toString());
        }
        return flag;
    }

    public static String handleAirportPhoneNumber(String response) {
        String TAG = "handleAirportPhoneNumber";
        JSONObject jsonObject = null;
        String number = null;
        try {
            jsonObject = new JSONObject(response);
            number = jsonObject.getString("aNumber");
        } catch (JSONException e) {
            e.printStackTrace();
            LogUtil.e(TAG, e.getStackTrace().toString());
        }
        return number;
    }

    public static List<Map<String, String>> handleTicketResultResponse(String response) {
        String TAG = "handleTicketResultResponse";
        List<Map<String, String>> list = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(response);
            for (int i = 0; i < array.length(); i++) {
                JSONObject ticket = array.getJSONObject(i);
                JSONArray jsonArray = ticket.names();

                for (int j = 0; j < jsonArray.length(); j++) {
                    Map<String, String> map = new HashMap<>();
                    Log.i("name", jsonArray.getString(i));
                    Log.i("value", ticket.getString(jsonArray.getString(i)));
                    map.put(jsonArray.getString(i), ticket.getString(jsonArray.getString(i)));
                    list.add(map);
                }
               /* String flightId = ticket.getString("flightId");
                int origin = ticket.getInt("originId");
                int dest = ticket.getInt("destId");
                String flightStartTime = ticket.getString("flightStartTime");
                String flightArriveTime = ticket.getString("flightArriveTime");
                int flightFare = ticket.getInt("flightFare");

                list.add(new Flight(flightId, origin, dest, flightStartTime, flightArriveTime, flightFare));*/
            }

        } catch (JSONException e) {
            e.printStackTrace();
            LogUtil.e(TAG, e.getStackTrace().toString());
        }
        return list;
    }
}
