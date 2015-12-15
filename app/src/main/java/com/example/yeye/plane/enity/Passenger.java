package com.example.yeye.plane.enity;

import android.text.TextUtils;

/**
 * Created by Mzz on 2015/12/14.
 */
public class Passenger {
    private String name;
    private String idCard;

    public Passenger(String name, String idCard) {
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(idCard)) {
            throw new NullPointerException("name or idCard can't be null or ''");
        }
        if (idCard.length() != 18) {
            throw new IllegalArgumentException("idCard length must be 18");
        }
        this.name = name;
        this.idCard = idCard;
    }

    public String getIdCard() {

        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
