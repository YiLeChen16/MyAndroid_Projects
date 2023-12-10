package com.example.taobaounion.view;

import com.example.taobaounion.base.IBaseCallBack;
import com.example.taobaounion.moudle.bean.HomeViewPagerContent;

import java.util.List;

//viewPager的更新回调接口
public interface ICategoryPagerCallBack extends IBaseCallBack {
    /**
     * 数据从这里回来
     *
     * @param contents
     */
    void onContentLoaded(List<HomeViewPagerContent.DataBean> contents);

    /**
     * 对应分类页面加载更多数据成功
     *
     * @param contents
     * @param categoryId
     */
    void onLoadMoreSuccess(List<HomeViewPagerContent.DataBean> contents,int categoryId);

    /**
     * 对应分类页面加载更多数据失败
     * @param categoryId
     */
    void onLoadMoreError(int categoryId);

    /**
     * 对应分类页面加载更多数据为空
     */
    void onLoadMoreEmpty();

    /**
     * 轮播图数据加载成功
     *
     * @param contents
     */
    void onLooperListLoaded(List<HomeViewPagerContent.DataBean> contents);

    //用于给UI层返回分类页的ID给网络请求处
    int getCategoryId();
}
