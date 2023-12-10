package com.example.taobaounion.presenter.impl;

import com.example.taobaounion.moudle.Api;
import com.example.taobaounion.moudle.bean.TicketParams;
import com.example.taobaounion.moudle.bean.TicketResult;
import com.example.taobaounion.presenter.ITicketPresenter;
import com.example.taobaounion.utils.LogUtils;
import com.example.taobaounion.utils.RetrofitCreator;
import com.example.taobaounion.utils.UrlUtils;
import com.example.taobaounion.view.ITicketResultCallBack;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TicketPresenterImpl implements ITicketPresenter {
    private ITicketResultCallBack callBack;
    private String mPicUrl;
    private TicketResult mTicketResult;

    //因为请求数据是在HomeViewPagerFragment中进行的，而注册过来的是TicketActivity类的对象
    //且请求数据操作与跳转到TicketActivity这两个操作是异步的，
    //故而，可能出现数据已经请求完毕了，但是TicketActivity类的对象还没有注册过来，
    //故而此时不能像之前一样简单的在getTicket中对注册过来的callback对象进行判空，再将数据传递回去
    //创建枚举类表示数据的加载状态
    enum LoadState{
        LOADING,SUCCESS,ERROR,EMPTY,NONE
    }

    //目前数据的加载状态
    private LoadState mCurrentState = LoadState.NONE;

    @Override
    public void registerViewCallBack(ITicketResultCallBack callBack) {
        this.callBack = callBack;
        LogUtils.d(this,"registerViewCallBack-->");
        //正常来讲，注册操作应该是早于数据请求操作的，这样才能保证在数据请求完成之后数据可以及时回传给UI类
        //若出现mCurrentState != LoadState.NONE，则说明此时出现了注册操作慢于数据请求操作的现象，若直接在getTicket中进行判断可能回导致数据无法正确回传给UI类
        if(mCurrentState != LoadState.NONE){
            //说明数据请求状态已经发生改变
            if(mCurrentState == LoadState.EMPTY){
                //请求数据为空
                onLoadSuccess();
            }else if(mCurrentState == LoadState.ERROR){
                //请求错误
                onLoadError();
            } else if(mCurrentState == LoadState.LOADING){
                //正在加载中
                onLoading();
            } else{
                //数据请求成功
                onLoadSuccess();
            }
        }
    }

    //数据正在加载中
    private void onLoading() {
        if(callBack != null){
            callBack.onLoading();
        }else {
            mCurrentState = LoadState.LOADING;
        }
    }

    @Override
    public void unregisterViewCallBack(ITicketResultCallBack callBack) {
        this.callBack = null;
    }

    //请求淘口令数据
    @Override
    public void getTicket(String picUrl, String clickUrl, String title) {
        mPicUrl = picUrl;
        onLoading();
        LogUtils.d(this,"picUrl -- > " + picUrl);
        LogUtils.d(this,"clickUrl -- > " + clickUrl);
        LogUtils.d(this,"title -- > " + title);
        //获取Retrofit
        Retrofit retrofit = RetrofitCreator.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        String ticketUrl = UrlUtils.getTicketUrl(clickUrl);
        LogUtils.d(this,"ticketUrl -- > " + ticketUrl);
        TicketParams params = new TicketParams(ticketUrl,title);
        Call<TicketResult> task = api.getTicket(params);
        task.enqueue(new Callback<TicketResult>() {

            @Override
            public void onResponse(Call<TicketResult> call, Response<TicketResult> response) {
                int code = response.code();
                LogUtils.d(TicketPresenterImpl.this,"onResponse --> " + code);
                if(code == HttpsURLConnection.HTTP_OK){
                    LogUtils.d(TicketPresenterImpl.this,"数据请求成功");
                    mTicketResult = response.body();
                    LogUtils.d(TicketPresenterImpl.this,"body-->" + mTicketResult.toString());
                    onLoadSuccess();
                } else{
                    LogUtils.d(TicketPresenterImpl.this,"数据请求失败");
                    onLoadError();
                }
            }

            @Override
            public void onFailure(Call<TicketResult> call, Throwable t) {
                LogUtils.d(TicketPresenterImpl.this,"onFailure-->" + t);
                onLoadError();
            }
        });
    }

    //数据请求失败
    private void onLoadError() {
        LogUtils.d(this,"onLoadError...");
        if(callBack != null){
            callBack.onError();
        } else{
            mCurrentState = LoadState.ERROR;
        }
    }

    //数据请求完毕且成功
    private void onLoadSuccess() {
        LogUtils.d(this,"onLoadSuccess...");
        if(callBack != null){
            callBack.onLoadTicketResult(mPicUrl,mTicketResult);
        }
        else{
            if(mTicketResult == null || mTicketResult.getData() == null){
                mCurrentState = LoadState.EMPTY;
            } else{
                mCurrentState = LoadState.SUCCESS;
            }
        }
    }
}
