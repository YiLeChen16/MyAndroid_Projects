package com.example.mychatrobot.Bean;
//此类为服务器返回的Json数据的实体类
public class JsonResult {
    private int code;
    private Result result;
    private String msg;


    public JsonResult() {
    }

    public JsonResult(int code, Result result, String msg) {
        this.code = code;
        this.result = result;
        this.msg = msg;
    }

    /**
     * 获取
     * @return code
     */
    public int getCode() {
        return code;
    }

    /**
     * 设置
     * @param code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 获取
     * @return result
     */
    public Result getResult() {
        return result;
    }

    /**
     * 设置
     * @param result
     */
    public void setResult(Result result) {
        this.result = result;
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
        return "JsonResult{code = " + code + ", result = " + result + ", msg = " + msg + "}";
    }
}
