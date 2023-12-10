package com.example.taobaounion.base;
//UI回调基类接口
public interface IBaseCallBack {
    //网络错误
    void onError();

    //正在加载中
    void onLoading();

    //数据为空
    void onEmpty();



}
