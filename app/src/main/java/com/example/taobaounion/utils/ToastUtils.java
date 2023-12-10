package com.example.taobaounion.utils;

import android.widget.Toast;

import com.example.taobaounion.base.BaseApplication;

//创建ToastUItls类用于改善，toust的使用，以及多次弹出toast时，toast长时间不消失的不好的用户体验
public class ToastUtils {
    private static Toast toast = null;
    public static void showToast(String tips){
        //确保唯一单例,避免重复创建Toast对象，导致Toast长时间不消失
        if(toast == null) {
            toast = Toast.makeText(BaseApplication.getBaseApplicationContext(), tips
                    , Toast.LENGTH_SHORT);
        } else{
            toast.setText(tips);
        }
        toast.show();
    }
}
