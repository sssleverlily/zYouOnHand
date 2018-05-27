package com.example.administrator.zyouonhand.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.zyouonhand.Adapter.Qa_recy_adapter;
import com.example.administrator.zyouonhand.Bean.Qa_whole_list_bean;
import com.example.administrator.zyouonhand.R;

import java.util.ArrayList;
import java.util.List;

public class Qa_whole_fragment extends Fragment {
    private View mView;
    List<Qa_whole_list_bean.zData> dataBeans = new ArrayList<>();
    Context context;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        RecyclerView recyclerView = (RecyclerView) mView.findViewById(R.id.r1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        Qa_recy_adapter adapter = new Qa_recy_adapter(dataBeans);
        recyclerView.setAdapter(adapter);

        mView = inflater.inflate(R.layout.qa_list, null);
        return mView;

    }


}
