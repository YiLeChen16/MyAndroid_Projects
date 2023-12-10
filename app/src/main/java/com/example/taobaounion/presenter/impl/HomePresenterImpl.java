package com.example.taobaounion.presenter.impl;
import com.example.taobaounion.moudle.bean.Categories;
import com.example.taobaounion.moudle.Api;
import com.example.taobaounion.presenter.IHomePresenter;
import com.example.taobaounion.utils.LogUtils;
import com.example.taobaounion.utils.RetrofitCreator;
import com.example.taobaounion.view.IHomeCallBack;
import java.net.HttpURLConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomePresenterImpl implements IHomePresenter {
    //声明接口变量
    private IHomeCallBack callBack = null;

    public HomePresenterImpl() {
    }

    //网络请求，获取主页分类数据
    @Override
    public void getCategories() {
        //通知UI类数据正在加载中
        if(callBack != null){
            callBack.onLoading();
        }
        //获取Retrofit
        Retrofit retrofit = RetrofitCreator.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        //创建任务
        Call<Categories> discoveryCategories = api.getDiscoveryCategories();
        //执行任务
        discoveryCategories.enqueue(new Callback<Categories>() {
            //访问成功
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                if(response.code() == HttpURLConnection.HTTP_OK){
                    LogUtils.d(this,"response code-->" + response.code() + "访问成功！");
                    Categories categories = response.body();
                    LogUtils.d(this,"response-->" + categories);
                    //将数据传递到UI通知更新的回调接口中
                    if(callBack != null){
                        if(categories == null || categories.getData().size() == 0){
                            callBack.onEmpty();
                        }
                        else {
                            LogUtils.d(this,"callBack != null" + "response-->" + categories);
                            //callBack不等于null，说明已被注册，此时callBack是HomeFragment的对象
                            //故而相当于用HomeFragment的对象调用了HomeFragment中的onCategoriesLoad()方法
                            //故而之后在HomeFragment中的onCategoriesLoad()方法可以获取到数据
                            callBack.onCategoriesLoad(categories);
                        }
                    }
                }
                else{
                    if(callBack != null){
                        callBack.onError();
                    }
                    LogUtils.d(this,"response code-->" + response.code() + "访问失败！");
                }
            }
            //访问失败
            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                LogUtils.d(this, "访问失败: " + t);
                if(callBack != null){
                    callBack.onError();
                }
            }
        });
    }

    //重新加载页面
    @Override
    public void retry() {
        getCategories();

    }

    //对UI类提供注册UI通知更新回调接口的方法
    @Override
    public void registerViewCallBack(IHomeCallBack callBack) {
        this.callBack = callBack;//此处iHomeCallBack是HomFragment
    }

    @Override
    public void unregisterViewCallBack(IHomeCallBack callBack) {
        this.callBack = null;
    }
}
