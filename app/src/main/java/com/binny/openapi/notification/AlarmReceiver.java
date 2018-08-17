package com.binny.openapi.notification;

import static com.binny.openapi.notification.NotificationUtils.BC_ACTION;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 红包通知接收器
 * 向通知栏发送红包通知
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("设置", "onReceive: ");
        if (intent.getAction()!=null && intent.getAction().equals(BC_ACTION)) {
            NotificationUtils.getInstance().sendNotification();
        }
    }
}