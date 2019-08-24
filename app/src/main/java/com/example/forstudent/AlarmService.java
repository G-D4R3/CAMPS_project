package com.example.forstudent;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class AlarmService extends Service {
    private int REQUEST_CODE = 1;
    public AlarmService(){

    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        PowerManager powerManager;
        PowerManager.WakeLock wakeLock;
        int year = intent.getIntExtra("year",1);
        int month = intent.getIntExtra("month",1);
        int date = intent.getIntExtra("date",1);
        int hour = intent.getIntExtra("hour",1);
        int minute = intent.getIntExtra("minute",1);
        String title = intent.getStringExtra("title");
        String memo = intent.getStringExtra("memo");



        Intent alarmIntent = new Intent(AlarmService.this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(AlarmService.this, 0, alarmIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notificationBuilder = null;
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = "default_channel";
            String channelDescription = "Default Channel";
            NotificationChannel notificationChannel=null;
            //NotificationChannel notificationChannel = notificationManager.getNotificationChannel(channelId);
           // NotificationChannel notificationChannel = new NotificationChannel(channelId, channelDescription, NotificationManager.IMPORTANCE_HIGH);
            if (notificationChannel == null) {
                int importance = NotificationManager.IMPORTANCE_HIGH;
                notificationChannel = new NotificationChannel(channelId, channelDescription, importance);
                notificationChannel.setLightColor(Color.GREEN);
                notificationChannel.enableVibration(true);
                notificationManager.createNotificationChannel(notificationChannel);
            }
            notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle(title)
                    .setContentText(memo)
                    .setTicker("TICK")
                    .setContentIntent(pendingIntent);
        } else {
            notificationBuilder = new NotificationCompat.Builder(getApplicationContext()).
                    setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle(title)
                    .setContentText(memo)
                    .setTicker("TICK")
                    .setContentIntent(pendingIntent);
        }
        notificationManager.notify(0,notificationBuilder.build());
        powerManager = (PowerManager)getSystemService(Context.POWER_SERVICE);

        wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, "WAKELOCK");

        wakeLock.acquire(); // WakeLock 깨우기

        wakeLock.release(); // WakeLock 해제

        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
