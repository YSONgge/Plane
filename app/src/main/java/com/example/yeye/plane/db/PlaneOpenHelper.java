package com.example.yeye.plane.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mzz on 2015/12/11.
 */
public class PlaneOpenHelper extends SQLiteOpenHelper {
    public PlaneOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static final String CREATE_PASSENGER = "create table passenger(" +
            "id integer primary key autoincrement," +
            "name text not null," +
            "id_card text not null" +
            ")";
    public static final String CREATE_CONTACT = "create table contact(" +
            "id integer primary key autoincrement," +
            "name text not null," +
            "phone_number text not null," +
            "email text not null" +
            ")";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CONTACT);
        db.execSQL(CREATE_PASSENGER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
