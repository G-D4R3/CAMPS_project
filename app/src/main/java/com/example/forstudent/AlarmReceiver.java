package com.example.forstudent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent = new Intent(context,AlarmService.class);
        serviceIntent.putExtra("year",intent.getStringExtra("year"));
        serviceIntent.putExtra("month",intent.getStringExtra("month"));
        serviceIntent.putExtra("day",intent.getStringExtra("day"));
        serviceIntent.putExtra("hour",intent.getStringExtra("hour"));
        serviceIntent.putExtra("minute",intent.getStringExtra("minute"));
        serviceIntent.putExtra("memo",intent.getStringExtra("memo"));
        context.startService(serviceIntent);

    }
}
