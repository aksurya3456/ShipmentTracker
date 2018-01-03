package com.msupply.shipmenttracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

    public void agreeButtonClick(View view) {
        //Toast.makeText(this,"Agree Button Clicked", Toast.LENGTH_SHORT).show();

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int choice) {
                switch (choice) {
                    case DialogInterface.BUTTON_POSITIVE:
                        positiveButtonClickListen();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        negativeButtonClickListen();
                }

            }
        };

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(R.string.warning_message_before_start_tracking)
                .setCancelable(false)
                .setPositiveButton(R.string.shipment_details_page_yes_button_text, dialogClickListener)
                .setNegativeButton(R.string.shipment_details_page_no_button_text, dialogClickListener)
                .show();
    }

    public void positiveButtonClickListen() {
        sessionManager.setKEY_SHIPMENT_STARTED_setTrue();
        Toast.makeText(this, R.string.shipment_details_page_yes_button_clicked, Toast.LENGTH_SHORT).show();
        Intent trackingActivityIntent = new Intent(this, TrackingDetails.class);
        startActivity(trackingActivityIntent);
    }

    public void negativeButtonClickListen() {
        Toast.makeText(this, R.string.shipment_details_page_no_button_clicked, Toast.LENGTH_SHORT).show();
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
