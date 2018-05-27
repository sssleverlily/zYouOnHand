package com.example.administrator.zyouonhand.Activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.administrator.zyouonhand.Adapter.ViewPagerChangedListenter;
import com.example.administrator.zyouonhand.Adapter.ViewPagerFragmentAdapter;
import com.example.administrator.zyouonhand.Fragment.ClassFragment;
import com.example.administrator.zyouonhand.Fragment.FindFragment;
import com.example.administrator.zyouonhand.Fragment.MyFragment;
import com.example.administrator.zyouonhand.Fragment.Qa_feel_fragment;
import com.example.administrator.zyouonhand.Fragment.Qa_life_fragment;
import com.example.administrator.zyouonhand.Fragment.Qa_study_fragment;
import com.example.administrator.zyouonhand.Fragment.Qa_whole_fragment;
import com.example.administrator.zyouonhand.Fragment.YouwenFragment;
import com.example.administrator.zyouonhand.R;
import com.example.administrator.zyouonhand.other.MyViewPager;

import java.util.ArrayList;

public class YouwenActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    private Button bt_whole;
    private Button bt_study;
    private Button bt_life;
    private Button bt_feel;

    private MyViewPager viewPager;
    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;
    private FragmentManager fragmentManager;
    private ViewPagerChangedListenter listenter;

    //    放四个碎片的容器
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qa_app_bar_main);

        initBtn();
        initFragment();
        initPager();
    }

    private void initBtn() {

        bt_whole = findViewById(R.id.bt_whole);
        bt_study = findViewById(R.id.bt_study);
        bt_life = findViewById(R.id.bt_life);
        bt_feel = findViewById(R.id.bt_feel);

        bt_whole.setOnClickListener(this);
        bt_study.setOnClickListener(this);
        bt_life.setOnClickListener(this);
        bt_feel.setOnClickListener(this);


        bt_whole.getPaint().setFakeBoldText(true);

    }

    private void initFragment(){
        Fragment f1 = new Qa_whole_fragment();
        Fragment f2 = new Qa_study_fragment();
        Fragment f3 = new Qa_life_fragment();
        Fragment f4 = new Qa_feel_fragment();

        fragments.add(f1);
        fragments.add(f2);
        fragments.add(f3);
        fragments.add(f4);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_whole:
                Drawable  blue = getResources().getDrawable(R.mipmap.dot_yes);
                blue.setBounds(0, 0, blue.getMinimumWidth(), blue.getMinimumHeight());
                bt_whole.setCompoundDrawables(null, blue, null, null);
                viewPager.setCurrentItem(0);
                break;
            case R.id.bt_study:
                Drawable  blue1 = getResources().getDrawable(R.mipmap.dot_yes);
                blue1.setBounds(0, 0, blue1.getMinimumWidth(), blue1.getMinimumHeight());
                bt_study.setCompoundDrawables(null, blue1, null, null);
                viewPager.setCurrentItem(1);
                break;
            case R.id.bt_life:
                Drawable  blue2 = getResources().getDrawable(R.mipmap.dot_yes);
                blue2.setBounds(0, 0, blue2.getMinimumWidth(), blue2.getMinimumHeight());
                bt_life.setCompoundDrawables(null, blue2, null, null);
                viewPager.setCurrentItem(2);
                break;
            case R.id.bt_feel:
                Drawable  blue3 = getResources().getDrawable(R.mipmap.dot_yes);
                blue3.setBounds(0, 0, blue3.getMinimumWidth(), blue3.getMinimumHeight());
                bt_feel.setCompoundDrawables(null, blue3, null, null);
                viewPager.setCurrentItem(3);
                break;
            default:
                break;
        }
    }
        private void initPager(){
            listenter = new ViewPagerChangedListenter();
            fragmentManager = getSupportFragmentManager();
            viewPager = (MyViewPager) findViewById(R.id.qa_viewpager);
            viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(fragmentManager,fragments);

            listenter.setContext(this);
            listenter.setAddBtn(bt_whole);
            listenter.setAddBtn(bt_study);
            listenter.setAddBtn(bt_life);
            listenter.setAddBtn(bt_feel);

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



