package com.lambdaschool.projectrestdogs;



import java.io.Serializable;

public class MessageDetail implements Serializable
        //because order matters in writing objects to the message queue
        //we need these objects to be a series of bytes
{
    private String text;


    //spring requires the default constructor


    public MessageDetail() {
    }

    public MessageDetail(String text) {
        this.text = text;

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }



    @Override
    public String toString() {
        return "MessageDetail{" +
                "text='" + text +
                '}';
    }
}
