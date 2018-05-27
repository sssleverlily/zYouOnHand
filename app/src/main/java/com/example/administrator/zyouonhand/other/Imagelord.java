package com.example.administrator.zyouonhand.other;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Imagelord {
    private ImageView imageview1;
    private Handler handler;

    public void imagehandler(final ImageView imageview, final String zurl){
        //将下载的图片更新到UI
        handler = new Handler(){
            public void handleMessage(Message message){
                Bitmap bitmap = (Bitmap) message.obj;
                imageview.setImageBitmap(bitmap);
            }
        };

        //下载图片
        Runnable downloadRun = new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                downloadPicture(handler,zurl);
            }
        };
        new Thread(downloadRun).start();
    }

    /*
     * 访问网络不能在主程序中进行,所以用Runnable()
     */




    private void downloadPicture(Handler handler,String urlStr){
        HttpURLConnection conn = null;
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();

            conn.setConnectTimeout(5 * 1000);
            conn.setReadTimeout(10 * 1000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            InputStream in = conn.getInputStream();
//            InputStream in =   conn.getErrorStream();
            System.out.println("我来看一看"+in);
            Bitmap bm = BitmapFactory.decodeStream(in);
            Message message = Message.obtain();
            message.obj = bm;
            handler.sendMessage(message);

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    public void showImg(ImageView imageView,final Handler handler,String urlStr){
        imagehandler(imageView,urlStr);

    }

}
