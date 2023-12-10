package com.example.taobaounion.base;

import com.example.taobaounion.view.IHomeCallBack;

public interface IBasePresenter<T> {

    //用泛型解决不同参数类型的问题
    /**
     * 注册UI通知更新接口
     */
    void registerViewCallBack(T callBack);

    /**
     * 取消UI通知更新接口的注册
     */
    void unregisterViewCallBack(T callBack);
}
