package com.example.administrator.zyouonhand.other;

import android.util.Log;

import com.example.administrator.zyouonhand.Util.MyCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUrl implements MyCallBack{

    public static void sendHttpRequest(final String api, final String param, final MyCallBack callback){
//        if (!NetWorkUrl.isNetworkConnected(ZApplication.getContext())){
//            System.out.println("当前无网络连接");
//        }else {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try{
                    URL url = new URL(api);
                    connection = (HttpURLConnection)url.openConnection();
                    connection.setReadTimeout(5 * 1000);
                    connection.setConnectTimeout(10 * 1000);
                    if (param == null)
                        connection.setRequestMethod("GET");
                    else{
                        connection.setRequestMethod("POST");
                        connection.setDoInput(true);
                        connection.setDoOutput(true);
                        OutputStream os = connection.getOutputStream();
                        os.write(param.getBytes("UTF-8"));
                        os.flush();
                        os.close();
                    }
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    try {
                        JSONObject object = new JSONObject(response.toString());
                        int status = object.getInt("status");
                        String info = object.getString("info");
                        Log.d("response",""+response);
                        if (status == 200 && callback != null){
                            callback.onComplete(response.toString());
                        }else {
                            callback.onError(info);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } catch (MalformedURLException e) {
                    if (callback != null)
                        callback.onError(e.toString());
                } catch (IOException e) {
                    if (callback != null)
                        callback.onError(e.toString());
                }finally {
                    if (connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    @Override
    public void onComplete(String response) {

    }

    @Override
    public void onError(String e) {

    }
}
