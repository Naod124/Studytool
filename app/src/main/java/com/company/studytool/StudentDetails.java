package com.company.studytool;

import java.util.Date;

public class StudentDetails {
    static String username = "";
    static String password = "";
    static String chatWith = "";
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
}
