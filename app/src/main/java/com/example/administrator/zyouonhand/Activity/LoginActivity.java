package com.example.administrator.zyouonhand.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.administrator.zyouonhand.Adapter.ClassAdapter;
import com.example.administrator.zyouonhand.Bean.TableBean;
import com.example.administrator.zyouonhand.R;
import com.example.administrator.zyouonhand.Util.ClassUtils;
import com.example.administrator.zyouonhand.Util.JsonUtil;
import com.example.administrator.zyouonhand.Util.MyCallBack;
import com.example.administrator.zyouonhand.other.HttpUrl;
import com.example.administrator.zyouonhand.other.Zapplication;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity{
    private EditText username;
    private EditText password;
    private Button login;
    private String searchType = "";
    private String searchId = "";
    private final static int saveData = 7;
    private SharedPreferences sp;
    private List<String> mList = new ArrayList(){};


    private Handler handler = new Handler(new Handler.Callback() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public boolean handleMessage(Message message) {
            if (message.what == saveData) {
                SharedPreferences.Editor editor = getSharedPreferences("saveContent", MODE_PRIVATE)
                        .edit();
                editor.putString("type", searchType);
                editor.putString("content", message.toString());
                Log.d("MainActivitys", "handleMessage: " + message.toString());
                editor.apply();
            }
            return true;
        }
    });

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                String psw = password.getText().toString();
                final String username1 = username.toString();
                final String password1 = password.toString();
//                JsonUtil jsonUtil = new JsonUtil();
//                jsonUtil.getStu(jsonUtil.jsonback("https://wx.idsbllp.cn/api/verify"));
               if(name.equals("")||psw.equals("")){
                    Toast.makeText(LoginActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                    username.setText("");
                    password.setText("");
                }else {
                    searchType = "student";
                    ClassUtils.setHttpConnection("http://jwzx.cqupt.congm.in/jwzxtmp/kebiao/kb_stu.php?xh=" + name,
                            new MyCallBack() {
                                @Override
                                public void onComplete(String response) {
                                    Message message = new Message();
                                    message.obj = response;
                                    message.what = saveData;
                                    handler.sendMessage(message);
                                    Zapplication zapplication =new Zapplication();
                                    zapplication.setUsername(username1);
                                    zapplication.setPassword(password1);
                                    Intent intent = new Intent(LoginActivity.this,ClassActivity.class);
                                    intent.putExtra("stunum",username1);
//                                            intent.putExtra("password",password1);
                                    startActivity(intent);
                                }

                                @Override
                                public void onError(String e) {
                                    Toast.makeText(LoginActivity.this,"学号不存在，请检查输入",Toast.LENGTH_SHORT).show();

                                }

                            });


                }




            }
        });
    }


}
