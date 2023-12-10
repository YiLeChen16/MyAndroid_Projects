package com.example.taobaounion.presenter.impl;

import com.example.taobaounion.moudle.Api;
import com.example.taobaounion.moudle.bean.RedPacketContentResult;
import com.example.taobaounion.presenter.IRedPacketPresenter;
import com.example.taobaounion.utils.LogUtils;
import com.example.taobaounion.utils.RetrofitCreator;
import com.example.taobaounion.utils.UrlUtils;
import com.example.taobaounion.view.IRedPacketCallBack;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

//特惠界面加载数据
public class RedPacketPresenterImpl implements IRedPacketPresenter {
    private IRedPacketCallBack callBack = null;
    private static int currentPage = 1;

    @Override
    public void registerViewCallBack(IRedPacketCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void unregisterViewCallBack(IRedPacketCallBack callBack) {
        this.callBack = null;
    }

    //获取特惠界面数据
    @Override
    public void getOnSellContent() {
        if (callBack != null) {
            callBack.onLoading();
        }
        //默认请求第一页的数据
        Call<RedPacketContentResult> task = getTask(currentPage);
        task.enqueue(new Callback<RedPacketContentResult>() {
            @Override
            public void onResponse(Call<RedPacketContentResult> call, Response<RedPacketContentResult> response) {
                int code = response.code();
                LogUtils.d(RedPacketPresenterImpl.this, "onResponse-->code:" + code);
                if (code == HttpsURLConnection.HTTP_OK) {
                    RedPacketContentResult data = response.body();
                    LogUtils.d(RedPacketPresenterImpl.this, "onResponse-->数据请求成功");
                    LogUtils.d(RedPacketPresenterImpl.this, "onResponse-->data:" + data);
                    if (callBack != null) {
                        //将请求成功的数据传回UI
                        if (data != null && data.getData() != null) {
                            callBack.onLoadContentSuccess(data);
                        } else {
                            callBack.onEmpty();
                        }
                    }
                } else {
                    LogUtils.d(RedPacketPresenterImpl.this, "数据请求错误");
                    if (callBack != null) {
                        callBack.onError();
                    }

                }
            }

            @Override
            public void onFailure(Call<RedPacketContentResult> call, Throwable t) {
                LogUtils.d(RedPacketPresenterImpl.this, "数据请求错误");
                if (callBack != null) {
                    callBack.onError();
                }
            }
        });
    }

    //获取网络请求任务
    private Call<RedPacketContentResult> getTask(Integer targetPage) {
        //获取Retrofit
        Retrofit retrofit = RetrofitCreator.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        //创建URL
        String redPacketUrl = UrlUtils.createRedPacketUrl(targetPage);
        LogUtils.d(this, "homeViewPagerUrl-->" + redPacketUrl);
        Call<RedPacketContentResult> task = api.getRedPacketContent(redPacketUrl);
        return task;
    }


    //重新加载页面
    @Override
    public void retry() {
        getOnSellContent();
    }

    /**
     * 加载更多数据
     */
    @Override
    public void onLoadMore() {
        //当前页码加一
        currentPage++;
        Call<RedPacketContentResult> task = getTask(currentPage);
        //获取更多数据
        task.enqueue(new Callback<RedPacketContentResult>() {
            @Override
            public void onResponse(Call<RedPacketContentResult> call, Response<RedPacketContentResult> response) {
                int code = response.code();
                LogUtils.d(RedPacketPresenterImpl.this,"onLoadMore-->code==" + code);
                if (code == HttpsURLConnection.HTTP_OK) {
                    RedPacketContentResult result = response.body();
                    handleLoadMoreSuccess(result);
                } else {
                    handleLoadMoreError();
                }
            }

            @Override
            public void onFailure(Call<RedPacketContentResult> call, Throwable t) {
                LogUtils.d(RedPacketPresenterImpl.this,"onLoadMore-->onFailure:" + t);
                handleLoadMoreError();
            }
        });
    }

    //处理加载更多失败
    private void handleLoadMoreError() {
        if(callBack != null){
            callBack.onLoadMoreError();
            currentPage--;
        }
    }

    //处理加载更多结果成功
    private void handleLoadMoreSuccess(RedPacketContentResult result) {
        if (callBack != null) {
            if (isEmpty(result)) {
                callBack.onLoadMoreSuccess(result);
            } else {
                callBack.onLoadMoreEmpty();
                currentPage--;
            }
        }
    }

    private boolean isEmpty(RedPacketContentResult result){
        try {
            return result.getData().getTbk_dg_optimus_material_response().getResult_list().getMap_data() != null;
        }catch (NullPointerException nullPointerException){
            return false;
        }
    }
}
