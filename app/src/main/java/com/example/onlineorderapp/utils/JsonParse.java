package com.example.onlineorderapp.utils;

import com.example.onlineorderapp.bean.Store;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

//此类用于解析从服务器上请求下来的json数据
//使用gson库解析
public class JsonParse {
    //解析json数据的方法
    //返回值为解析出来的店铺列表集合
    public static ArrayList<Store> getData(String json){
        //创建Gson类的对象
        Gson gson = new Gson();
        //创建一个TypeToken的匿名子类的对象，并调用对象的getTYpe()方法,获取到json数据要解析出来的结果数据类型
        Type type = new TypeToken<ArrayList<Store>>(){}.getType();
        //解析数据
        ArrayList<Store> storeList = gson.fromJson(json, type);
        //返回结果集合
        return  storeList;
    }
}
