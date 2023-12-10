package com.example.taobaounion.view;

import com.example.taobaounion.base.IBaseCallBack;
import com.example.taobaounion.moudle.bean.SearchRecommendWords;
import com.example.taobaounion.moudle.bean.SearchResult;

import java.util.List;

public interface ISearchCallBack extends IBaseCallBack {

    /**
     * 搜索成功结果
     * @param result
     */
    void onLoadSearchResult(SearchResult result);

    /**
     * 加载搜索页推荐词结果成功
     * @param recommendWords
     */
    void onLoadSearchRecommendWordsSuccess(SearchRecommendWords recommendWords);

    /**
     * 加载搜索页推荐词结果失败
     */
    void onLoadSearchRecommendWordsError();


    /**
     * 加载更多数据成功
     * @param result
     */
    void onLoadMoreSuccess(SearchResult result);

    /**
     * 加载更多数据失败
     */
    void onLoadMoreError();

    /**
     * 加载更多数据为空
     */
    void onLoadMoreEmpty();



    /**
     * 加载历史记录
     * @param histories
     */
    void onLoadHistoriesSuccess(List<String> histories);


    /**
     * 加载历史记录为空
     */
    void onLoadHistoriesEmpty();

}
