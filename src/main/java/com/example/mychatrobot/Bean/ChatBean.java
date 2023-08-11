package com.example.mychatrobot.Bean;
//此类为聊天条目的实体类
public class ChatBean {
    //表示此条目为用户发送出去的
    public static final int SEND = -1;
    //表示此条目为用户接收到的
    public static final int RECEIVE = 0;
    //条目的内容
    private String content;
    //表示此条目的状态
    private int state;

    public ChatBean() {
    }

    public ChatBean(String content, int state) {
        this.content = content;
        this.state = state;
    }

    /**
     * 获取
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取
     * @return state
     */
    public int getState() {
        return state;
    }

    /**
     * 设置
     * @param state
     */
    public void setState(int state) {
        this.state = state;
    }

    public String toString() {
        return "ChatBean{content = " + content + ", state = " + state + "}";
    }
}
