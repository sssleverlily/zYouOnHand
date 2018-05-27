package com.example.administrator.zyouonhand.Util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClassUtils {
    public static void setHttpConnection(final String address, final MyCallBack myCallback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection httpURLConnection = null;
                try {
                    URL url = new URL(address);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(1000);
                    httpURLConnection.setReadTimeout(1000);
                    InputStream in = httpURLConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    if (myCallback != null) {
                        myCallback.onComplete(response.toString());
                    }
                } catch (Exception e) {
                    if (myCallback != null) {
//                        myCallback.onError(e);
                    }
                } finally {
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                }
            }
        }).start();
    }

    public static String RandomColor(int pos) {
        String color = null;
        if(pos>=0&&pos<=15){
            color = "#FF69B4";//红
        }else if(pos>=16&&pos<=31){
            color = "#F0E68C";//黄
        }else {
            color = "#6495ED";//蓝
        }

        return color;
    }

    public static int px2dip(Context context, Float pxVaule) {
        Float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxVaule / scale + 0.5f);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    public static String newString(String Change) {
        char a[] = Change.toCharArray();
        String result = " ";
        int j = 0;
        while (a[j] != '-') {
            j++;
        }
        for (int i = ++j; i < Change.length(); i++) {
            if (a[i] == ' ')
                result += '\n';
            result += a[i];
        }
        return result;
    }


    public static String lessonCode(String all_content) {
        char a[] = all_content.toCharArray();
        String result = "";
        int j = 0;
        while (a[j] != ' ') {
            j++;
        }
        for (int i = 0; i < j; i++) {
            result += a[i];
        }
        return result;
    }

    public static void FileSave(String filename, String content, Context context) {
        FileOutputStream out = null;
        try {
            out = context.openFileOutput(filename, Context.MODE_PRIVATE);
            out.write(content.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String FileRead(String filename, Context context) {
        byte[] content = null;
        FileInputStream in = null;
        ByteArrayOutputStream bout = null;
        byte[] buf = new byte[1024];
        bout = new ByteArrayOutputStream();
        int length = 0;
        try {
            in = context.openFileInput(filename); //获得输入流
            while ((length = in.read(buf)) != -1) {
                bout.write(buf, 0, length);
            }
            content = bout.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            in.close();
            bout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            return new String(content,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
