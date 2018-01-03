package com.msupply.shipmenttracker;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TrackingDetails extends AppCompatActivity {

    SessionManager sessionManager;
    TextView shipmentID;

    AlarmManager alarmManager;
    Intent alarmReceiverIntent;
    PendingIntent alarmIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_details);
        sessionManager = new SessionManager(this);
        shipmentID = (TextView) findViewById(R.id.tracking_shipment_id);
        shipmentID.setText(sessionManager.getKEY_SHIPMENT_ID());
        alarmManager = (AlarmManager) this.getSystemService(this.ALARM_SERVICE);


        Toast.makeText(this, "Tracking Details Page Loaded", Toast.LENGTH_SHORT).show();
        Log.i("Tracking Details Page", "Tracking Details Page Loaded");

        if (!sessionManager.getKEY_ALARM_SERVICE()) {
            startAlarm();
        }

    }

    public void startAlarmButton(View v) {
        startAlarm();
    }

    public void startAlarm() {

//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
//                System.currentTimeMillis() + 5000,
//                5000, alarmIntent);

        alarmReceiverIntent = new Intent(this.getApplicationContext(),
                AlarmBroadcastReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(this, 0, alarmReceiverIntent, 0);

        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 5000, alarmIntent);


        Toast.makeText(this, "Alarm Started", Toast.LENGTH_SHORT).show();
        Log.i("Tracking Details Page", "Alarm Started");
        sessionManager.setKEY_ALARM_SERVICE_true();


    }

    public void endAlarmButton(View v) {
        endAlarm();
    }

    public void endAlarm() {
        if (alarmManager != null) {
            alarmManager.cancel(alarmIntent);
            Toast.makeText(this, "Alarm Cancelled", Toast.LENGTH_SHORT).show();
            Log.i("Tracking Details Page", "Alarm Cancelled");
        } else {
            Toast.makeText(this, "No Alarm Found", Toast.LENGTH_SHORT).show();
            Log.i("Tracking Details Page", "No Alarm Found");
        }
        sessionManager.setKEY_ALARM_SERVICE_false();

    }

    public void endTripButton(View v) {
        endTrip();
    }


    public void endTrip() {
        sessionManager.setKEY_SHIPMENT_STARTED_setFalse();
        Toast.makeText(this, R.string.end_trip_toast_message, Toast.LENGTH_SHORT).show();
        Intent shipmentListIntent;
        shipmentListIntent = new Intent(this, ShipmentList.class);
        startActivity(shipmentListIntent);
        finish();
    }

    public void minimiseButton(View v) {
        onBackPressed();
    }


    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
