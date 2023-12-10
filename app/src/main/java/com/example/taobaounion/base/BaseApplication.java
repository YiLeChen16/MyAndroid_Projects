package com.example.taobaounion.base;

import android.app.Application;
import android.content.Context;

import com.vondear.rxtool.RxTool;

//此类用户获取唯一的context
public class BaseApplication extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getBaseContext();
        //初始化RX工具库，用于生成和扫描二维码
        RxTool.init(this);
    }

    public static Context getBaseApplicationContext(){
        return context;
    }
}
