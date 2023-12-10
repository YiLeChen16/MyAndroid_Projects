package com.example.taobaounion.presenter;

import com.example.taobaounion.base.IBasePresenter;
import com.example.taobaounion.view.ISearchCallBack;

//搜索界面的Presenter
public interface ISearchPresenter extends IBasePresenter<ISearchCallBack> {

    /**
     * 搜索
     */
    void doSearch(String keyword);

    /**
     * 重新搜索
     */
    void reSearch();

    /**
     * 加载搜索历史数据
     */
    void loadHistory();


    /**
     * 删除搜索历史记录
     */
    void deleteHistories();

    /**
     * 加载推荐词
     */
    void loadRecommendWords();

    /**
     * 加载更多搜索结果
     */
    void loadMoreSearchResult();

    /**
     * 重新加载界面
     */
    void retry();
}
