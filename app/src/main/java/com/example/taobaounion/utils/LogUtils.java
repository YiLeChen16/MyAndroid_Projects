package com.example.taobaounion.utils;

import android.util.Log;

//创建LogUtils工具类统一管理日志输出，避免应用发布后日志输出不可控
public class LogUtils {
    private static final int CURRENT = 4;//目前输出日志等级
    private static final int INFO = 4;
    private static final int DEBUG = 3;
    private static final int WARN = 2;
    private static final int ERROR = 1;

    public static void i(Object obj,String msg){
        if(CURRENT >= INFO){
            Log.i(obj.getClass().getName(),msg);
        }
    }

    public static void d(Object obj,String msg){
        if(CURRENT >= DEBUG){
            Log.d(obj.getClass().getName(),msg);
        }
    }

    public static void w(Object obj,String msg){
        if(CURRENT >= WARN){
            Log.w(obj.getClass().getName(),msg);
        }
    }

    public static void e(Object obj,String msg){
        if(CURRENT >= ERROR){
            Log.e(obj.getClass().getName(),msg);
        }
    }


}
