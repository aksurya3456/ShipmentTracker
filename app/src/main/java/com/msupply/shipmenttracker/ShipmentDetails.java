package com.msupply.shipmenttracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ShipmentDetails extends AppCompatActivity {

    private SessionManager sessionManager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipment_details);

        /*toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });*/

        sessionManager = new SessionManager(this);

        TextView info_text = (TextView) findViewById(R.id.info_details);
        info_text.setText(sessionManager.getKEY_SHIPMENT_ID()/* +
        " " + sessionManager.getKEY_PICKUP_DATE() + " " +
        sessionManager.getKEY_DELIVERY_DATE()*/);
    }


    public void goBack(View view) {
        onBackPressed();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }
}
