package com.example.ci360;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.text.DateFormat;

public class ChatMessage{

    private String messageText;
    private String messageUser;
    private long messageTime;


    public ChatMessage(String messageText, String messageUser) {
        this.messageText = messageText;
        this.messageUser = messageUser;

        // Initialize to current time
        messageTime = new Date().getTime();
    }

    public ChatMessage(){

    }

    public String getMessageText() {
        return messageText;
    }


    public String getMessageUser() {
        return messageUser;
    }


    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageText(String s){
        this.messageText = s;
    }
}

