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

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class AlarmService extends Service {
    private int REQUEST_CODE = 1;
    public AlarmService(){

    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        String year = intent.getStringExtra("year");
        String month = intent.getStringExtra("month");
        String date = intent.getStringExtra("date");
        String hour = intent.getStringExtra("hour");
        String minute = intent.getStringExtra("minute");
        String memo = intent.getStringExtra("memo");

        Intent alarmIntent = new Intent(AlarmService.this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(AlarmService.this, 0, alarmIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notificationBuilder = null;
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = "default_channel_id";
            String channelDescription = "Default Channel";
            //NotificationChannel notificationChannel = notificationManager.getNotificationChannel(channelId);
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelDescription, NotificationManager.IMPORTANCE_HIGH);
            if (notificationChannel == null) {
                int importance = NotificationManager.IMPORTANCE_HIGH;
                notificationChannel = new NotificationChannel(channelId, channelDescription, importance);
                notificationChannel.setLightColor(Color.GREEN);
                notificationChannel.enableVibration(true);
                notificationManager.createNotificationChannel(notificationChannel);
            }
            notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("알람이다")
                    .setContentText("알람이야")
                    .setTicker("TICK")
                    .setContentIntent(pendingIntent);
        } else {
            notificationBuilder = new NotificationCompat.Builder(getApplicationContext()).
                    setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("알람이다")
                    .setContentText("알람이야")
                    .setTicker("TICK")
                    .setContentIntent(pendingIntent);
        }
        notificationManager.notify(0,notificationBuilder.build());
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
