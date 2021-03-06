package com.example.yeye.plane.entity;


import org.json.JSONException;
import org.json.JSONObject;


public class Flight {
    private String flightId;
    private int originId;
    private int destId;
    private String origin;
    private String dest;
    private String flightDate;
    private String flightStartTime;
    private String flightArriveTime;
    private int flightFare;

    public Flight(String origin, String dest, String flightDate) {
        super();
        this.origin = origin;
        this.dest = dest;
        this.flightDate = flightDate;
    }

    public Flight(String flightId, int originId, int destId,
                  String flightStartTime, String flightOrriveTime, int flightFare) {
        super();
        this.originId = originId;
        this.destId = destId;
        this.flightId = flightId;
        this.flightStartTime = flightStartTime;
        this.flightArriveTime = flightOrriveTime;
        this.flightFare = flightFare;
    }

    public JSONObject getJsonObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("flightId", flightId);
            json.put("originId", originId);
            json.put("destId", destId);
            json.put("flightStartTime", flightStartTime);
            json.put("flightArriveTime", flightArriveTime);
            json.put("flightFare", flightFare);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public int getOriginId() {
        return originId;
    }

    public void setOriginId(int originId) {
        this.originId = originId;
    }

    public int getDestId() {
        return destId;
    }

    public void setDestId(int destId) {
        this.destId = destId;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getFlightStartTime() {
        return flightStartTime;
    }

    public void setFlightStartTime(String flightStartTime) {
        this.flightStartTime = flightStartTime;
    }

    public String getFlightOrriveTime() {
        return flightArriveTime;
    }

    public void setFlightOrriveTime(String flightOrriveTime) {
        this.flightArriveTime = flightOrriveTime;
    }

    public int getFlightFare() {
        return flightFare;
    }

    public void setFlightFare(int flightFare) {
        this.flightFare = flightFare;
    }

}

