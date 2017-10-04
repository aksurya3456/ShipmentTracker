package com.msupply.shipmenttracker;

import android.app.DownloadManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by abhishek on 4/10/17.
 */

public final class ConnectionManager {

    private static final String baseUrl = "http://10.1.1.187/API/";
    RequestQueue requestQueue = Volley.newRequestQueue(null);

    public static boolean generateOTP(String mobile)
    {




        return true;
    }
}
