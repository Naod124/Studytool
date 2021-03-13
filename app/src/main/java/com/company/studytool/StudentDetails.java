package com.company.studytool;

import java.util.Date;

public class StudentDetails {
    static String username = "";
    static String password = "";
    static String chatWith = "";
    static String currentCourse ="";
    private long messageTime;

    public StudentDetails(){
        messageTime = new Date().getTime();
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }

    public static String getCurrentCourse() {
        return currentCourse;
    }

    public static void setCurrentCourse(String currentCourse) {
        StudentDetails.currentCourse = currentCourse;
    }
}
