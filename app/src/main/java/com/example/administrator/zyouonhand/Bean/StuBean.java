package com.example.administrator.zyouonhand.Bean;

public class StuBean {
    private zData data;

    public  class zData{
        private String stunum;
        private String idnum;
        private String id;
        private String introduction;
        private String username;
        private String nickname;
        private String gender;
        private String photo_thumsrc;
        private String photo_src;
        private String updatetime;
        private String phone;
        private String qq;

        public String getStunum() {
            return stunum;
        }

        public void setStunum(String stunum) {
            this.stunum = stunum;
        }

        public String getIdnum() {
            return idnum;
        }

        public void setIdnum(String idnum) {
            this.idnum = idnum;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public void setPhoto_thumsrc(String photo_thumsrc) {
            this.photo_thumsrc = photo_thumsrc;
        }

        public void setPhoto_src(String photo_src) {
            this.photo_src = photo_src;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }
    }

}
