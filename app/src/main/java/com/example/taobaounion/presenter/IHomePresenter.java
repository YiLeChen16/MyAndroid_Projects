package com.example.taobaounion.presenter;

import com.example.taobaounion.base.IBasePresenter;
import com.example.taobaounion.view.IHomeCallBack;

//获取主页分类数据的接口
public interface IHomePresenter extends IBasePresenter<IHomeCallBack> {
    /**
     * 获取商品分类
     */
    void getCategories();

    /**
     * 重新加页面
     */
    void retry();
}
