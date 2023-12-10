package com.example.taobaounion.presenter;

import com.example.taobaounion.base.IBasePresenter;
import com.example.taobaounion.view.IRedPacketCallBack;

//特惠界面数据请求接口
public interface IRedPacketPresenter extends IBasePresenter<IRedPacketCallBack> {
    /**
     * 获取特惠界面内容数据
     */
    void getOnSellContent();

    /**
     * 数据加载失败时重试
     */
    void retry();

    /**
     * 加载更多特惠界面的内容数据
     */
    void onLoadMore();
}
