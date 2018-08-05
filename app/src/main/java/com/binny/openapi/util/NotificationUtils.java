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

    /**
     * @param title 标题
     * @param content 内容
     * @param intent PendingIntent
     */
    public void sendNotification(String title, String content, PendingIntent intent) {
        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
            channel.setShowBadge(false);
            channel.enableLights(true);
            channel.enableVibration(true);
            getNotificationManage().createNotificationChannel(channel);

        }
        //未适配 4.1一下的版本，若适配4.1一下的，用 支持包中的
        Notification.Builder builder = new Notification.Builder(getApplicationContext())
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.mipmap.app)
                .setAutoCancel(true);
        builder.setContentIntent(intent);
        Notification notification = builder.build();
        getNotificationManage().notify(1, notification);
    }
}

