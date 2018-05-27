package com.example.administrator.zyouonhand.Util;

import android.util.Log;
import android.widget.LinearLayout;

import com.example.administrator.zyouonhand.Bean.AnswerBean;
import com.example.administrator.zyouonhand.Bean.Qa_whole_list_bean;
import com.example.administrator.zyouonhand.Bean.QuestionCare;
import com.example.administrator.zyouonhand.Bean.StuBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
    private String response;

    //通过指定的url获取json数据
    public String jsonback(final String url){
        Thread back =  new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                Reader read;
                BufferedReader bufferReader;
                try {
                    URL url1 = new URL(url);
                    connection = (HttpURLConnection) url1.openConnection();
                    connection.setConnectTimeout(8000);
                    connection.setRequestMethod("GET");
                    InputStream in = connection.getInputStream();
                    read=new InputStreamReader(connection.getInputStream());
                    bufferReader=new BufferedReader(read);
                    //获取服务器返回的字符串
                    String str;//读取每一行数据
                    StringBuffer buffer=new StringBuffer();//接受全部数据
                    while((str=bufferReader.readLine())!=null){
                        buffer.append(str + "\n");
                    }
                    //关闭连接
                    read.close();
                    connection.disconnect();
                    //测试
                    Log.d("发出去的请求",url.toString());
                    Log.d("读取来的数据",buffer.toString());
                    response = buffer.toString();

                } catch (Exception e) {
                    Log.d("TAG",e.toString());
//                    return null;
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
//        String response = null;
        });

        back.start();
        try {
            back.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }

    public List getStu(String response1){
        List<StuBean.zData> stulist = new ArrayList<>();
        StuBean stuBean  = new StuBean();
        try {
            JSONObject obj = new JSONObject(response1);
            JSONObject obj1 = obj.getJSONObject("data");
            StuBean.zData zData = stuBean.new zData();
            zData.setStunum(obj1.getString("stuNum"));
            zData.setIdnum(obj1.getString("idNum"));
            stulist.add(zData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return stulist;

    }


    public List getCareStu(String response2){
        List<StuBean.zData> stucarelist = new ArrayList<>();
        StuBean stuBean  = new StuBean();
        try {
            JSONObject obj = new JSONObject(response2);
            JSONObject obj1 = obj.getJSONObject("data");
            StuBean.zData zData = stuBean.new zData();
            zData.setStunum(obj1.getString("stunum"));
            zData.setUsername(obj1.getString("username"));
            zData.setIntroduction(obj1.getString("introduction"));
            zData.setNickname(obj1.getString("nickname"));
            zData.setGender(obj1.getString("gender"));
            zData.setPhoto_thumsrc(obj1.getString("photo_thumbnail_src"));
            zData.setPhoto_src(obj1.getString("photo_src"));
            zData.setUpdatetime(obj1.getString("update_time"));
            zData.setPhone(obj1.getString("phone"));
            zData.setQq(obj1.getString("qq"));
            stucarelist.add(zData);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return stucarelist;

    }


    public List getqalist(String response3){
        List<Qa_whole_list_bean.zData> qalist = new ArrayList<>();
        Qa_whole_list_bean qa_whole_list_bean = new Qa_whole_list_bean();
        try {
            JSONObject obj = new JSONObject(response3);
            JSONArray jsonArray = obj.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj1 = jsonArray.getJSONObject(i);
                Qa_whole_list_bean.zData zData = qa_whole_list_bean.new zData();
                zData.setTitle(obj1.getString("title"));
                zData.setContent(obj1.getString("description"));
                zData.setTopic(obj1.getString("kind"));
                zData.setMisstime(obj1.getString("disappear_at"));
                String sub = obj1.getString("photo_thumbnail_src").replaceAll("http","https");
                zData.setHeadimg(sub);
                zData.setJifen(obj1.getString("reward"));
                zData.setNickname(obj1.getString("nickname"));
                qalist.add(zData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return qalist;

    }

    public String getPicurl(String response){
        String res = null;
        String zres = null;
        String sub = null;
        List<Qa_whole_list_bean.zData> qalist = new ArrayList<>();
        Qa_whole_list_bean qa_whole_list_bean = new Qa_whole_list_bean();
        try {
            JSONObject obj = new JSONObject(response);
            JSONObject obj1 = obj.getJSONObject("data");
            Qa_whole_list_bean.zData zData = qa_whole_list_bean.new zData();
            zData.setHeadimg(obj1.getString("photo_thumbnail_src"));
            zres = res.substring(1,res.length()-1);
//            sub = zres.replaceAll("\\\\","");
            sub = zres.replaceAll("http","https");
            qalist.add(zData);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  sub;
    }

    public QuestionCare getCarequestion(String response){
        QuestionCare questionCare = new QuestionCare();
        List questionurl = new ArrayList();
        try {
            JSONObject obj = new JSONObject(response);
            questionCare.setDescription(obj.getString("discreption"));
            questionCare.setGender(obj.getString("gender"));
            questionCare.setIs_self(obj.getInt("is_self"));
            questionCare.setKind(obj.getString("kind"));
            questionCare.setMisstime(obj.getString("disappear_at"));
            questionCare.setNickname(obj.getString("nickname"));
            questionCare.setReward(obj.getString("reward"));
            questionCare.setTags(obj.getString("tags"));
            String z = obj.getString("questioner_photo_thumbnail_src");
            z.replaceAll("http","https");
            questionCare.setPhoto_thumbnail_src(z);
            JSONArray jsonArray = obj.getJSONArray("photo_urls");
            for (int i = 0; i < jsonArray.length(); i++) {
                String url = jsonArray.getString(i);
                url.replaceAll("http","https");
                questionurl.add(url);
            }

            questionCare.setPhoto_urls(questionurl);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return questionCare;

    }


    public AnswerBean getAnswer(String response){
        List answerurl = new ArrayList();
        AnswerBean answerBean = new AnswerBean();
        try {
            JSONObject obj = new JSONObject(response);
            JSONArray jsonArray = obj.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj1 = jsonArray.getJSONObject(i);
                answerBean.setContent(obj1.getString("content"));
                answerBean.setCreat_at(obj1.getString("created_at"));
                answerBean.setNickname(obj1.getString("nickname"));
                answerBean.setGender(obj1.getString("gender"));
                answerBean.setPhoto_thumbnail_src(obj1.getString("photo_thumbnail_src"));
                JSONArray jsonArray1 = obj.getJSONArray("photo_urls");
                for (int j = 0; j < jsonArray1.length(); j++) {
                    String url = jsonArray.getString(i);
                    url.replaceAll("http","https");
                    answerurl.add(url);
                }
                answerBean.setPhoto_url(answerurl);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return answerBean;
    }

}
