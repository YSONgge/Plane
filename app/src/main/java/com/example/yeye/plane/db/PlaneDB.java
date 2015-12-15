package com.example.yeye.plane.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.yeye.plane.enity.Contact;
import com.example.yeye.plane.enity.Passenger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mzz on 2015/12/14.
 */
public class PlaneDB {
    public static final String DB_NAME = "Plane";
    public static final int VERSION = 1;
    private static PlaneDB planeDB;
    private SQLiteDatabase db;

    private PlaneDB(Context context) {
        PlaneOpenHelper dbHelper = new PlaneOpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    public synchronized static PlaneDB getInstance(Context context) {
        if (planeDB == null) {
            planeDB = new PlaneDB(context);
        }
        return planeDB;
    }

    public void savePassenger(Passenger passenger) {
        if (passenger != null) {
            ContentValues values = new ContentValues();
            values.put("name", passenger.getName());
            values.put("id_card", passenger.getIdCard());
            db.insert("passenger", null, values);
        }
    }


    public void saveContact(Contact contact) {
        if (contact != null) {
            ContentValues values = new ContentValues();
            values.put("name", contact.getName());
            values.put("email", contact.getEmail());
            values.put("phone_number", contact.getPhoneNumber());
            db.insert("contact", null, values);
        }
    }

    public List<Passenger> loadPassenger() {
        List<Passenger> list = new ArrayList<>();
        Cursor c = db.query("passenger", null, null, null, null, null, null);
        while (c.moveToNext()) {
            String name = c.getString(c.getColumnIndex("name"));
            String idCard = c.getString(c.getColumnIndex("id_card"));
            Passenger p = new Passenger(name, idCard);
            list.add(p);
        }
        close(c);
        return list;
    }

    public List<Contact> loadContact() {
        List<Contact> list = new ArrayList<>();
        Cursor c = db.query("contact", null, null, null, null, null, null);
        while(c.moveToNext()){
            String name = c.getString(c.getColumnIndex("name"));
            String email = c.getString(c.getColumnIndex("email"));
            String phoneNumber = c.getString(c.getColumnIndex("phone_number"));
            Contact contact = new Contact(name, phoneNumber, email);
            list.add(contact);
        }
        close(c);
        return list;
    }

    private void close(Cursor cursor) {
        if(cursor != null){
            cursor.close();
        }
    }
}
