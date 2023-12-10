package com.example.taobaounion.utils;

import android.content.Context;

//单位转换工具类
public class SizeUtils {
    //px转dp
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
