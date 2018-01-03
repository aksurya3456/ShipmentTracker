package com.msupply.shipmenttracker;


import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmBroadcastReceiver extends BroadcastReceiver {

    AlarmManager alarmManager;

    @SuppressLint("LongLogTag")
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context.getApplicationContext(), "Alarm Fired", Toast.LENGTH_SHORT).show();
        Log.d("Alarm Receiver", "Alarm Receiver Triggered");
        //Log.i("intent name",intent.toString());

        alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);

        Intent alarmReceiverIntent = new Intent(context.getApplicationContext(),
                AlarmBroadcastReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, alarmReceiverIntent, 0);

        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 30000, alarmIntent);


    }
}
