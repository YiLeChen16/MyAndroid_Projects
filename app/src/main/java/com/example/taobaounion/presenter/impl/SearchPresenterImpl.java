package com.example.taobaounion.presenter.impl;

import android.text.TextUtils;

import com.example.taobaounion.moudle.Api;
import com.example.taobaounion.moudle.bean.Histories;
import com.example.taobaounion.moudle.bean.SearchRecommendWords;
import com.example.taobaounion.moudle.bean.SearchResult;
import com.example.taobaounion.presenter.ISearchPresenter;
import com.example.taobaounion.utils.JSONCacheUtils;
import com.example.taobaounion.utils.LogUtils;
import com.example.taobaounion.utils.RetrofitCreator;
import com.example.taobaounion.view.ISearchCallBack;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchPresenterImpl implements ISearchPresenter {

    private static final int DEFAULT_PAGE = 1;
    private final JSONCacheUtils mJsonCacheUtils;
    private ISearchCallBack callBack = null;
    private int currentPage = DEFAULT_PAGE;
    private String currentKeyword;
    private static final String KEY_HISTORY = "key_history";
    private static final int MAX_HISTORY_COUNT = 10;

    public SearchPresenterImpl() {
        //获取数据缓存类的单例
        mJsonCacheUtils = JSONCacheUtils.getInstance();
    }

    @Override
    public void registerViewCallBack(ISearchCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void unregisterViewCallBack(ISearchCallBack callBack) {
        this.callBack = null;
    }

    //搜索接口
    @Override
    public void doSearch(String keyword) {
        if(callBack != null){
            callBack.onLoading();
        }
        //将当前搜索词记录下来,以便重新搜索
        if(!keyword.equals(currentKeyword)){
            this.currentKeyword = keyword;
            //保存搜索历史记录
            saveHistory(keyword);
        }
        Retrofit retrofit = RetrofitCreator.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        Call<SearchResult> task = api.getSearchResult(this.currentPage, keyword);
        task.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                int code = response.code();
                LogUtils.d(SearchPresenterImpl.this,"code-->" + code);
                if(code == HttpsURLConnection.HTTP_OK){
                    LogUtils.d(SearchPresenterImpl.this,"搜索成功");
                    SearchResult searchResult = response.body();
                    handleLoadSearchResultSuccess(searchResult);
                }else{
                    LogUtils.d(SearchPresenterImpl.this,"网络错误");
                    handleLoadSearchResultError();
                }
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                handleLoadSearchResultError();
            }
        });
    }

    //处理加载搜索结果错误
    private void handleLoadSearchResultError() {
        if(callBack != null){
            callBack.onError();
        }
    }

    //处理加载搜索结果成功
    private void handleLoadSearchResultSuccess(SearchResult searchResult) {
        if(callBack != null){
            if(isNull(searchResult)){
                callBack.onLoadSearchResult(searchResult);
            }else{
                callBack.onEmpty();
            }
        }
    }


    //重新搜索接口
    @Override
    public void reSearch() {
        doSearch(currentKeyword);
    }

    //添加历史记录
    public void saveHistory(String history){
        //判断此历史记录是否之前的历史记录有重复，有则去除之前的历史记录
        Histories histories = mJsonCacheUtils.getCache(KEY_HISTORY, Histories.class);
        //之前有历史记录，去重
        List<String> historyList = null;
        if(histories != null && histories.getHistories() != null){
            historyList = histories.getHistories();
            if(historyList.contains(history)){
                historyList.remove(history);
            }
        }
        //已完成去重
        if(historyList == null){
            historyList = new ArrayList<>();
        }
        if(histories == null){
            histories = new Histories();
        }
        //将新的历史数据添加到历史记录集合中
        historyList.add(history);
        //控制历史记录数量在十个以内
        if(historyList.size() > MAX_HISTORY_COUNT){
            //对集合进行裁剪
            historyList = historyList.subList(historyList.size() - 10,historyList.size());
        }
        histories.setHistories(historyList);
        //将历史记录集合进行缓存
        mJsonCacheUtils.saveCache(KEY_HISTORY,histories);
    }


    //加载历史记录
    @Override
    public void loadHistory() {
        Histories histories = mJsonCacheUtils.getCache(KEY_HISTORY, Histories.class);
        if(histories != null && histories.getHistories() != null && histories.getHistories().size() != 0){
            if(callBack != null){
                callBack.onLoadHistoriesSuccess(histories.getHistories());
            }
        }else{
            //没有历史记录，可能是由于用户点击了清空历史记录，故需要通知UI将FlowTextLayout中现有的子View清空
            if(callBack != null){
                callBack.onLoadHistoriesEmpty();
            }
        }
    }

    //删除搜索历史记录
    @Override
    public void deleteHistories() {
        mJsonCacheUtils.deleteCache(KEY_HISTORY);
    }

    //对搜索结果进行判空
    public boolean isNull(SearchResult searchResult){
        try{
            return searchResult.getData() .getTbk_dg_material_optional_response().getResult_list().getMap_data() != null;
        }catch (NullPointerException nullPointerException){
            return false;
        }
    }

    //加载搜索推荐词
    @Override
    public void loadRecommendWords() {
        if(callBack != null){
            callBack.onLoading();
        }
        Retrofit retrofit = RetrofitCreator.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        Call<SearchRecommendWords> task = api.getSearchRecommendWords();
        task.enqueue(new Callback<SearchRecommendWords>() {
            @Override
            public void onResponse(Call<SearchRecommendWords> call, Response<SearchRecommendWords> response) {
                int code = response.code();
                LogUtils.d(SearchPresenterImpl.this,"code-->" + code);
                if(code == HttpsURLConnection.HTTP_OK){
                    LogUtils.d(SearchPresenterImpl.this,"加载成功！");
                    SearchRecommendWords recommendWords = response.body();
                    handleLoadSearchRecommendWordSuccess(recommendWords);
                }else{
                    handleLoadSearchRecommendWordError();
                }
            }

            @Override
            public void onFailure(Call<SearchRecommendWords> call, Throwable t) {
                LogUtils.d(SearchPresenterImpl.this,"onFailure-->" + t);
                handleLoadSearchRecommendWordError();
            }
        });
    }

    //处理加载搜索推荐词错误
    private void handleLoadSearchRecommendWordError() {
        if(callBack != null){
            callBack.onLoadSearchRecommendWordsError();
        }
    }

    //处理加载搜索推荐词成功
    private void handleLoadSearchRecommendWordSuccess(SearchRecommendWords recommendWords) {
        if(callBack != null){
            callBack.onLoadSearchRecommendWordsSuccess(recommendWords);
        }
    }

    //加载更多搜索结果
    @Override
    public void loadMoreSearchResult() {
        currentPage++;
        Retrofit retrofit = RetrofitCreator.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        Call<SearchResult> task = api.getSearchResult(currentPage, currentKeyword);
        task.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                int code = response.code();
                if(code == HttpsURLConnection.HTTP_OK){
                    LogUtils.d(SearchPresenterImpl.this,"加载更多搜索结果成功！");
                    SearchResult searchResult = response.body();
                    handleLoadMoreSuccess(searchResult);
                }else {
                    LogUtils.d(SearchPresenterImpl.this,"网络错误");
                    handleLoadMoreError();
                }
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                handleLoadMoreError();
            }
        });
    }

    //重新加载界面
    @Override
    public void retry() {
        loadRecommendWords();
        loadHistory();
    }

    //处理加载更多数据错误
    private void handleLoadMoreError() {
        if(callBack != null){
            callBack.onLoadMoreError();
        }
    }

    //处理加载更多数据成功
    private void handleLoadMoreSuccess(SearchResult searchResult) {
        if(callBack != null){
            if(isNull(searchResult)){
                callBack.onLoadMoreSuccess(searchResult);
            }else{
                callBack.onLoadMoreEmpty();
            }
        }
    }


}
