package com.binny.openapi.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.binny.openapi.R;


public class NotificationUtils extends ContextWrapper {

    public static final String id = "channel_1";
    public static final String name = "default";

    public NotificationUtils(Context context) {
        super(context);
    }
    private NotificationManager getNotificationManage() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    public void sendNotification(String title, String content, PendingIntent intent) {
        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
            channel.setShowBadge(false);
            channel.enableLights(true);
            channel.enableVibration(true);
            getNotificationManage().createNotificationChannel(channel);
            Notification.Builder builder = new Notification.Builder(getApplicationContext(), id)
                    .setContentTitle(title)
                    .setContentText(content)
                    .setSmallIcon(R.mipmap.app)
                    .setAutoCancel(true);
            builder.setContentIntent(intent);
            Notification notification = builder.build();
            getNotificationManage().notify(1, notification);
        } else {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "default");
            builder.setContentTitle(title)
                    .setContentText(content)
                    .setSmallIcon(android.R.drawable.stat_notify_more)
                    .setContentIntent(intent)
                    .setAutoCancel(true);
            Notification notification = builder.build();
            getNotificationManage().notify(1, notification);
        }
    }
}

