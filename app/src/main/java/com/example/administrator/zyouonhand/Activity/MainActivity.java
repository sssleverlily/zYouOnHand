package com.example.administrator.zyouonhand.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.administrator.zyouonhand.Adapter.ViewPagerChangedListenter;
import com.example.administrator.zyouonhand.Adapter.ViewPagerFragmentAdapter;
import com.example.administrator.zyouonhand.Fragment.ClassFragment;
import com.example.administrator.zyouonhand.Fragment.FindFragment;
import com.example.administrator.zyouonhand.Fragment.MyFragment;
import com.example.administrator.zyouonhand.Fragment.YouwenFragment;
import com.example.administrator.zyouonhand.R;
import com.example.administrator.zyouonhand.other.MyViewPager;

import java.util.ArrayList;

import static com.example.administrator.zyouonhand.R.id.bt_class;


/*
主界面
 */

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener{
    private Button bt_class;
    private Button bt_qa;
    private Button bt_find;
    private Button bt_my;
    private MyViewPager viewPager;
    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;
    private FragmentManager fragmentManager;
    private ViewPagerChangedListenter listenter;
    private int count =0;

    //    放四个碎片的容器
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        initBtn();
        initFragment();
        initPager();
    }

    private void initBtn() {
//        Typeface iconfont = Typeface.createFromAsset(this.getAssets(),"C:\\Users\\Administrator\\AndroidStudioProjects\\zYouOnHand\\app\\src\\main\\java\\com\\example\\administrator\\zyouonhand\\assets\\four\\iconfont.ttf");

        bt_class = findViewById(R.id.bt_class);
        bt_qa = findViewById(R.id.bt_qa);
        bt_find = findViewById(R.id.bt_find);
        bt_my = findViewById(R.id.bt_my);

//        bt_class.setTypeface(iconfont);
//        bt_qa.setTypeface(iconfont);
//        bt_find.setTypeface(iconfont);
//        bt_my.setTypeface(iconfont);

        bt_class.setOnClickListener(this);
        bt_qa.setOnClickListener(this);
        bt_find.setOnClickListener(this);
        bt_my.setOnClickListener(this);

    }

    private void initFragment(){
        Fragment f1 = new ClassFragment();
        Fragment f2 = new YouwenFragment();
        Fragment f3 = new FindFragment();
        Fragment f4 = new MyFragment();

        fragments.add(f1);
        fragments.add(f2);
        fragments.add(f3);
        fragments.add(f4);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_class:
                Drawable class1 = getResources().getDrawable(R.mipmap.classclass);
                class1.setBounds(0, 0, class1.getMinimumWidth(), class1.getMinimumHeight());
                bt_class.setCompoundDrawables(null, class1, null, null);
                viewPager.setCurrentItem(0);
                break;
            case R.id.bt_qa:
                Drawable qa = getResources().getDrawable(R.mipmap.askask);
                qa.setBounds(0, 0, qa.getMinimumWidth(), qa.getMinimumHeight());
                bt_qa.setCompoundDrawables(null, qa, null, null);
                viewPager.setCurrentItem(1);
                break;
            case R.id.bt_find:
                Drawable find = getResources().getDrawable(R.mipmap.findfind);
                find.setBounds(0, 0, find.getMinimumWidth(), find.getMinimumHeight());
                bt_find.setCompoundDrawables(null, find, null, null);
                viewPager.setCurrentItem(2);
                break;
            case R.id.bt_my:
                Drawable mymy = getResources().getDrawable(R.mipmap.mymy);
                mymy.setBounds(0, 0, mymy.getMinimumWidth(), mymy.getMinimumHeight());
                bt_my.setCompoundDrawables(null, mymy, null, null);
                viewPager.setCurrentItem(3);
                break;
            default:
                break;
        }

    }

    private void initPager(){
        listenter = new ViewPagerChangedListenter();
        fragmentManager = getSupportFragmentManager();
        viewPager = (MyViewPager) findViewById(R.id.main_viewpager);
        viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(fragmentManager,fragments);

        listenter.setContext(this);
        listenter.setAddBtn(bt_class);
        listenter.setAddBtn(bt_qa);
        listenter.setAddBtn(bt_find);
        listenter.setAddBtn(bt_my);

        viewPager.setScanScroll(false);
        viewPager.setAdapter(viewPagerFragmentAdapter);
        viewPager.addOnPageChangeListener(listenter);
        viewPager.setCurrentItem(0);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
