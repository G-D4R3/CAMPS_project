package com.example.forstudent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent = new Intent(context,AlarmService.class);
        serviceIntent.putExtra("year",intent.getIntExtra("year",1));
        serviceIntent.putExtra("month",intent.getIntExtra("month",1));
        serviceIntent.putExtra("day",intent.getIntExtra("day",1));
        serviceIntent.putExtra("hour",intent.getIntExtra("hour",1));
        serviceIntent.putExtra("minute",intent.getIntExtra("minute",1));
        serviceIntent.putExtra("title",intent.getStringExtra("title"));
        serviceIntent.putExtra("memo",intent.getStringExtra("memo"));
        context.startService(serviceIntent);

    }
}
