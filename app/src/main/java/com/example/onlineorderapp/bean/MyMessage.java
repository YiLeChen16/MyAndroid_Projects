package com.example.onlineorderapp.bean;

public class MyMessage {
    String msg;

    public MyMessage() {
    }

    public MyMessage(String msg) {
        this.msg = msg;
    }

    /**
     * 获取
     * @return msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 设置
     * @param msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String toString() {
        return "MyMessage{msg = " + msg + "}";
    }
}
