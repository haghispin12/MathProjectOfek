package com.example.mathprojectofek;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class Music extends Service {
    public Music() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //end
        Log.d("testService","stop");
    }

    private void startForegroundService() {
        String channelId;

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){

            channelId = createNotificationChannel();

        } else {

// If earlier version channel ID is not used

// https://developer.android.com/reference/android/support/v4/app/NotificationCompat.Builder.html#NotificationCompat.Builder(android.content.Context)

            channelId = "";

        }
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,channelId);
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId);

        Notification notification = notificationBuilder.setOngoing(true)

                .setSmallIcon(R.mipmap.ic_launcher)

                .setPriority(NotificationCompat.PRIORITY_MIN)

                .setCategory(Notification.CATEGORY_SERVICE)

                .build();

        startForeground(101, notification);
    }

private String createNotificationChannel() {
    String channelId = "my_service";

    String channelName = "My Background Service";

    NotificationChannel chan = new NotificationChannel(channelId,

            channelName, NotificationManager.IMPORTANCE_HIGH);

    chan.setLightColor(Color.BLUE);

    chan.setImportance(NotificationManager.IMPORTANCE_NONE);

    chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

    NotificationManager service = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

    service.createNotificationChannel(chan);

    return channelId;
}

@Override
    public void onCreate() {
        super.onCreate();
        //start
        Log.d("testService","start");
        startForegroundService();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}