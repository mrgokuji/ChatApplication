package com.chatClient.Model;

public class User {
    public User(Integer userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    Integer userId;
    String userName;

    @Override
    public String toString(){
        String s = "{\"userId\":%d , \"userName\" : \"%s\"}".formatted(this.userId,this.userName);
        System.out.println(s);
        return s;
    }

}
