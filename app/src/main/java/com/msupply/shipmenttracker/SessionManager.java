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
    private Context _context;

    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor edit;

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

}
