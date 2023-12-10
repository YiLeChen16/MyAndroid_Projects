package com.example.taobaounion.presenter;

import com.example.taobaounion.base.IBasePresenter;
import com.example.taobaounion.view.ISelectedCallBack;

//精选界面的数据请求接口
public interface ISelectedPresenter extends IBasePresenter<ISelectedCallBack> {


    /**
     * 获取精选界面的内容
     */
    void getSelectedContent(int categoryID);
}
