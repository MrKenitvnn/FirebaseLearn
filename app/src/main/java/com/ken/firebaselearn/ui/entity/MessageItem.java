package com.ken.firebaselearn.ui.entity;

/**
 * Created by ken on 18/03/2016.
 */
public class MessageItem {

    private String messageEmail;
    private String messageContent;

    public MessageItem() {
    }

    public MessageItem(String messageEmail, String messageContent) {
        this.messageEmail = messageEmail;
        this.messageContent = messageContent;
    }

    public String getMessageEmail() {
        return messageEmail;
    }

    public void setMessageEmail(String messageEmail) {
        this.messageEmail = messageEmail;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    @Override
    public String toString() {
        return getMessageContent();
    }
}