package com.example.administrator.zyouonhand.Bean;

public class TableBean {
    private String textView;
    private static String lessonId;

    public TableBean(String lessonId, String textView) {
        this.lessonId = lessonId;
        this.textView = textView;
    }

    public static String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getTextView() {
        return textView;
    }

    public void setTextView(String textView) {
        this.textView = textView;
    }

}
