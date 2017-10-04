package com.msupply.shipmenttracker;


import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import org.json.JSONObject;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by abhishek on 4/10/17.
 */

public final class MainActivityImplementer {

    private static final String TAG = "MainActivityImplementer";
    static String responseReturn;
    static String url = constants.baseUrl + "requestOTP.php";

    private static ProgressDialog pDialog;


    public static String jsonRequest(Context context, final String mobile){
        Log.i(TAG,"jsonRequest()");
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        showpDialog();
        Map<String, String> params = new HashMap();
        params.put("mobile", mobile);

        JSONObject jsonObject = new JSONObject(params);


        Log.i(TAG,"params : " + new Gson().toJson(jsonObject));
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, jsonObject, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "Response : " +response.toString());
                hidepDialog();
                responseReturn = response.toString();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                hidepDialog();
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                responseReturn = error.toString();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap();
                headers.put("Content-Type","application/json");
                return headers;
            }
        };
        //Log.i(TAG,"request : " + new Gson().toJson(jsonObjReq));
        Log.i(TAG,"request : " + jsonObjReq.toString());
        AppController.getInstance().addToRequestQueue(jsonObjReq);
        return responseReturn;
    }

    private static void showpDialog() {
        Log.i(TAG,"showpDialog()");
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private static void hidepDialog() {
        Log.i(TAG,"hidepDialog()");
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


}
