package com.chatClient.Model;

public class Message {
    public Message(Integer messageId, String messageBody, Boolean isRead) {
        this.messageId = messageId;
        this.messageBody = messageBody;
        this.isRead = isRead;
    }

    Integer messageId;
    String messageBody;
    Boolean isRead;

    public Integer getMessageId() {
        return messageId;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public Boolean getRead() {
        return isRead;
    }
}
