package com.msupply.shipmenttracker;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by abhishek on 9/10/17.
 */

public class SessionManager {

    private final String PREF_NAME = "shipmentTracker";
    private final String KEY_LOGIN = "isLoggedIn";
    private final String KEY_MOBILE = "mobile";
    private final String KEY_SHIPMENT_ID = "shipmentId";
    private final String KEY_PICKUP_DATE = "pickUpDate";
    private final String KEY_DELIVERY_DATE = "deliveryDate";
    private final String KEY_SHIPMENT_STARTED = "shipmentStarted";
    private final String KEY_ALARM_SERVICE = "alarmServiceON";
    SharedPreferences.Editor edit;
    private Context _context;
    private SharedPreferences sharedPreferences;

    public SessionManager(Context context)
    {
        _context = context;
        sharedPreferences = _context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        edit = sharedPreferences.edit();
    }

    public void setLogin(String mobile)
    {
        edit.putBoolean(KEY_LOGIN,true);
        edit.putString(KEY_MOBILE,mobile);
        edit.commit();
    }

    public String getKEY_SHIPMENT_ID() {
        return sharedPreferences.getString(KEY_SHIPMENT_ID, null);
    }

    public void setKEY_SHIPMENT_ID(String shipmentId) {
        edit.putString(KEY_SHIPMENT_ID, shipmentId);
        edit.commit();
    }

    public void clear()
    {
        edit.clear();
        edit.commit();
    }

    public boolean isLoggedIn()
    {
        return sharedPreferences.getBoolean(KEY_LOGIN, false);
    }

    public String getKEY_MOBILE()
    {
        return sharedPreferences.getString(KEY_MOBILE, null);
    }

    public String getKEY_DELIVERY_DATE() {
        return sharedPreferences.getString(KEY_DELIVERY_DATE, null);
    }

    public void setKEY_DELIVERY_DATE(String delivery_date) {
        edit.putString(KEY_DELIVERY_DATE, delivery_date);
        edit.commit();
    }

    public String getKEY_PICKUP_DATE() {
        return sharedPreferences.getString(KEY_PICKUP_DATE, null);
    }

    public void setKEY_PICKUP_DATE(String pickup_date) {
        edit.putString(KEY_PICKUP_DATE, pickup_date);
        edit.commit();
    }

    public void setKEY_SHIPMENT_STARTED_setTrue() {
        edit.putBoolean(KEY_SHIPMENT_STARTED, true);
        edit.commit();
    }

    public void setKEY_SHIPMENT_STARTED_setFalse() {
        edit.putBoolean(KEY_SHIPMENT_STARTED, false);
        edit.commit();
    }

    public boolean getKEY_SHIPMENT_STARTED() {
        return sharedPreferences.getBoolean(KEY_SHIPMENT_STARTED, false);
    }

    public void setKEY_ALARM_SERVICE_true() {
        edit.putBoolean(KEY_ALARM_SERVICE, true);
        edit.commit();
    }

    public void setKEY_ALARM_SERVICE_false() {
        edit.putBoolean(KEY_ALARM_SERVICE, false);
        edit.commit();
    }

    public boolean getKEY_ALARM_SERVICE() {
        return sharedPreferences.getBoolean(KEY_ALARM_SERVICE, false);
    }
}
