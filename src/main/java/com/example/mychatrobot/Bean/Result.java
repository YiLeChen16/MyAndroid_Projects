package com.example.mychatrobot.Bean;
//此类为服务器返回数据中的一个参数
public class Result {
    private String displayText;

    public Result() {
    }

    public Result(String displayText) {
        this.displayText = displayText;
    }

    /**
     * 获取
     * @return displayText
     */
    public String getDisplayText() {
        return displayText;
    }

    /**
     * 设置
     * @param displayText
     */
    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    public String toString() {
        return "result{displayText = " + displayText + "}";
    }
}
