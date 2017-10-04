package com.msupply.shipmenttracker;

import android.app.DownloadManager;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by abhishek on 4/10/17.
 */

public final class MainActivityImplementer {


    static String responseReturn;

    public static String generateOTP(Context context, final String mobile)
    {
        String url = constants.baseUrl + "requestOTP.php";
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        MainActivityImplementer.responseCompleted(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }

        ){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("mobile", mobile);
                return params;
            }
        };

        queue.add(request);

        return responseReturn;
    }


    public static void responseCompleted(String response)
    {
        MainActivityImplementer.responseReturn = response;
    }
}
