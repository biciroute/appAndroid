package com.example.myapplication.entities;



public class Token{

    private String accessToken;
    private String userName;
    private String password;

    public Token(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Token(String accessToken, String userName, String password) {
        this.accessToken = accessToken;
        this.userName = userName;
        this.password = password;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Token{" +
                "accessToken='" + accessToken + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
