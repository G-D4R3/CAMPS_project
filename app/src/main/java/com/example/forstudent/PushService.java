package com.example.forstudent;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class PushService extends Service {
    NotificationManager Notifi_M;
    ServiceThread thread;
    ServiceThread thread2;
    Notification Notifi ;
    PowerManager powerManager;
    PowerManager.WakeLock wakeLock;


    public String messageTitle =null;
    public String messageText = null;

    boolean FLAG = true;

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

            messageTitle = intent.getStringExtra("messageTitle");
            messageText = intent.getStringExtra("messageText");
            Notifi_M = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            myServiceHandler handler = new myServiceHandler();
            thread = new ServiceThread(handler);
            thread.start();


        return START_STICKY;
    }

    //서비스가 종료될 때 할 작업

    public void onDestroy() {
        thread.stopForever();
        thread = null;//쓰레기 값을 만들어서 빠르게 회수하라고 null을 넣어줌.
    }

    class myServiceHandler extends Handler {
        @Override
        public void handleMessage(android.os.Message msg) {
            Intent intent = new Intent(PushService.this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(PushService.this, 0, intent,PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder notificationBuilder = null;
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            {
                String channelId = "default_channel_id";
                String channelDescription = "Default Channel";
                NotificationChannel notificationChannel = notificationManager.getNotificationChannel(channelId);
                if (notificationChannel == null) {
                    int importance = NotificationManager.IMPORTANCE_HIGH;
                    notificationChannel = new NotificationChannel(channelId, channelDescription, importance);
                    notificationChannel.setLightColor(Color.GREEN);
                    notificationChannel.enableVibration(true);
                    notificationManager.createNotificationChannel(notificationChannel);
                }
                notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(messageTitle)
                .setContentText(messageText)
                .setTicker("TICK")
                .setContentIntent(pendingIntent);
            } else {
                notificationBuilder = new NotificationCompat.Builder(getApplicationContext()).
                        setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle(messageTitle)
                        .setContentText(messageText)
                        .setTicker("TICK")
                        .setContentIntent(pendingIntent);
            }
            notificationManager.notify(0,notificationBuilder.build());
/*
            Notifi = new Notification.Builder(getApplicationContext())
                    .setContentTitle("Content Title")
                    .setContentText("Content Text")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setTicker("알림!!!")
                    .setContentIntent(pendingIntent)
                    .build();

            //소리추가
            Notifi.defaults = Notification.DEFAULT_SOUND;

            //알림 소리를 한번만 내도록
            Notifi.flags = Notification.FLAG_ONLY_ALERT_ONCE;

            //확인하면 자동으로 알림이 제거 되도록
            Notifi.flags = Notification.FLAG_AUTO_CANCEL;


            Notifi_M.notify( 777 , Notifi);
*/
            powerManager = (PowerManager)getSystemService(Context.POWER_SERVICE);

            wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, "WAKELOCK");


            wakeLock.acquire(); // WakeLock 깨우기

            wakeLock.release(); // WakeLock 해제



            //토스트 띄우기
            Toast.makeText(PushService.this, "뜸?", Toast.LENGTH_LONG).show();
        }
    };


}
