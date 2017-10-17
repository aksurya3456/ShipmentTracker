package com.msupply.shipmenttracker;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.app.ProgressDialog;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by abhishek on 5/10/17.
 */

public final class OTPValidationImplementer {
    private static final String TAG = "OTPValidationImpl";
    private static ProgressDialog pDialog;
    Context mContext;
    static int responseStatus;
    static String validateOTPurl = constants.validateOTPurl;
    static String resendOTPurl = constants.resendOTPurl;
    IOTPValidation mIotpValidation;


    public OTPValidationImplementer(Context context, IOTPValidation iOTPValidation, String mobile, String otp) {
        mContext = context;
        mIotpValidation = iOTPValidation;
        jsonRequest(mobile, otp);
    }

    private void jsonRequest(final String mobile, String otp) {
        Log.i(TAG, "jsonRequest()");
        pDialog = new ProgressDialog(mContext);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        showpDialog();
        Map<String, String> params = new HashMap();
        params.put("mobile", mobile);
        params.put("otp", otp);

        JSONObject jsonObject = new JSONObject(params);

        Log.i(TAG, "params : " + new Gson().toJson(jsonObject));

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                validateOTPurl, jsonObject, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "Response : " + response.toString());
                hidepDialog();
                try {
                    responseStatus = response.getInt("http_code");
                    Log.d(TAG, "responseStatus : " + responseStatus);
                    mIotpValidation.OTPResponse(responseStatus);
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
                mIotpValidation.displayError(error.getMessage());
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

    public OTPValidationImplementer(Context context, IOTPValidation iOTPValidation, String mobile) {
        mContext = context;
        mIotpValidation = iOTPValidation;
        resendJsonRequest(mobile);
    }

    private void resendJsonRequest(final String mobile) {
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
                resendOTPurl, jsonObject, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "Response : " + response.toString());
                hidepDialog();
                try {
                    responseStatus = response.getInt("http_code");
                    Log.d(TAG, "responseStatus : " + responseStatus);
                    mIotpValidation.resendOTPResponse(responseStatus);
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
                mIotpValidation.displayError(error.getMessage());
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
