package com.example.forstudent;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import androidx.annotation.Nullable;

public class AlarmService extends IntentService {
    public static final int POLL_INTERVAL = 1000 * 5;
    public static final String TAG = "알람 테스트";
    public static Intent newIntent(Context context){
     return new Intent(context,AlarmService.class);
    }
    public static void setServiceAlarm(Context context, boolean isOn){
        Intent i = AlarmService.newIntent(context);
        PendingIntent pi = PendingIntent.getService(context,0,i,0);
        AlarmManager alarmManager=(AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if(isOn){
            alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(),POLL_INTERVAL,pi);

        }
        else{
            alarmManager.cancel(pi);
            pi.cancel();
        }
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }

    public AlarmService(String name) {
        super(name);
    }
}
