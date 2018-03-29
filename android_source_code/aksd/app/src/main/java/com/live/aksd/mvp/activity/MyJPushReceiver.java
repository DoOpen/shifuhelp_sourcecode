package com.live.aksd.mvp.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.live.aksd.MainActivity;
import com.live.aksd.R;

import cn.jpush.android.api.JPushInterface;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * @author Created by stone
 * @since 2017/11/1
 */

public class MyJPushReceiver extends BroadcastReceiver {

    public MyJPushReceiver() {
    }

    private static final String TAG = "JPush------------------------";
    private static final int NOTIFICATION_SHOW_SHOW_AT_MOST = 3;   //推送通知最多显示条数

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle =intent.getExtras();
        if(intent.getAction().equals(JPushInterface.ACTION_NOTIFICATION_RECEIVED)){
            Log.i(TAG, "接收到了通知");
            playSound(context);
            String title=bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
            String content=bundle.getString(JPushInterface.EXTRA_ALERT);
            String extra=bundle.getString(JPushInterface.EXTRA_EXTRA);
            Log.i(TAG, "标题:【"+title+"】，内容：【"+content+"】，附加参数:【"+extra+"】");
        }else if(intent.getAction().equals(JPushInterface.ACTION_MESSAGE_RECEIVED)){
            Log.i(TAG, "接收到了消息");
            playSound(context);
            String message =bundle.getString(JPushInterface.EXTRA_MESSAGE);
            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            NotificationCompat.Builder builder =new NotificationCompat.Builder(context);//NotificationCompat是v7包下的类，来处理新老版本的兼容问题
            builder.setContentTitle("师傅上门Pro")
                    .setContentText(message)
                    .setTicker(message)
                    .setPriority(Notification.PRIORITY_DEFAULT)
                    .setSmallIcon(R.mipmap.logo);//这个属性是自定义通知里面必须要传递的，否则通知不显示
            Notification notification=builder.build();
            Intent notificationIntent =new Intent(context, MainActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(context,0, notificationIntent,0);//PendingIntent获取的是活动
            notification.contentIntent= contentIntent;//把跳转意图赋值给通知
            notification.flags=Notification.FLAG_AUTO_CANCEL;//这句代码的意思是当点击通知跳转到新的activity后，该通知消失
            mNotificationManager.notify(1, notification);
            Log.i(TAG, "接收到的消息是:【"+message+"】");
        }else if(intent.getAction().equals(JPushInterface.ACTION_NOTIFICATION_OPENED)){
            Log.i(TAG, "用户正在打开通知");
        }
    }
    public void playSound(Context context) {
        MediaPlayer mediaPlayer = MediaPlayer.create(context.getApplicationContext(), R.raw.message);
        mediaPlayer.start();
    }

}
