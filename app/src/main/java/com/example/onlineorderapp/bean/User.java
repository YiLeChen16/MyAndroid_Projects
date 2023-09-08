package com.example.onlineorderapp.bean;

import java.util.ArrayList;

//APP登录注册用户的实体类
public class User {
    private String phoneNumber;
    private String password;

    public User() {
    }

    public User(String phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    /**
     * 获取
     * @return phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 设置
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "User{phoneNumber = " + phoneNumber + ", password = " + password + "}";
    }
}
