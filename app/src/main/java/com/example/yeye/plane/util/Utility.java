package com.example.yeye.plane.util;

import android.text.TextUtils;
import android.util.Log;

import com.example.yeye.plane.enity.Ticket;

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
            LogUtil.e(TAG, e.getMessage());
        }
        return flag;
    }

    public static String handleAirportPhoneNumber(String response) {
        String TAG = "handleAirportPhoneNumber";
        JSONObject jsonObject;
        String number = null;
        try {
            jsonObject = new JSONObject(response);
            number = jsonObject.getString("aNumber");
        } catch (JSONException e) {
            e.printStackTrace();
            LogUtil.e(TAG, e.getMessage());
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
                Map<String, String> map = new HashMap<>();
                for (int j = 0; j < jsonArray.length(); j++) {
                    Log.i("name", jsonArray.getString(j));
                    Log.i("value", ticket.getString(jsonArray.getString(j)));
                    map.put(jsonArray.getString(j), ticket.getString(jsonArray.getString(j)));
                }
                list.add(map);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            LogUtil.e(TAG, e.getMessage());
        }
        return list;
    }

    public static List<Ticket> handleOrderListResponse(String response) {
        // TODO: 2015/12/14 finish
        List<Ticket> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Ticket t = new Ticket(jsonObject.getString("orderId"),
                        jsonObject.getString("pName"),
                        jsonObject.getString("pCardNumber"),
                        jsonObject.getString("pTicketType"),
                        jsonObject.getString("pInsurance"),
                        jsonObject.getString("cName"),
                        jsonObject.getString("cPhone"),
                        jsonObject.getString("cEmail"),
                        jsonObject.getString("UId"),
                        jsonObject.getString("flightId"));
                list.add(t);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            LogUtil.e("handleOrderListResponse", e.getMessage());
        }
        return list;
    }
}
