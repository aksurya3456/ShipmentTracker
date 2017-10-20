package com.msupply.shipmenttracker;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class ShipmentList extends AppCompatActivity implements IShipmentList {

    private static final String TAG = "ShipmentList";
    SessionManager sessionManager;
    private List<shipmentListRow> shipmentRowList = new ArrayList<>();
    private RecyclerView recyclerView;
    private shipmentListAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipment_list);

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/


        //If session is timed out, this will load the homescreen
        sessionChecker();

        //Initiate list adapter
        initiator();

    }

    private void initiator() {

        //findViewById(R.id.action_layout).setBackgroundColor(Color.CYAN);

        recyclerView = (RecyclerView) findViewById(R.id.rv_shipment);
        recyclerView.setBackgroundColor(Color.WHITE);
        recyclerView.setVisibility(View.GONE);
        findViewById(R.id.error_message).setVisibility(View.GONE);
        mAdapter = new shipmentListAdapter(shipmentRowList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        recyclerView.addItemDecoration(itemDecoration);

        ShipmentListImplementer mShipmentListImplementer = new ShipmentListImplementer(this, this);
        //prepareListData();

    }


    public void refresh(View v) {
        sessionChecker();
        initiator();
    }

    public void close(View v) {
        onBackPressed();
    }


    private void sessionChecker() {
        //insert code for redirecting to shipping progress if shipping is on the way
        sessionManager = new SessionManager(this);
        if (!sessionManager.isLoggedIn()) onSessionTimedOut();
    }

    @Override
    public void prepareListData(JSONArray list) {
        shipmentListRow mShipmentListRow;
        findViewById(R.id.rv_shipment).setVisibility(View.VISIBLE);


        for (int i = 0; i < list.length(); i++) {
            try {
                String shipmentId = list.getJSONObject(i).getString("id");
                String pickUpDate = list.getJSONObject(i).getString("pickUpDate");
                String deliveryDate = list.getJSONObject(i).getString("deliveryDate");
                mShipmentListRow = new shipmentListRow(shipmentId, pickUpDate, deliveryDate);
                Log.i(TAG, "Shipment Id : " + shipmentId + ", Pickup Date : " + pickUpDate + ", Delivery Date : " + deliveryDate);
                shipmentRowList.add(mShipmentListRow);
            } catch (JSONException e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
        /*mShipmentListRow = new shipmentListRow("id 2", "Date 21", "Date 22");
        shipmentRowList.add(mShipmentListRow);
        */
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void onSessionTimedOut() {
        sessionManager = new SessionManager(getApplicationContext());
        sessionManager.clear();
        Intent mainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(mainActivityIntent);
        finish();
    }

    @Override
    public void errorListener() {
        findViewById(R.id.error_message).setVisibility(View.VISIBLE);
    }

    @Override
    public void buttonClickListener(String shipmentID) {
        Toast.makeText(this, "Shipment Id: " + shipmentID, Toast.LENGTH_SHORT).show();
        Log.i(TAG, "Row clicked for shipment ID: " + shipmentID);

        sessionManager.setKEY_SHIPMENT_ID(shipmentID);

        //while(!sessionManager.getKEY_SHIPMENT_ID().equals(shipmentID)){}

        Intent shipmentDetailsIntent = new Intent(this, ShipmentDetails.class);
        startActivity(shipmentDetailsIntent);

    }


    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    public void APIError(VolleyError error) {
        Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show();
    }

    public void clear(View v) {
        onSessionTimedOut();
    }
}
