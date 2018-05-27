package com.example.administrator.zyouonhand.Bean;

import java.util.List;

public class AnswerBean {
    private int id;
    private int user_id;
    private String content;
    private String creat_at;
    private int praise_num;
    private int comment_num;
    private int is_adopted;
    private List photo_url;
    private String photo_thumbnail_src;
    private String nickname;
    private String gender;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreat_at() {
        return creat_at;
    }

    public void setCreat_at(String creat_at) {
        this.creat_at = creat_at;
    }

    public int getPraise_num() {
        return praise_num;
    }

    public void setPraise_num(int praise_num) {
        this.praise_num = praise_num;
    }

    public int getComment_num() {
        return comment_num;
    }

    public void setComment_num(int comment_num) {
        this.comment_num = comment_num;
    }

    public int getIs_adopted() {
        return is_adopted;
    }

    public void setIs_adopted(int is_adopted) {
        this.is_adopted = is_adopted;
    }


    public void setPhoto_url(List photo_url) {
        this.photo_url = photo_url;
    }

    public String getPhoto_thumbnail_src() {
        return photo_thumbnail_src;
    }

    public void setPhoto_thumbnail_src(String photo_thumbnail_src) {
        this.photo_thumbnail_src = photo_thumbnail_src;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List getPhoto_url() {
        return photo_url;
    }
}
