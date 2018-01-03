package com.msupply.shipmenttracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class TrackingDetails extends AppCompatActivity {

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_details);
        sessionManager = new SessionManager(this);
    }

    public void endTripButton(View v) {
        endTrip();
    }


    public void endTrip() {
        sessionManager.setKEY_SHIPMENT_STARTED_setFalse();
        Toast.makeText(this, R.string.end_trip_toast_message, Toast.LENGTH_SHORT).show();
        Intent trackingDetailsIntent = new Intent(this, ShipmentList.class);
        startActivity(trackingDetailsIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
