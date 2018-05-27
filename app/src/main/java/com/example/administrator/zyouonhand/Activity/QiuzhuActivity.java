package com.example.administrator.zyouonhand.Activity;


import android.app.Dialog;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.administrator.zyouonhand.R;

public class QiuzhuActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton bt_shezhi;
    private ImageButton bt_topic;
    private ImageButton bt_camara;
    private Button bt_yes;
    private View view;
    private Button Button1;
    private Button Button2;
    private Button Button3;
    private Button Button4;
    private Button Button5;
    private Button Button6;
    private TextView textView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qiuzhu_click);
        bt_topic = findViewById(R.id.imageButton10);
        bt_yes = findViewById(R.id.yes);



        bt_topic.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                dialog();

            }
        });

    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void dialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.add_topic_click, null);
        final PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT, true);
        Button1 = view.findViewById(R.id.dawu);
        Button2 = view.findViewById(R.id.english);
        Button3 = view.findViewById(R.id.xiandai);
        Button4 = view.findViewById(R.id.gaoshu);
        Button5 = view.findViewById(R.id.jihe);
        Button6 = view.findViewById(R.id.sixiu);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
         int screenWidth = dm.widthPixels;
         int screenHeigh = dm.heightPixels;


        int x = 0;
        int y = screenHeigh;

        popupWindow.showAsDropDown(view, Gravity.BOTTOM, x, y);

        //pw对话框设置半透明背景。原理：pw显示时，改变整个窗口的透明度为0.7，当pw消失时，透明度为1
        final WindowManager.LayoutParams params = QiuzhuActivity.this.getWindow().getAttributes();
        params.alpha = 0.5f;
        QiuzhuActivity.this.getWindow().setAttributes(params);



        view.findViewById(R.id.yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                isExit = true;
                popupWindow.dismiss();
                params.alpha = 1f;
                QiuzhuActivity.this.getWindow().setAttributes(params);
            }
        });

        //pw对话框消失监听事件
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1f;
                QiuzhuActivity.this.getWindow().setAttributes(params);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dawu:
                textView.setText("#"+Button1.getText()+"#");
                textView.setTextColor(Color.parseColor("#6495ED"));
                break;
            case R.id.english:
                textView.setText("#"+Button2.getText()+"#");
                textView.setTextColor(Color.parseColor("#6495ED"));
                break;
            case R.id.xiandai:
                textView.setText("#"+Button3.getText()+"#");
                textView.setTextColor(Color.parseColor("#6495ED"));
                break;
            case R.id.gaoshu:
                textView.setText("#"+Button4.getText()+"#");
                textView.setTextColor(Color.parseColor("#6495ED"));
                break;
            case R.id.jihe:
                textView.setText("#"+Button5.getText()+"#");
                textView.setTextColor(Color.parseColor("#6495ED"));
                break;
            case R.id.sixiu:
                textView.setText("#"+Button6.getText()+"#");
                textView.setTextColor(Color.parseColor("#6495ED"));
                break;
            default:
                break;
        }
    }
}
