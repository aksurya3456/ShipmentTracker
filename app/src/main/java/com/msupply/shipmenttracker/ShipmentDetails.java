package com.msupply.shipmenttracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ShipmentDetails extends AppCompatActivity {

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipment_details);

        sessionManager = new SessionManager(this);

        TextView info_text = (TextView) findViewById(R.id.info_details);
        info_text.setText(sessionManager.getKEY_SHIPMENT_ID());
    }
}
