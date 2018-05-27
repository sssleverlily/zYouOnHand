package com.example.administrator.zyouonhand.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.zyouonhand.Activity.QiuzhuActivity;
import com.example.administrator.zyouonhand.Adapter.Qa_recy_adapter;
import com.example.administrator.zyouonhand.Bean.Qa_whole_list_bean;
import com.example.administrator.zyouonhand.R;
import com.example.administrator.zyouonhand.Util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class YouwenFragment extends Fragment{
    private View mView;
    private FloatingActionButton bt_ask;
    private List list;
    Context context =getActivity();
    private List quelist = new ArrayList();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.qa_app_bar_main, null);
        bt_ask = mView.findViewById(R.id.floatask);
        bt_ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),QiuzhuActivity.class);
                startActivity(intent);

            }
        });
        initItem();
        JsonUtil jsonUtil = new JsonUtil();
       list= jsonUtil.getqalist(jsonUtil.jsonback(" https://wx.idsbllp.cn/springtest/cyxbsMobile/index.php/QA/Question/getQuestionList"));

        RecyclerView recyclerView = (RecyclerView) mView.findViewById(R.id.r1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        Qa_recy_adapter adapter = new Qa_recy_adapter(getActivity(),R.layout.class_table_item,quelist);
        recyclerView.setAdapter(adapter);

        return mView;
    }

    public void initItem(){
        for(int i=0;i<6;i++){
            Qa_whole_list_bean qa_whole_list_bean = new Qa_whole_list_bean();
            Qa_whole_list_bean.zData z1 =qa_whole_list_bean.new zData();
            z1.zyData(R.mipmap.ic_launcher_round,"匿名","消失于6小时"
                    ,"#线代#","线代好难啊","好难啊好难啊好难啊好难啊好难啊好难啊好难啊好难啊","1积分" );
            quelist.add(z1);
            Qa_whole_list_bean.zData z2 =qa_whole_list_bean.new zData();
            z2.zyData(R.mipmap.login,"哈哈","消失于3小时"
                    ,"#高数#","呵呵","哈哈哈哈哈哈哈哈哈哈哈哈","0积分");
            quelist.add(z2);
            Qa_whole_list_bean.zData z3 =qa_whole_list_bean.new zData();
            z3.zyData(R.mipmap.lunbo2,"匿名","消失于1小时"
                    ,"#英语#","额。。。","嗯嗯嗯嗯呃嗯嗯嗯嗯嗯嗯额嗯嗯嗯嗯呢呢嫩嫩嗯嗯嗯","3积分" );
            quelist.add(z3);

        }

    }

}
