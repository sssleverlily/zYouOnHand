package com.example.administrator.zyouonhand.other;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.example.administrator.zyouonhand.Activity.ClassActivity;
import com.example.administrator.zyouonhand.R;

public class RealService extends BroadcastReceiver {
    private NotificationManager manager;

    @Override
    public void onReceive(Context context, Intent intent) {
        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        intent.setClass(context, ClassActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setTicker("有提醒")
                .setContentTitle("提醒：")
                .setContentText("同学记得要上课咯")
                .setSmallIcon(R.mipmap.login)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        manager.notify(1, builder.build());
    }

}
