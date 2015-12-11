package com.example.yeye.plane.util;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

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
}
