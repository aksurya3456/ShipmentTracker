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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by abhishek on 17/10/17.
 */

public class ShipmentListImplementer {

    private static final String TAG = "ShipmentList";
    static int responseStatus;
    static String shipmentListUrl = constants.getShipmentListUrl;
    private static ProgressDialog pDialog;
    Context mContext;
    IShipmentList mIShipmentList;
    SessionManager sessionManager;

    public ShipmentListImplementer(Context context, IShipmentList iShipmentList) {
        mContext = context;
        mIShipmentList = iShipmentList;
        sessionManager = new SessionManager(mContext);
        String mobile = sessionManager.getKEY_MOBILE();
        if (mobile != null) {
            jsonRequest(mobile);
        } else {
            mIShipmentList.onSessionTimedOut();
        }
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

    private void jsonRequest(final String mobile) {
        Log.i(TAG, "jsonRequest()");
        pDialog = new ProgressDialog(mContext);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        showpDialog();
        final Map<String, String> params = new HashMap();
        params.put("mobile", mobile);


        JSONObject jsonObject = new JSONObject(params);

        Log.i(TAG, "params : " + new Gson().toJson(jsonObject));

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                shipmentListUrl, jsonObject, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "Response : " + response.toString());
                hidepDialog();

                try {
                    responseStatus = response.getInt("http_code");
                    Log.d(TAG, "responseStatus : " + responseStatus);

                    if (response.getInt("http_code") == 200) {

                        mIShipmentList.prepareListData(response.getJSONObject("message").getJSONArray("shipments"));
                    } else {
                        mIShipmentList.errorListener();
                    }

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
                //mIShipmentList.displayError(error.getMessage());
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

}
