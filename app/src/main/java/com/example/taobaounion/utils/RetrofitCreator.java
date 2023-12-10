package com.example.taobaounion.utils;
import com.example.taobaounion.moudle.bean.Constants;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//将Retrofit的创建和配置一个封装类，要使用时直接创建此类的对象并调用getRetrofit()方法即可
public class RetrofitCreator {

    private static RetrofitCreator mRetrofitCreator = null;
    private  Retrofit mRetrofit = null;



    //构造方法私有
    private RetrofitCreator() {
        //创建并配置Retrofit
        createRetrofit();
    }

    //创建并配置Retrofit
    private void createRetrofit() {
        //结合okhttp进行网络请求
        //设置一下okHttp的参数
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(Constants.CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    //提供方法供外部获取此类的对象
    public static RetrofitCreator getInstance(){
        if(mRetrofitCreator == null){
            synchronized (RetrofitCreator.class){
                if(mRetrofitCreator == null){
                    mRetrofitCreator = new RetrofitCreator();
                }
            }
        }
        return mRetrofitCreator;
    }

    //获取已经配置好的Retrofit对象
    public Retrofit getRetrofit(){
        return mRetrofit;
    }

}
