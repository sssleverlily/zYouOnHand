package com.example.administrator.zyouonhand.Adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.zyouonhand.Bean.Qa_whole_list_bean;
import com.example.administrator.zyouonhand.R;
import com.example.administrator.zyouonhand.Util.JsonUtil;

import java.util.List;

public class Qa_recy_adapter extends RecyclerView.Adapter<Qa_recy_adapter.ViewHolder> {
    private List<Qa_whole_list_bean.zData> beanList;
    private int classitem;
    private FragmentActivity activity;


    public Qa_recy_adapter(List<Qa_whole_list_bean.zData> picList) {
        beanList = picList;
    }

    public Qa_recy_adapter(FragmentActivity activity, int class_table_item, List list) {
        this.activity = activity;
        this.classitem = class_table_item;
        this.beanList = list;

    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.qa_item,parent
                ,false);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        JsonUtil jsonUtil = new JsonUtil();
        String response = jsonUtil.jsonback("https://wx.idsbllp.cn/springtest/cyxbsMobile/index.php/QA/Question/getQuestionList");
        jsonUtil.getqalist(response);
        Qa_whole_list_bean.zData bean = beanList.get(position);
        holder.title.setText(bean.getTitle());
        holder.jifen.setText(bean.getJifen());
        holder.topic.setText(bean.getTopic());
        holder.nickname.setText(bean.getNickname());
        holder.misstime.setText(bean.getMisstime());
        holder.content.setText(bean.getContent());
//        holder.headimg.setImageResource(bean.getHeadimg().length());
        holder.lookpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nickname;
        TextView misstime;
        TextView topic;
        TextView title;
        TextView content;
        TextView jifen;
        Button lookpic;
        ImageView headimg;
        ImageView jifenimg;


        public ViewHolder(View itemView) {
            super(itemView);

            nickname = itemView.findViewById(R.id.name);
            misstime = itemView.findViewById(R.id.misstime);
            topic = itemView.findViewById(R.id.topic);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            jifen = itemView.findViewById(R.id.jifen);
            lookpic = itemView.findViewById(R.id.lookpic);
            headimg = itemView.findViewById(R.id.touxiang);
            jifenimg = itemView.findViewById(R.id.jifenimg);


        }
    }

    public interface OnItemClickListener{
        void onClick(int position);
    }



}
