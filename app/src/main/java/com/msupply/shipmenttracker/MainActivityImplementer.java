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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.HashMap;

/**
 * Created by abhishek on 4/10/17.
 */

public final class MainActivityImplementer {

    private static final String TAG = "MainActivityImplementer";
    static String responseReturn;
    static int responseStatus;
    static String url = constants.requestOTPurl;

    private static ProgressDialog pDialog;
    private IMainActivityView mIMainActivityView;
    private Context mContext;

    public MainActivityImplementer(Context context, IMainActivityView iMainActivityView, String mobileNumber) {
        mContext = context;
        mIMainActivityView = iMainActivityView;
        jsonRequest(mobileNumber);
    }

    private void jsonRequest(final String mobile) {
        Log.i(TAG, "jsonRequest()");
        pDialog = new ProgressDialog(mContext);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        showpDialog();
        Map<String, String> params = new HashMap();
        params.put("mobile", mobile);

        JSONObject jsonObject = new JSONObject(params);

        Log.i(TAG, "params : " + new Gson().toJson(jsonObject));

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, jsonObject, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "Response : " + response.toString());
                hidepDialog();
                try {
                    responseStatus = response.getInt("http_code");
                    Log.d(TAG, "responseStatus : " + responseStatus);
                    mIMainActivityView.displayReturnedValue(responseStatus);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                hidepDialog();
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Log.d(TAG, "Error: " + error.getMessage());
                mIMainActivityView.displayError(error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        Log.i(TAG, "request using Gson() : " + new Gson().toJson(jsonObjReq));
        Log.i(TAG, "request using toString() : " + jsonObjReq.toString());
        Log.i(TAG, "request direct : " + jsonObjReq);

        AppController.getInstance().addToRequestQueue(jsonObjReq);

    }

    static void showpDialog() {
        Log.i(TAG, "showpDialog()");
        if (!pDialog.isShowing())
            pDialog.show();
    }

    static void hidepDialog() {
        Log.i(TAG, "hidepDialog()");
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


}
