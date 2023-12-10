package com.example.taobaounion.view;

import com.example.taobaounion.base.IBaseCallBack;
import com.example.taobaounion.moudle.bean.RedPacketContentResult;

//特惠界面的UI回调接口
public interface IRedPacketCallBack extends IBaseCallBack {

    /**
     * 特惠界面数据加载成功
     * @param result
     */
    void onLoadContentSuccess(RedPacketContentResult result);

    /**
     * 加载更多数据成功
     * @param result
     */
    void onLoadMoreSuccess(RedPacketContentResult result);

    /**
     * 加载更多数据错误
     */
    void onLoadMoreError();


    /**
     * 加载更多数据为空
     */
    void onLoadMoreEmpty();

}
