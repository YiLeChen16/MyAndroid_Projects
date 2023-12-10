package com.example.taobaounion.presenter.impl;

import com.example.taobaounion.moudle.Api;
import com.example.taobaounion.moudle.bean.SelectedContentResult;
import com.example.taobaounion.presenter.ISelectedPresenter;
import com.example.taobaounion.utils.LogUtils;
import com.example.taobaounion.utils.RetrofitCreator;
import com.example.taobaounion.utils.UrlUtils;
import com.example.taobaounion.view.ISelectedCallBack;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

//精选界面请求数据
public class SelectedPresenterImpl implements ISelectedPresenter {
    private ISelectedCallBack callBack = null;
    private boolean firstLoad = true;

    @Override
    public void registerViewCallBack(ISelectedCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void unregisterViewCallBack(ISelectedCallBack callBack) {
        this.callBack = null;
    }

    //请求精选界面数据
    @Override
    public void getSelectedContent(int categoryID) {

        if(callBack != null && firstLoad){
            callBack.onLoading();
        }
        //获取Retrofit
        Retrofit retrofit = RetrofitCreator.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        String url = UrlUtils.getSelectedCategoryUrl(categoryID);
        Call<SelectedContentResult> task = api.getSelectedContent(url);
        task.enqueue(new Callback<SelectedContentResult>() {
            @Override
            public void onResponse(Call<SelectedContentResult> call, Response<SelectedContentResult> response) {
                int code = response.code();
                LogUtils.d(SelectedPresenterImpl.this,"code --> " + code);
                if(code == HttpURLConnection.HTTP_OK){
                    SelectedContentResult selectedContentResult = response.body();
                    LogUtils.d(SelectedPresenterImpl.this,"数据请求成功！");
                    LogUtils.d(SelectedPresenterImpl.this,"selectedContentResult-->" + selectedContentResult);
                    //将数据传回UI层
                    if(callBack != null){
                        if(selectedContentResult.getData() != null){
                            callBack.onLoadContent(selectedContentResult.getData());
                        }else{
                            callBack.onEmpty();
                        }
                    }
                }
                else{
                    LogUtils.d(SelectedPresenterImpl.this,"数据请求失败！");
                    if(callBack != null){
                        callBack.onError();
                    }
                }
            }

            @Override
            public void onFailure(Call<SelectedContentResult> call, Throwable t) {
                LogUtils.d(SelectedPresenterImpl.this,"数据请求错误--> "+ t);
                if(callBack != null){
                    callBack.onError();
                }
            }
        });
    }
}
