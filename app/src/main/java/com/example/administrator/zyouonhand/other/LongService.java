package com.example.administrator.zyouonhand.other;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Calendar;

public class LongService extends Service{
    private long time;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SharedPreferences sp = getSharedPreferences("Timer",MODE_PRIVATE);
        time = sp.getLong("setTime",0L);
        intent = new Intent(this, RealService.class);
        intent.setAction(ALARM_SERVICE);
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        PendingIntent pi = PendingIntent.getBroadcast(this,0,intent,0);
        Calendar c = Calendar.getInstance();
        am.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis() + time,pi);
        return super.onStartCommand(intent, flags, startId);
    }
}
