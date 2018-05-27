package com.example.administrator.zyouonhand.Adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.widget.Button;

import com.example.administrator.zyouonhand.R;

import java.util.ArrayList;

public class ViewPagerChangedListenter implements ViewPager.OnPageChangeListener {
    private ArrayList<Button> buttons = new ArrayList<>();
    Context context;
    private int i = 0;

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    @Override
    public void onPageSelected(int position) {

        if(buttons.size() == 4){
            buttons.get(i).setTextSize(22);
            buttons.get(i).setTextColor(context.getResources().getColor(R.color.colorHalfVisibility));
            buttons.get(i).getPaint().setFakeBoldText(false);

            buttons.get(position).setTextSize(30);
            buttons.get(position).setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            buttons.get(position).getPaint().setFakeBoldText(true);
            i = position;
        }
    }

    @Override
    public void onPageScrollStateChanged(int position) {
    }

    public void setAddBtn(Button button){
        this.buttons.add(button);
    }

    public void setContext(Context context) {
        this.context = context;
    }

}
