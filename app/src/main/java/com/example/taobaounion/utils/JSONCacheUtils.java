package com.example.taobaounion.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.taobaounion.base.BaseApplication;
import com.example.taobaounion.moudle.bean.CacheWithDuration;
import com.google.gson.Gson;

//缓存数据工具类
public class JSONCacheUtils {

    private static JSONCacheUtils mJsonCacheUtils = null;
    private final String JSON_CACHE_SP_NAME = "json_cache_sp_name";
    private final SharedPreferences mSP;
    private final Gson mGson;

    private JSONCacheUtils() {
        //获取到一个SharePreference对象
        mSP = BaseApplication.getBaseApplicationContext().getSharedPreferences(JSON_CACHE_SP_NAME, Context.MODE_PRIVATE);
        mGson = new Gson();
    }

    //对外提供获取此类单例的方法
    public static JSONCacheUtils getInstance(){
        if(mJsonCacheUtils == null){
            mJsonCacheUtils = new JSONCacheUtils();
        }
        return mJsonCacheUtils;
    }

    //缓存不带时间的数据
    public void saveCache(String key,Object value){
        this.saveCache(key,value,-1L);
    }

    //缓存带时间的数据
    //将传递过来的数据对象，转换为json字符串，再带上时间，
    // 转为带时间的数据对象，再次转为json字符串，再将其存储的SharePreference中
    public void saveCache(String key,Object value,long duration){
        SharedPreferences.Editor edit = mSP.edit();
        String json = mGson.toJson(value);
        //计算当前时间
        if(duration != -1L){
            duration = duration + System.currentTimeMillis();
        }
        CacheWithDuration cacheWithDuration = new CacheWithDuration(json, duration);
        String jsonWithDuration = mGson.toJson(cacheWithDuration);
        edit.putString(key,jsonWithDuration);
        edit.apply();
    }

    //删除指定缓存数据
    public void deleteCache(String key){
        mSP.edit().remove(key).apply();
    }

    //获取缓存数据
    public <T>T getCache(String key,Class<T> clazz){
        String value = mSP.getString(key, null);
        if(value == null){
            return null;
        }
        CacheWithDuration cacheWithDuration = mGson.fromJson(value, CacheWithDuration.class);
        long duration = cacheWithDuration.getDuration();
        //判断当前时间
        if(duration != -1L && (duration - System.currentTimeMillis() < 0)){
            //过期
            return null;
        }else {
            //未过期
            String json = cacheWithDuration.getJson();
            T result = mGson.fromJson(json, clazz);
            return result;
        }
    }
}
