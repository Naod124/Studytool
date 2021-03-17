package com.company.studytool.Model;

import java.util.Date;

public class StudentDetails {
    public static String username = "";
    public static String password = "";
    public static String chatWith = "";
    public static String currentCourse ="";
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
