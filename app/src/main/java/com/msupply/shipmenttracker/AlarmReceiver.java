package com.msupply.shipmenttracker;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AlarmReceiver extends Service {
    public AlarmReceiver() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.

        /*Toast.makeText(this,"Alarm Fired",Toast.LENGTH_SHORT).show();
        Log.d("Alarm Receiver","Alarm Received Triggered");*/
        throw new UnsupportedOperationException("Not yet implemented");

    }
}
