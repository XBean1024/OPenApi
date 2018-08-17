package com.binny.openapi.notification;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.NOTIFICATION_SERVICE;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.binny.openapi.APP;
import com.binny.openapi.R;
import com.binny.openapi.util.ValueUtils;
import com.orhanobut.logger.Logger;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;


/**
 * 通知栏管理类
 *
 * 适配 8.0
 *
 * 暂时支持跳转到某一个 Activity
 */
public class NotificationUtils {
    public static final String id = "channel_1";
    public static final String name = "default";
    public static final String BC_ACTION = "android.intent.action.MY_NOTIFICATION";
    @SuppressLint("StaticFieldLeak")
    private volatile static NotificationUtils mManager;
    private AlarmManager am;


    private Activity mActivity;
    private Activity mActivityTo;

    /**
     * @param activityTo 跳转到哪里
     */
    public NotificationUtils setActivityTo(Activity activityTo) {
        mActivityTo = activityTo;
        return this;
    }

    private int update = 0;

    private int requestCode;

    private List<PendingIntent> mPendingIntents = new ArrayList<>();//缓存 PendingIntent 用于取消 通知
    private List<Long> mRelativeTime = new ArrayList<>();//缓存 相对时长 用于取消 通知

    /**
     * 通知标题
     */
    private String mTitle;
    /**
     * 通知内容
     */
    private String mContent;
    /**
     * 释放 activity
     */
    public void releaseActivity() {
        mActivity = null;
    }

    private NotificationUtils() {

    }

    public static NotificationUtils getInstance() {
        if (mManager == null) {
            synchronized (NotificationUtils.class) {
                if (mManager == null) {
                    mManager = new NotificationUtils();
                }
            }
        }
        return mManager;
    }

    /**
     * @return 获取 NotificationUtils
     */
    private android.app.NotificationManager getNotificationManage() {
        return (android.app.NotificationManager) APP.mApp.getSystemService(NOTIFICATION_SERVICE);
    }


    /**
     * 初始化所需的参数
     */
    public NotificationUtils init(Activity activity) {
        if (mActivity == null) {
            mActivity = activity;

        }
        if (am == null) {
            am = (AlarmManager) APP.mApp.getSystemService(ALARM_SERVICE);
        }
        return this;
    }

    /**
     * @param title 设置的通知标题
     */
    public NotificationUtils setTitle(String title) {
        mTitle = title;
        return this;
    }

    /**
     * @param content 设置的通知内容
     */
    public NotificationUtils setContent(String content) {
        mContent = content;

        return this;
    }

    /**
     * 发送通知
     */
    public void sendNotification() {
        if (mActivity == null) {
            Logger.e("请先初始化！");
            return;
        }
        Intent intent = new Intent(mActivity,  mActivityTo.getClass());
        Bundle bundle = new Bundle();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        final PendingIntent resultPendingIntent = PendingIntent.getActivity(mActivity, update, intent, FLAG_UPDATE_CURRENT);
        update++;
        Notification.Builder builder = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            @SuppressLint("WrongConstant")
            NotificationChannel channel = new NotificationChannel(id, name, android.app.NotificationManager.IMPORTANCE_DEFAULT);
            channel.setShowBadge(false);
            channel.enableLights(true);
            channel.enableVibration(true);
            channel.setBypassDnd(true);
            getNotificationManage().createNotificationChannel(channel);
            builder = new Notification.Builder(APP.mApp.getApplicationContext(), id);

        } else {
            builder = new Notification.Builder(APP.mApp.getApplicationContext());
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            builder.setShowWhen(true);
        }
        builder.setContentTitle(mTitle)
                .setContentText(mContent)
                .setStyle(new Notification.BigTextStyle().bigText(mContent))
                .setSmallIcon(R.mipmap.app)
                .setAutoCancel(true)
                .setContentIntent(resultPendingIntent);
        Notification notification = builder.build();
        getNotificationManage().notify(0, notification);
    }

    /**
     * @param open 设置本地推送的状态，true 开启，false 关闭
     */
    public void toggle(boolean open) {
        if (!open) {
            //关闭
            close();
        } else {
            //开启
            open();
        }
    }

    /**
     * 关闭红包雨通知
     */
    private void close() {
        if (ValueUtils.isListEmpty(mPendingIntents)) {
            return;
        }
        int count = mPendingIntents.size();
        for (int i = 0; i < count; i++) {
            am.cancel(mPendingIntents.get(i));
        }
    }

    /**
     * 设置红包雨通知
     */
    private void open() {

        if (ValueUtils.isListNotEmpty(mRelativeTime)) {
            int size = mRelativeTime.size();
            for (int i = 0; i < size; i++) {
                long relativeTimes = mRelativeTime.get(i);
                if (relativeTimes > 0) {
                    setAlarmData(relativeTimes);
                }
            }
        }

    }

    /**
     * @param relativeTimes 相对时间
     */
    private void setAlarmData(long relativeTimes) {
        Log.i("设置", "多少秒后" + (relativeTimes / 1000 / 60) + "分，共 " + (relativeTimes / 1000) + "秒");
        requestCode++;
        Intent intent = new Intent(mActivity, AlarmReceiver.class);
        intent.setAction(BC_ACTION);
        intent.setComponent(new ComponentName("com.binny.openapi", "com.binny.openapi.notification.AlarmReceiver"));
        PendingIntent pi = PendingIntent.getBroadcast(mActivity, requestCode, intent, 0);
        mPendingIntents.add(pi);
        relativeTimes = SystemClock.elapsedRealtime() + relativeTimes;
        if (Build.VERSION.SDK_INT < 19) {
            am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, relativeTimes, pi);
        } else {
            am.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, relativeTimes, pi);
        }
    }

    /**
     * 设置一次闹钟的时间
     * <p>
     * 相对于当前系统的开机时长的时间
     * <p>
     * 开始时长为 t
     * <p>
     * 则在 t + relativeTimes 毫秒数后发出通知
     *
     * @param relativeTimesMill 相对于当前时间时间
     */
    public NotificationUtils setRelativeLongMill(long relativeTimesMill) {
        mRelativeTime.add(relativeTimesMill);
        return this;
    }

    /**
     * @param relativeTimesMills 闹钟偏移量
     */
    public NotificationUtils setRelativeLongMillList(List<Long> relativeTimesMills) {
        mRelativeTime.addAll(relativeTimesMills);
        return this;
    }

    /**
     * 打印当前系统时间
     *
     * @param lt 时间戳
     */
    private void printTime(long lt) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM:dd:hh:mm:ss");
        Date date = new Date(lt);
        String res = simpleDateFormat.format(date);
        Logger.i("设置", "系统时间为！" + (res) + "  ");
    }

    /**
     * 清除所有该应用之前发送的通知
     */
    public NotificationUtils clearOldNotification() {
        getNotificationManage().cancelAll();
        return this;
    }

}
