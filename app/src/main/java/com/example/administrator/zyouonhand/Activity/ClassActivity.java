package com.example.administrator.zyouonhand.Activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.zyouonhand.Adapter.ClassAdapter;
import com.example.administrator.zyouonhand.Bean.TableBean;
import com.example.administrator.zyouonhand.R;
import com.example.administrator.zyouonhand.Util.ClassTime;
import com.example.administrator.zyouonhand.Util.ClassUtils;
import com.example.administrator.zyouonhand.Util.MyCallBack;
import com.example.administrator.zyouonhand.other.LongService;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class ClassActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private String searchType = "";
    private String searchId = "";
    private RecyclerView recyclerView;
    private List<TableBean> showTables = new ArrayList<>();
    private ClassAdapter adapter;
    private final static int saveData = 7;
    private SharedPreferences sp;
    private List<String> mList = new ArrayList(){};

    private ArrayAdapter arrayAdapter;
    private EditText mEditText;
    private Spinner mSpinner;

    private Handler handler = new Handler(new Handler.Callback() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public boolean handleMessage(Message message) {
            //~~~~~~~分割得到数据存储~~~~~~~~~~~~~~~~~~~~~~~~
            if (message.what == saveData) {
                SharedPreferences.Editor editor = getSharedPreferences("saveContent", MODE_PRIVATE)
                        .edit();
                editor.putString("type", searchType);
                editor.putString("content", message.toString());
                Log.d("MainActivitys", "handleMessage: " + message.toString());
                editor.apply();
            }
            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            updataT(message.toString(), searchType, message.what);
            return true;
        }
    });


    @android.support.annotation.RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.class_main);

        /**
         * spinner初始化数据
         */

        mList.add("不提前");
        mList.add("提前10分钟");
        mList.add("提前20分钟");
        mList.add("提前半小时");
        mList.add("提前一小时");

        //~~~~~~~~~~~~~~~~~~~~~~~~~

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("本周");
        setSupportActionBar(toolbar);

        TextView setDay = (TextView) findViewById(R.id.week_day);
        setDay.setText("第" + (ClassTime.getDate() / 7 + 1) + "周");
        adapter = new ClassAdapter(this, this, showTables);
        recyclerView = (RecyclerView) findViewById(R.id.show_table_recycler);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(8, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

//        学号存储，不必二次输入
        sp = getSharedPreferences("saveContent", MODE_PRIVATE);
        if (Objects.equals(sp.getString("type", " "), " ")) {
            showNotice();
        } else {
            Log.d("sp:", sp.getString("type", " "));
            updataT(sp.getString("content", " "), sp.getString("type", " "), 1);
        }
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        adapter.setOnItemClickListener(new ClassAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, final int position) {
                if (!Objects.equals(showTables.get(position).getTextView(), "")) {
                    LayoutInflater inflater = getLayoutInflater();
                    final View layout = inflater.inflate(R.layout.alarm,
                            (ViewGroup) findViewById(R.id.item_sel));
                    mSpinner = (Spinner) layout.findViewById(R.id.spinner);
                    arrayAdapter = new ArrayAdapter(getBaseContext(),android.R.layout.simple_spinner_item,mList);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mSpinner.setAdapter(arrayAdapter);

                    new AlertDialog.Builder(ClassActivity.this).setTitle("提前")
                            .setView(layout)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    int pos = mSpinner.getSelectedItemPosition();
                                    long time = cuTime(position);
                                    if(time > 0){
                                        setNoti(time);
                                        showToast(ClassActivity.this,"设置成功",2500);
                                    }
                                    else showToast(ClassActivity.this,"时间有点错误",2500);

                                }
                            })
                            .setNegativeButton("取消", null).show();
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.content_refresh) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static void showToast(final Activity activity, final String word, final long time) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                final Toast toast = Toast.makeText(activity, word, Toast.LENGTH_LONG);
                toast.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        toast.cancel();
                    }
                }, time);
            }
        });
    }

    /**
     * 对类型以及学号的输入编辑
     */
    private void showNotice() {
        final EditText editText = new EditText(this);
        Intent intent = getIntent();
        final String z = intent.getStringExtra("stunum");
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
//        new AlertDialog.Builder(this).setTitle("输入：")
//                .setView(editText)
//                .setNegativeButton("学生", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
                        searchType = "student";
                        searchId = z;
                        ClassUtils.setHttpConnection("http://jwzx.cqupt.congm.in/jwzxtmp/kebiao/kb_stu.php?xh=" + searchId,
                                new MyCallBack() {
                                    @Override
                                    public void onComplete(String response) {
                                        Message message = new Message();
                                        message.obj = response;
                                        message.what = saveData;
                                        handler.sendMessage(message);
                                    }

                                    @Override
                                    public void onError(String e) {

                                    }
                                });
//                    }
//                });
    }

    /**
     * 对数据进行更新
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void updataT(String tableData, String type, int what) {
        Document doc = Jsoup.parse(tableData);
        Elements es = (Elements) doc.getElementsByClass("printTable");
        Elements ele = es.select("td");
        for (int i = 8; i < 72; i++) {
            Element e = (Element) ele.get(i);
            String content = e.text();
        if (Objects.equals(type, "student") && content.length() > 20) {
                showTables.add(new TableBean(" ", ClassUtils.newString(content)));
            } else if (Objects.equals(type, "student") && content.length() < 20) {
                showTables.add(new TableBean(" ", content));
            }
        }

        adapter.notifyDataSetChanged();
    }

    /**
     * 时间的设定相差等等
     *
     * @param time
     */
    private void setNoti(long time) {
        SharedPreferences.Editor editor = getSharedPreferences("Timer", MODE_PRIVATE)
                .edit();
        editor.putLong("setTime", time);
        editor.apply();
        Log.d("Main:", "setNoti: " + time);
        Intent intent = new Intent(ClassActivity.this, LongService.class);
        startService(intent);
    }


    /**
     * @param position
     * @return two date's days
     */
    private long cuTime(int position) {
        Calendar calendar = Calendar.getInstance();
        long time = 0;
        int weekday = position % 8;//星期几
        //需要0(1.2..8),1(3.4..10),3(5.6..14),4(7.8..16),6(9.10..19)
        int lesson = position / 8;

        //现在的毫秒数
        long now = calendar.getTimeInMillis();

        //当前年月日时分
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR);
        //1.2.3.4.5.6.7.周日到周六
        int week = calendar.get(Calendar.DAY_OF_WEEK);

        //weekday%7+1    1.2.3.4.5.6.7.周日到周六
        int newHour = 0;
        if ((weekday % 7 + 1) < week) {
            switch (lesson) {
                case 0:
                    newHour = 8;
                    break;
                case 1:
                    newHour = 10;
                    break;
                case 3:
                    newHour = 14;
                    break;
                case 4:
                    newHour = 16;
                    break;
                case 6:
                    newHour = 19;
                    break;
            }
            calendar.set(year, month, day - (week - weekday % 7 - 1) + 7, newHour, 0);
            time = calendar.getTimeInMillis() - now;
        } else if ((weekday % 7 + 1) == week) {
            switch (lesson) {
                case 0:
                    if (hour > 8) day = day + 7;
                    calendar.set(year, month, day, 8, 0);
                    time = calendar.getTimeInMillis() - now;
                    break;
                case 1:
                    if (hour > 10) day = day + 7;
                    calendar.set(year, month, day, 10, 0);
                    time = calendar.getTimeInMillis() - now;
                    break;
                case 3:
                    if (hour > 14) day = day + 7;
                    calendar.set(year, month, day, 14, 0);
                    time = calendar.getTimeInMillis() - now;
                    break;
                case 4:
                    if (hour > 16) day = day + 7;
                    calendar.set(year, month, day, 16, 0);
                    time = calendar.getTimeInMillis() - now;
                    break;
                case 6:
                    if (hour > 19) day = day + 7;
                    calendar.set(year, month, day, 19, 0);
                    time = calendar.getTimeInMillis() - now;
                    break;

            }
        } else {
            switch (lesson) {
                case 0:
                    newHour = 8;
                    break;
                case 1:
                    newHour = 10;
                    break;
                case 3:
                    newHour = 14;
                    break;
                case 4:
                    newHour = 16;
                    break;
                case 6:
                    newHour = 19;
                    break;
            }
            calendar.set(year, month, day + (weekday % 7 + 1 - week), newHour, 0);
            time = calendar.getTimeInMillis() - now;
        }

        return 1000;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
