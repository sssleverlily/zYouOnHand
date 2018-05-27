package com.example.administrator.zyouonhand.Util;

import java.util.Calendar;

public class ClassTime {
    public static String getTime() {
        String month1 ;
        String minute ;
        String second ;
        String hour  ;
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        month = month + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int h = c.get(Calendar.HOUR_OF_DAY);
        int m = c.get(Calendar.MINUTE);
        int t = c.get(Calendar.SECOND);
        if(h<10){
            hour = "0"+h;
        }
        else
            hour = String.valueOf(h);
        if(month<10) {
            month1 = "0" + month;
        }
        else
            month1 = String.valueOf(month);
        if(m<10){
            minute  = "0" + m;
        }
        else
            minute = String.valueOf(m);
        if(t<10){
            second = "0" + t;
        }
        else
            second = String.valueOf(t);
        String time = year + ""+ month1 + "" + day + "" + hour + "" + minute + "" + second;
        return time;
    }

    //与2018.3.5   相差时间的计算
    public static long getDate(){
        int monthHave[] = {31,0,31,30,31,30,31,31,30,31,30,31};
        int allYearDay = 0;
        int initYear = 2018;
        int initMonth = 3;
        int initDay = 5;
        long theTime = 0;
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        month = month + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);

        if((year % 4 == 0 && year % 100 !=0) || year % 400 == 0){
            allYearDay = 366;
            monthHave[1] = 29;
        }
        else{
            allYearDay = 365;
            monthHave[1] = 28;
        }

        if(year == initYear){
            if(month == initMonth){
                if(day > initDay){
                    theTime = day-initDay;
                }
            }else {
                switch (month){
                    case 10:theTime = 30 - initDay + day;
                        break;
                    case 11:theTime = 30 - initDay + 31 + day;
                        break;
                    case 12:theTime = 30 - initDay + day + 31 + 30;
                        break;
                }
            }
        }
        else {
            theTime = 30 - initDay + 31 + 30 + 31;
            for(int i = 1;i < month;i++){
                theTime += monthHave[i];
            }
            theTime += day;
        }
        System.out.println(allYearDay);
        return theTime;
    }

}
