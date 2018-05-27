package com.example.administrator.zyouonhand.other;

import android.app.Activity;

import java.util.ArrayList;

public class ActivityCollector {
    private static ArrayList<Activity> activities = new ArrayList<>();

    private static Activity activity;

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void setWelcomeActivity(Activity a){
        activity = a;
    }

    public static void finishWelComeActivity(){
        if(activity != null){
            activity.finish();
        }
    }

    public static void finishAll(){
        for (Activity a:activities){
            if(a != null){
                a.finish();
            }
        }
    }
}
