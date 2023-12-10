package com.example.taobaounion.presenter;

import com.example.taobaounion.base.IBasePresenter;
import com.example.taobaounion.view.ICategoryPagerCallBack;

//分类页面数据获取接口
public interface ICategoryPagerPresenter extends IBasePresenter<ICategoryPagerCallBack> {
    /**
     * 根据分类界面id获取数据
     * @param categoryId
     */
    void getContentByCategoryId(int categoryId);


    /**
     * 根据分类界面id加载更多该界面的数据
     * @param categoryId
     */
    void loadMore(int categoryId);

    /**
     * 根据分类界面id重新加载页面数据
     * @param categoryId
     */
    void reload(int categoryId);



}
