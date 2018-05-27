package com.example.administrator.zyouonhand.Bean;

public class Qa_whole_list_bean {
    public zData zData;


    public class zData{
        private String headimg;
        private int headimgid;
        private String nickname;
        private String misstime;
        private String topic;
        private String title;
        private String content;
        private String jifen;

        public void zyData(int headimgid,String nickname,String misstime,String topic,String title,String content,String jifen){
            this.headimgid = headimgid;
            this.nickname = nickname;
            this.misstime = misstime;
            this.topic = topic;
            this.title = title;
            this.content = content;
            this.jifen = jifen;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public void setMisstime(String misstime) {
            this.misstime = misstime;
        }


        public void setTopic(String topic) {
            this.topic = topic;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setJifen(String jifen) {
            this.jifen = jifen;
        }

        public String getHeadimg() {
            return headimg;
        }

        public String getNickname() {
            return nickname;
        }

        public String getMisstime() {
            return misstime;
        }

        public String getTopic() {
            return topic;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }

        public String getJifen() {
            return jifen;
        }

        public int getHeadimgid() {
            return headimgid;
        }

        public void setHeadimgid(int headimgid) {
            this.headimgid = headimgid;
        }
    }

}
