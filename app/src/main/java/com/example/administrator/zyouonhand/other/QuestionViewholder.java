package com.example.administrator.zyouonhand.other;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.administrator.zyouonhand.Activity.QuesxijieActivity;
import com.example.administrator.zyouonhand.Bean.QuestionCare;
import com.example.administrator.zyouonhand.R;
import java.util.List;

public class QuestionViewholder extends zViewHolder{
    private CircleImageView head;
    private TextView title;
    private TextView topic;
    private TextView misstime;
    private TextView userId;
    private TextView jifen;
    private TextView content;
    private List<QuestionCare> mQuestion;
    private Button kantu;


    private String kind;

    public QuestionViewholder(View itemView, List<QuestionCare> questions, final String kind) {
        super(itemView);
        head= getView(R.id.touxiang);
        misstime = getView(R.id.misstime);
        userId = getView(R.id.username);
        jifen = getView(R.id.jifen);
        content = getView(R.id.content);
        title = getView(R.id.title);
        topic = getView(R.id.topic);
        kantu = getView(R.id.lookpic);


        mQuestion = questions;
        this.kind = kind;

        kantu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionCare qa_whole_list_bean = mQuestion.get(getLayoutPosition());
                Intent intent = new Intent(v.getContext(), QuesxijieActivity.class);
                intent.putExtra("questionId",qa_whole_list_bean.getNickname());
                intent.putExtra("kind",kind);
                v.getContext().startActivity(intent);
            }
        });

    }

    public void initData(QuestionCare ic_question_man,String kind) {
        misstime.setText("小时后消失");
        jifen.setText(ic_question_man.getReward() + "积分");
        title.setText(ic_question_man.getTitle());
        content.setText(ic_question_man.getDescription());
        topic.setText("#" + ic_question_man.getTags() + "#");
        userId.setText(ic_question_man.getNickname());
        String head_url = ic_question_man.getPhoto_thumbnail_src();
        Imagelord imagelord = new Imagelord();
        imagelord.imagehandler(head,head_url);
    }
}
