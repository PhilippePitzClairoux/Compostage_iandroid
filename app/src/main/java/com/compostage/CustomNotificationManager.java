package com.compostage;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;


public class CustomNotificationManager {

    static NotificationManager manager;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static void sendNotifications(Context ctx, String title, String text) {

       manager  = (NotificationManager) ctx.getSystemService(ctx.NOTIFICATION_SERVICE);

        Intent intent = new Intent("com.compostage.notitfications.SECACTIVITY");

        PendingIntent pendingIntent = PendingIntent.getActivity(ctx, 1, intent, 0);

        Notification.Builder builder = new Notification.Builder(ctx);

        builder.setAutoCancel(false);
        builder.setContentTitle("Urgent Notification!");
        builder.setContentText(title);
        builder.setSmallIcon(R.drawable.ic_icon);
        builder.setContentIntent(pendingIntent);
        builder.setOngoing(true);
        builder.setSubText(text);   //API level 16
        builder.setNumber(100);
        builder.build();

        Notification myNotication = builder.getNotification();
        manager.notify(11, myNotication);

    }

}
