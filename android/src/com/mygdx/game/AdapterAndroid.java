package com.mygdx.game;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AdapterAndroid implements NotificationHandler {


    private Activity gameActivity;
    public AdapterAndroid(Activity gameActivity){this.gameActivity= gameActivity;}

    private final static String TAG = "CheckRecentPlay";
    private static Long MILLISECS_PER_DAY = 86400000L;
    private static Long MILLISECS_PER_MIN = 60000L;
    private static long delay = MILLISECS_PER_MIN * 3;   // 3 minutes (for testing)


    public void onStarting(){

    }

    @SuppressLint("NewApi")
    @Override
    public void showNotification(String title, String text) {
        Notification.Builder mBuilder = new Notification.Builder(gameActivity)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(title)
                .setContentText(text);

        int notificationId = 1;
        NotificationManager notificationManager = (NotificationManager) gameActivity.getSystemService(gameActivity.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, mBuilder.build());
    }

}
