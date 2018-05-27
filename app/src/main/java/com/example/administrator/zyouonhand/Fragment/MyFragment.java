package com.example.administrator.zyouonhand.Fragment;


import android.annotation.SuppressLint;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.administrator.zyouonhand.Activity.LoginActivity;
import com.example.administrator.zyouonhand.R;

public class MyFragment extends Fragment {
    private View mView;
    private ImageButton button;
    private ImageButton button1;
    private Button button_login;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView=inflater.inflate(R.layout.myfragment, null);

        button_login = mView.findViewById(R.id.textView12);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        return mView;
    }

}
