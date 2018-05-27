package com.example.administrator.zyouonhand.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.zyouonhand.Activity.ClassActivity;
import com.example.administrator.zyouonhand.Bean.TableBean;
import com.example.administrator.zyouonhand.Fragment.ClassFragment;
import com.example.administrator.zyouonhand.R;
import com.example.administrator.zyouonhand.Util.ClassUtils;
import com.example.administrator.zyouonhand.other.LongService;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.MyViewHolder> {
    private List<TableBean> mDatas;
    private LayoutInflater layoutInflater;
    private Context mContex;
    private Activity mActivity;

    public ClassAdapter(Activity activity, Context context, List<TableBean> datas){
        this.mActivity = activity;
        this.mDatas = datas;
        this.mContex = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.class_table_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final TableBean Tablebean = mDatas.get(position);
        final String s = Tablebean.getTextView();
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        lp.width = ClassUtils.dip2px(mContex,60);
        holder.tv.setText(Tablebean.getTextView());
        if(position == 0||position == 8||position == 16||position == 24||position == 32){

        }
        if(position>=16&&position<=23||position>=40&&position<=47){
            lp.height = ClassUtils.dip2px(mContex,0);
        }
        if(Tablebean.getTextView().length()>25)
            holder.tv.setBackgroundColor(Color.parseColor(ClassUtils.RandomColor(position)));
        if(onItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v) {
                    int layoutPos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView,layoutPos);
                    if(!Objects.equals(mDatas.get(position).getTextView(), "") && !Objects.equals(TableBean.getLessonId(), " ")){
                        new AlertDialog.Builder(mContex)
                                .setMessage(mDatas.get(position).getTextView())
                                .show();
                    }
                    else if (!Objects.equals(mDatas.get(position).getTextView(), "") && Objects.equals(TableBean.getLessonId(), " ")){
                        new AlertDialog.Builder(mContex)
                                .setMessage(mDatas.get(position).getTextView())
                                .show();
                    }
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int layoutPos = holder.getLayoutPosition();
                    onItemClickListener.onItemLongClick(holder.itemView,layoutPos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.item_show);
        }
    }



}

